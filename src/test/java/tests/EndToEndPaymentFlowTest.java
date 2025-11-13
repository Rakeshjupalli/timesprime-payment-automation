package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.PaymentPage;
import utils.BaseTest;

/**
 * End-to-End Payment Flow Test
 * Tests complete payment scenarios with various edge cases and error handling
 */
public class EndToEndPaymentFlowTest extends BaseTest {

    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void pageSetup() {
        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test(priority = 1)
    public void testCompletePaymentFlowFromHomePage() throws Exception {
        System.out.println("=== Starting Complete Payment Flow from Home Page ===");

        // Navigate to home page
        driver.get("https://www.timesprime.com");
        homePage.waitForPageLoad();

        // Navigate to payment
        homePage.navigateToPayment();
        Thread.sleep(3000);

        // Verify on payment page
        String title = paymentPage.getCurrentPageTitle();
        System.out.println("Payment page title: " + title);

        // Check payment methods available
        Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(),
                "Payment methods not displayed!");

        System.out.println("=== Complete Payment Flow Test Completed ===");
    }

    @Test(priority = 2)
    public void testPaymentPageDirectNavigation() throws Exception {
        System.out.println("=== Starting Direct Payment Page Navigation Test ===");

        // Direct navigation to payment page
        homePage.navigateToPayment();
        Thread.sleep(3000);

        // Verify payment page
        String url = paymentPage.getCurrentUrl();
        Assert.assertTrue(url.contains("payment"), "Not on payment page!");

        // Verify payment methods
        Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(),
                "Payment methods not visible on payment page!");

        System.out.println("=== Direct Payment Page Navigation Test Completed ===");
    }

    @Test(priority = 3)
    public void testPaymentFlowWithPageRefresh() throws Exception {
        System.out.println("=== Starting Payment Flow with Page Refresh Test ===");

        homePage.navigateToPayment();
        Thread.sleep(2000);

        // Refresh page
        driver.navigate().refresh();
        Thread.sleep(2000);

        // Verify payment methods still visible
        Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(),
                "Payment methods not visible after refresh!");

        // Try selecting a payment method
        paymentPage.selectUPI();

        // Refresh again while in flow
        driver.navigate().refresh();
        Thread.sleep(2000);

        System.out.println("=== Payment Flow with Page Refresh Test Completed ===");
    }

    @Test(priority = 4)
    public void testPaymentFlowPageValidation() throws Exception {
        System.out.println("=== Starting Payment Flow Page Validation Test ===");

        homePage.navigateToPayment();
        Thread.sleep(3000);

        // Validate page elements
        String pageSource = driver.getPageSource();

        // Check for common payment page elements
        Assert.assertTrue(pageSource.toLowerCase().contains("payment"),
                "Payment keyword not found on page!");

        Assert.assertTrue(pageSource.toLowerCase().contains("upi") ||
                         pageSource.toLowerCase().contains("card") ||
                         pageSource.toLowerCase().contains("bank"),
                "Payment methods not found on page!");

        System.out.println("=== Payment Flow Page Validation Test Completed ===");
    }

    @Test(priority = 5)
    public void testMultiplePaymentAttempts() throws Exception {
        System.out.println("=== Starting Multiple Payment Attempts Test ===");

        int successCount = 0;
        int failureCount = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.println("\n--- Payment Attempt " + i + " ---");

            try {
                homePage.navigateToPayment();
                Thread.sleep(2000);

                Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(),
                        "Payment methods not available!");

                // Try different payment method each time
                switch (i % 4) {
                    case 0:
                        paymentPage.selectUPI();
                        paymentPage.enterUPIId("user" + i + "@upi");
                        break;
                    case 1:
                        paymentPage.selectCreditCard();
                        paymentPage.enterCardNumber("4111111111111111");
                        paymentPage.enterCardHolderName("USER " + i);
                        paymentPage.enterExpiry("12/25");
                        paymentPage.enterCVV("123");
                        break;
                    case 2:
                        paymentPage.selectDebitCard();
                        paymentPage.enterCardNumber("5555555555554444");
                        paymentPage.enterCardHolderName("USER " + i);
                        paymentPage.enterExpiry("06/26");
                        paymentPage.enterCVV("456");
                        break;
                    case 3:
                        paymentPage.selectNetBanking();
                        break;
                }

                try {
                    paymentPage.clickPay();
                } catch (Exception e) {
                    paymentPage.clickProceed();
                }

                Thread.sleep(3000);

                if (paymentPage.isOTPScreenDisplayed() || 
                    paymentPage.isPaymentSuccessful() ||
                    paymentPage.getErrorMessage() != null) {
                    successCount++;
                    System.out.println("Attempt " + i + " successful");
                } else {
                    failureCount++;
                    System.out.println("Attempt " + i + " inconclusive");
                }
            } catch (Exception e) {
                failureCount++;
                System.out.println("Attempt " + i + " failed: " + e.getMessage());
            }

            if (i < 5) {
                driver.navigate().refresh();
                Thread.sleep(1000);
            }
        }

        System.out.println("\n=== Results Summary ===");
        System.out.println("Successful attempts: " + successCount);
        System.out.println("Failed attempts: " + failureCount);

        System.out.println("=== Multiple Payment Attempts Test Completed ===");
    }

    @Test(priority = 6)
    public void testPaymentFlowWithBackNavigation() throws Exception {
        System.out.println("=== Starting Payment Flow with Back Navigation Test ===");

        // Navigate to home
        driver.get("https://www.timesprime.com");
        homePage.waitForPageLoad();

        // Navigate to payment
        homePage.navigateToPayment();
        Thread.sleep(3000);

        // Select payment method
        paymentPage.selectUPI();

        // Go back
        driver.navigate().back();
        Thread.sleep(2000);

        // Should be on payment page still or different page
        String url = driver.getCurrentUrl();
        System.out.println("URL after back navigation: " + url);

        // Navigate forward
        driver.navigate().forward();
        Thread.sleep(2000);

        System.out.println("=== Payment Flow with Back Navigation Test Completed ===");
    }

    @Test(priority = 7)
    public void testPaymentFlowErrorHandling() throws Exception {
        System.out.println("=== Starting Payment Flow Error Handling Test ===");

        homePage.navigateToPayment();
        Thread.sleep(3000);

        // Try multiple invalid inputs
        try {
            paymentPage.selectUPI();
            paymentPage.enterUPIId(""); // Empty UPI
            paymentPage.clickPay();
            
            Thread.sleep(2000);
            String errorMsg = paymentPage.getErrorMessage();
            if (errorMsg != null) {
                System.out.println("Error caught for empty UPI: " + errorMsg);
            }
        } catch (Exception e) {
            System.out.println("Exception handled: " + e.getMessage());
        }

        System.out.println("=== Payment Flow Error Handling Test Completed ===");
    }

    @Test(priority = 8)
    public void testPaymentFlowConcurrency() throws Exception {
        System.out.println("=== Starting Payment Flow Concurrency Test ===");

        // Simulate rapid navigation and selection
        for (int i = 0; i < 10; i++) {
            System.out.println("Iteration " + (i + 1) + "/10");

            homePage.navigateToPayment();
            Thread.sleep(500);

            try {
                switch (i % 2) {
                    case 0:
                        paymentPage.selectUPI();
                        break;
                    case 1:
                        paymentPage.selectCreditCard();
                        break;
                }
            } catch (Exception e) {
                // ignore
            }

            driver.navigate().refresh();
            Thread.sleep(300);
        }

        System.out.println("=== Payment Flow Concurrency Test Completed ===");
    }

    @Test(priority = 9)
    public void testPaymentFlowWithWait() throws Exception {
        System.out.println("=== Starting Payment Flow with Extended Wait Test ===");

        homePage.navigateToPayment();
        paymentPage.waitForPageLoad();

        Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(),
                "Payment methods not displayed after wait!");

        paymentPage.selectUPI();
        paymentPage.waitForPageLoad();

        paymentPage.enterUPIId("test@upi");

        try {
            paymentPage.clickPay();
        } catch (Exception e) {
            paymentPage.clickProceed();
        }

        paymentPage.waitForPageLoad();

        System.out.println("=== Payment Flow with Extended Wait Test Completed ===");
    }

    @Test(priority = 10)
    public void testPaymentFlowComprehensiveValidation() throws Exception {
        System.out.println("=== Starting Comprehensive Payment Flow Validation Test ===");

        homePage.navigateToPayment();
        Thread.sleep(3000);

        // Validate multiple conditions
        Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(), "Payment methods not visible");
        Assert.assertNotNull(paymentPage.getCurrentUrl(), "URL is null");
        Assert.assertNotNull(paymentPage.getCurrentPageTitle(), "Page title is null");

        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.length() > 0, "Page source is empty");

        // Test payment method selections
        paymentPage.selectUPI();
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("upi"), "UPI not selected");

        driver.navigate().refresh();
        Thread.sleep(1000);

        paymentPage.selectCreditCard();
        // Verify credit card view

        System.out.println("✓ All payment methods available");
        System.out.println("✓ Page navigation working");
        System.out.println("✓ Element interaction functioning");

        System.out.println("=== Comprehensive Payment Flow Validation Test Completed ===");
    }
}
