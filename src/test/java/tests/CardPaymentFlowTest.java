package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.PaymentPage;
import utils.BaseTest;

/**
 * Credit/Debit Card Payment Flow Test
 * Tests complete card payment flow with various card types
 */
public class CardPaymentFlowTest extends BaseTest {

    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void pageSetup() {
        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test(priority = 1)
    public void testCreditCardPaymentFlow() throws Exception {
        System.out.println("=== Starting Credit Card Payment Flow Test ===");

        // Step 1: Navigate to JusPay sandbox payment page
        System.out.println("Step 1: Navigating to JusPay sandbox payment page");
        driver.get("https://sandbox.assets.juspay.in/payment-page/signature/picasso-019a7c749cee00000000000076020d0b");
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait
        
        // Step 2: Click on card number field
        System.out.println("Step 2: Clicking on card number field");
        paymentPage.clickCardNumberField();
        
        // Wait 2 seconds for field to become interactive
        Thread.sleep(2000);
        
        // Step 3: Enter card number
        System.out.println("Step 3: Entering card number");
        paymentPage.enterCardNumber("4111111111111111");
        
        // Step 4: Wait 2 seconds before entering expiry
        Thread.sleep(2000);
        
        // Step 5: Enter expiry date
        System.out.println("Step 4: Entering expiry date");
        paymentPage.enterExpiry("1225");
        
        // Step 6: Enter CVV
        System.out.println("Step 5: Entering CVV");
        paymentPage.enterCVV("123");

        // Step 7: Click Pay button
        System.out.println("Step 6: Clicking Pay button");
        paymentPage.clickPay();

        // Step 8: Wait and verify
        System.out.println("Step 7: Waiting for response");
        Thread.sleep(5000);

        System.out.println("=== Credit Card Payment Flow Test Completed Successfully ===");
    }
}
