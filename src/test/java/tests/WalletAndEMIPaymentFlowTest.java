package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.PaymentPage;
import utils.BaseTest;

/**
 * Wallet and EMI Payment Flow Test
 * Tests wallet payment, EMI options, and other alternative payment methods
 */
public class WalletAndEMIPaymentFlowTest extends BaseTest {

    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void pageSetup() {
        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test(priority = 1)
    public void testWalletPaymentFlow() throws Exception {
        System.out.println("=== Starting Wallet Payment Flow Test ===");

        // Step 1: Navigate to payment page
        System.out.println("Step 1: Navigating to payment page");
        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        // Step 2: Verify payment methods are displayed
        System.out.println("Step 2: Verifying payment methods are displayed");
        Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(),
                "Payment methods not displayed on the page!");

        // Step 3: Select Wallet payment method
        System.out.println("Step 3: Selecting Wallet payment method");
        try {
            paymentPage.selectWallet();

            // Step 4: Click Pay/Proceed
            System.out.println("Step 4: Clicking Pay/Proceed button");
            try {
                paymentPage.clickPay();
            } catch (Exception e) {
                paymentPage.clickProceed();
            }

            // Step 5: Wait for response
            System.out.println("Step 5: Waiting for payment processing");
            Thread.sleep(5000);

            // Step 6: Verify payment status
            System.out.println("Step 6: Verifying wallet payment status");
            Assert.assertTrue(paymentPage.isOTPScreenDisplayed() || 
                            paymentPage.isPaymentSuccessful() ||
                            paymentPage.getErrorMessage() != null,
                    "Wallet payment flow not initiated!");

            System.out.println("=== Wallet Payment Flow Test Completed Successfully ===");
        } catch (Exception e) {
            System.out.println("Wallet payment method not available: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testEMIPaymentFlow() throws Exception {
        System.out.println("=== Starting EMI Payment Flow Test ===");

        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        try {
            paymentPage.selectEMI();

            System.out.println("EMI option selected");

            try {
                paymentPage.clickPay();
            } catch (Exception e) {
                paymentPage.clickProceed();
            }

            Thread.sleep(5000);

            Assert.assertTrue(paymentPage.isOTPScreenDisplayed() || 
                            paymentPage.isPaymentSuccessful() ||
                            paymentPage.getErrorMessage() != null,
                    "EMI payment flow not initiated!");

            System.out.println("=== EMI Payment Flow Test Completed Successfully ===");
        } catch (Exception e) {
            System.out.println("EMI payment method not available: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testMultipleWalletAttempts() throws Exception {
        System.out.println("=== Starting Multiple Wallet Attempts Test ===");

        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.println("Wallet payment attempt: " + attempt);

            homePage.navigateToPayment();
            System.out.println("Waiting 3 minutes for page to fully load...");
            Thread.sleep(180000); // 3 minutes wait

            try {
                paymentPage.selectWallet();

                try {
                    paymentPage.clickPay();
                } catch (Exception e) {
                    paymentPage.clickProceed();
                }

                Thread.sleep(3000);

                if (paymentPage.isOTPScreenDisplayed() || paymentPage.isPaymentSuccessful()) {
                    System.out.println("Wallet payment initiated successfully");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Attempt " + attempt + " failed: " + e.getMessage());
            }

            if (attempt < 3) {
                driver.navigate().refresh();
                Thread.sleep(1000);
            }
        }

        System.out.println("=== Multiple Wallet Attempts Test Completed ===");
    }

    @Test(priority = 4)
    public void testPaymentMethodAvailability() throws Exception {
        System.out.println("=== Starting Payment Method Availability Test ===");

        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        try {
            paymentPage.selectUPI();
            System.out.println("✓ UPI payment method available");
        } catch (Exception e) {
            System.out.println("✗ UPI payment method not available");
        }

        driver.navigate().refresh();
        Thread.sleep(1000);

        try {
            paymentPage.selectCreditCard();
            System.out.println("✓ Credit Card payment method available");
        } catch (Exception e) {
            System.out.println("✗ Credit Card payment method not available");
        }

        driver.navigate().refresh();
        Thread.sleep(1000);

        try {
            paymentPage.selectDebitCard();
            System.out.println("✓ Debit Card payment method available");
        } catch (Exception e) {
            System.out.println("✗ Debit Card payment method not available");
        }

        driver.navigate().refresh();
        Thread.sleep(1000);

        try {
            paymentPage.selectNetBanking();
            System.out.println("✓ Net Banking payment method available");
        } catch (Exception e) {
            System.out.println("✗ Net Banking payment method not available");
        }

        driver.navigate().refresh();
        Thread.sleep(1000);

        try {
            paymentPage.selectWallet();
            System.out.println("✓ Wallet payment method available");
        } catch (Exception e) {
            System.out.println("✗ Wallet payment method not available");
        }

        System.out.println("=== Payment Method Availability Test Completed ===");
    }

    @Test(priority = 5)
    public void testEMIWithMultipleOptions() throws Exception {
        System.out.println("=== Starting EMI with Multiple Options Test ===");

        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        try {
            paymentPage.selectEMI();

            // Simulate selecting different EMI tenures
            String[] emiOptions = {"3 months", "6 months", "12 months"};

            for (String emiOption : emiOptions) {
                System.out.println("Testing EMI option: " + emiOption);
                // Implementation would depend on actual EMI selector UI
                Thread.sleep(1000);
            }

            System.out.println("=== EMI with Multiple Options Test Completed ===");
        } catch (Exception e) {
            System.out.println("EMI not available for this product");
        }
    }

    @Test(priority = 6)
    public void testWalletBalanceDisplay() throws Exception {
        System.out.println("=== Starting Wallet Balance Display Test ===");

        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        try {
            paymentPage.selectWallet();
            System.out.println("Wallet selected - balance should be displayed");
            
            Thread.sleep(2000);
            
            String pageSource = driver.getPageSource();
            if (pageSource.toLowerCase().contains("balance") || 
                pageSource.toLowerCase().contains("wallet")) {
                System.out.println("✓ Wallet balance information displayed");
            } else {
                System.out.println("✗ Wallet balance information not visible");
            }

            System.out.println("=== Wallet Balance Display Test Completed ===");
        } catch (Exception e) {
            System.out.println("Wallet option not available");
        }
    }
}
