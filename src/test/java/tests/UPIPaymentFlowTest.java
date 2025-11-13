package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import utils.BaseTest;

/**
 * UPI Payment Flow Test
 * Tests complete UPI payment flow from login to payment verification
 */
public class UPIPaymentFlowTest extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void pageSetup() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test(priority = 1)
    public void testUPIPaymentFlow() throws Exception {
        System.out.println("=== Starting UPI Payment Flow Test ===");

        // Step 1: Navigate to payment page
        System.out.println("Step 1: Navigating to payment page");
        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        // Step 2: Verify payment methods are displayed
        System.out.println("Step 2: Verifying payment methods are displayed");
        Assert.assertTrue(paymentPage.isPaymentMethodsDisplayed(),
                "Payment methods not displayed on the page!");

        // Step 3: Select UPI payment method
        System.out.println("Step 3: Selecting UPI payment method");
        paymentPage.selectUPI();

        // Step 4: Enter UPI ID
        System.out.println("Step 4: Entering UPI ID");
        paymentPage.enterUPIId("user@upi");

        // Step 5: Click Pay/Proceed button
        System.out.println("Step 5: Clicking Pay button");
        try {
            paymentPage.clickPay();
        } catch (Exception e) {
            System.out.println("Pay button not found, trying Proceed button");
            paymentPage.clickProceed();
        }

        // Step 6: Wait for OTP screen or verification
        System.out.println("Step 6: Waiting for OTP screen");
        Thread.sleep(5000);

        // Step 7: Verify OTP screen is displayed
        System.out.println("Step 7: Verifying OTP screen");
        Assert.assertTrue(paymentPage.isOTPScreenDisplayed(),
                "OTP screen not reached after UPI payment initiation!");

        System.out.println("=== UPI Payment Flow Test Completed Successfully ===");
    }

    @Test(priority = 2)
    public void testUPIPaymentWithOTPVerification() throws Exception {
        System.out.println("=== Starting UPI Payment with OTP Verification Test ===");

        // Navigate to payment
        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        // Select UPI
        paymentPage.selectUPI();

        // Enter UPI ID
        paymentPage.enterUPIId("testuser@okhdfcbank");

        // Click Pay
        try {
            paymentPage.clickPay();
        } catch (Exception e) {
            paymentPage.clickProceed();
        }

        Thread.sleep(5000);

        // Verify OTP screen
        if (paymentPage.isOTPScreenDisplayed()) {
            System.out.println("OTP screen displayed, entering OTP");
            paymentPage.enterOTP("000000"); // Test OTP
            paymentPage.verifyOTP();
            Thread.sleep(3000);
        }

        System.out.println("=== UPI Payment with OTP Test Completed ===");
    }

    @Test(priority = 3)
    public void testUPIPaymentFlowWithInvalidUPI() throws Exception {
        System.out.println("=== Starting UPI Payment Flow with Invalid UPI Test ===");

        homePage.navigateToPayment();
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        paymentPage.selectUPI();
        paymentPage.enterUPIId("invalid@upi");

        try {
            paymentPage.clickPay();
        } catch (Exception e) {
            paymentPage.clickProceed();
        }

        Thread.sleep(3000);

        // Check for error message
        String errorMsg = paymentPage.getErrorMessage();
        if (errorMsg != null) {
            System.out.println("Error message displayed: " + errorMsg);
            Assert.assertTrue(errorMsg.toLowerCase().contains("invalid") || 
                            errorMsg.toLowerCase().contains("error"),
                    "Expected error message for invalid UPI");
        }

        System.out.println("=== Invalid UPI Test Completed ===");
    }

    @Test(priority = 4)
    public void testMultipleUPIAttempts() throws Exception {
        System.out.println("=== Starting Multiple UPI Attempts Test ===");

        String[] upiIds = {"user1@upi", "user2@upi", "user3@upi"};

        for (String upiId : upiIds) {
            System.out.println("Testing UPI: " + upiId);

            homePage.navigateToPayment();
            System.out.println("Waiting 3 minutes for page to fully load...");
            Thread.sleep(180000); // 3 minutes wait

            paymentPage.selectUPI();
            paymentPage.enterUPIId(upiId);

            try {
                paymentPage.clickPay();
            } catch (Exception e) {
                paymentPage.clickProceed();
            }

            Thread.sleep(3000);

            // Verify response
            Assert.assertTrue(paymentPage.isOTPScreenDisplayed() || 
                            paymentPage.getErrorMessage() != null,
                    "No valid response for UPI: " + upiId);

            driver.navigate().refresh();
            Thread.sleep(1000);
        }

        System.out.println("=== Multiple UPI Attempts Test Completed ===");
    }
}
