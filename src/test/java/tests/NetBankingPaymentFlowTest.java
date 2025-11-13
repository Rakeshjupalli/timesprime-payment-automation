package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.PaymentPage;
import utils.BaseTest;

/**
 * Net Banking Payment Flow Test
 * Tests complete net banking payment flow with different banks
 */
public class NetBankingPaymentFlowTest extends BaseTest {

    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void pageSetup() {
        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test(priority = 1)
    public void testNetBankingPaymentFlow() throws Exception {
        System.out.println("=== Starting Net Banking Payment Flow Test ===");

        // Step 1: Navigate to payment page
        System.out.println("Step 1: Navigating to payment page");
        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        // Step 2: Verify payment methods are displayed
        System.out.println("Step 2: Verifying payment methods are displayed");
        Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(),
                "Payment methods not displayed on the page!");

        // Step 3: Select Net Banking payment method
        System.out.println("Step 3: Selecting Net Banking payment method");
        paymentPage.selectNetBanking();

        // Step 4: Select a bank (e.g., HDFC Bank)
        System.out.println("Step 4: Selecting bank");
        try {
            paymentPage.selectBank("HDFC Bank");
        } catch (Exception e) {
            System.out.println("Bank selection not required or bank dropdown not found");
        }

        // Step 5: Click Pay/Proceed button
        System.out.println("Step 5: Clicking Pay/Proceed button");
        try {
            paymentPage.clickPay();
        } catch (Exception e) {
            System.out.println("Pay button not found, trying Proceed button");
            paymentPage.clickProceed();
        }

        // Step 6: Wait for bank redirect or OTP
        System.out.println("Step 6: Waiting for bank redirect");
        Thread.sleep(5000);

        // Step 7: Verify navigation
        System.out.println("Step 7: Verifying page state");
        String currentUrl = paymentPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("payment") || 
                         currentUrl.contains("bank") ||
                         paymentPage.isOTPScreenDisplayed(),
                "Net Banking payment flow not initiated!");

        System.out.println("=== Net Banking Payment Flow Test Completed Successfully ===");
    }

    @Test(priority = 2)
    public void testNetBankingWithMultipleBanks() throws Exception {
        System.out.println("=== Starting Multiple Banks Test ===");

        String[] banks = {"HDFC Bank", "ICICI Bank", "SBI", "Axis Bank", "Kotak Mahindra Bank"};

        for (String bank : banks) {
            System.out.println("Testing Net Banking with: " + bank);

            homePage.navigateToPayment();
            System.out.println("Waiting 3 minutes for page to fully load...");
            Thread.sleep(180000); // 3 minutes wait

            paymentPage.selectNetBanking();

            try {
                paymentPage.selectBank(bank);
            } catch (Exception e) {
                System.out.println("Bank not available: " + bank);
                continue;
            }

            try {
                paymentPage.clickPay();
            } catch (Exception e) {
                paymentPage.clickProceed();
            }

            Thread.sleep(3000);

            String currentUrl = paymentPage.getCurrentUrl();
            Assert.assertTrue(!currentUrl.equals(paymentPage.getCurrentUrl()) || 
                            paymentPage.isOTPScreenDisplayed(),
                    "Net Banking not working for: " + bank);

            driver.navigate().refresh();
            Thread.sleep(1000);
        }

        System.out.println("=== Multiple Banks Test Completed ===");
    }

    @Test(priority = 3)
    public void testNetBankingPaymentFlowWithRetry() throws Exception {
        System.out.println("=== Starting Net Banking with Retry Test ===");

        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.println("Attempt " + attempt + " of 3");

            homePage.navigateToPayment();
            System.out.println("Waiting 3 minutes for page to fully load...");
            Thread.sleep(180000); // 3 minutes wait

            paymentPage.selectNetBanking();

            try {
                paymentPage.selectBank("HDFC Bank");
            } catch (Exception e) {
                // ignore
            }

            try {
                paymentPage.clickPay();
            } catch (Exception e) {
                paymentPage.clickProceed();
            }

            Thread.sleep(3000);

            if (paymentPage.isOTPScreenDisplayed() || paymentPage.isPaymentSuccessful()) {
                System.out.println("Payment initiated on attempt: " + attempt);
                break;
            }

            if (attempt < 3) {
                driver.navigate().refresh();
                Thread.sleep(1000);
            }
        }

        System.out.println("=== Net Banking with Retry Test Completed ===");
    }

    @Test(priority = 4)
    public void testNetBankingTimeoutHandling() throws Exception {
        System.out.println("=== Starting Net Banking Timeout Test ===");

        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        paymentPage.selectNetBanking();

        try {
            paymentPage.selectBank("HDFC Bank");
        } catch (Exception e) {
            // ignore
        }

        try {
            paymentPage.clickPay();
        } catch (Exception e) {
            paymentPage.clickProceed();
        }

        // Wait for extended time to handle bank server delays
        System.out.println("Waiting for bank response (extended timeout)");
        Thread.sleep(10000);

        String pageTitle = paymentPage.getCurrentPageTitle();
        System.out.println("Current page title: " + pageTitle);

        System.out.println("=== Net Banking Timeout Test Completed ===");
    }
}
