package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import utils.BaseTest;

public class PaymentFlowTest extends BaseTest {

    @Test
    public void testTimesPrimePaymentFlow() throws InterruptedException {

        driver.get("https://www.timesprime.com");
        System.out.println("Waiting 3 minutes for page to fully load...");
        Thread.sleep(180000); // 3 minutes wait

        HomePage home = new HomePage(driver);
        // attempt to click join/subscribe; it's okay if locator needs tuning for the live page
        try {
            home.clickJoinNow();
        } catch (Exception e) {
            // ignore if not present - continue the flow
        }

        LoginPage login = new LoginPage(driver);
        login.enterMobile("9999999999");   // Use a valid test number if required
        login.clickContinue();

    // Wait for navigation to payment (simple static waits here; replace with explicit waits if desired)
    // Increased sleep to ensure payment/login flow finishes loading
    Thread.sleep(5000);

        PaymentPage payment = new PaymentPage(driver);
        try {
            payment.selectUPI();
        } catch (Exception e) {
            // if UPI option not visible, attempt to click pay directly
        }
        try {
            payment.clickPay();
        } catch (Exception e) {
            // ignore
        }

        Thread.sleep(5000);

        Assert.assertTrue(payment.isOTPScreenDisplayed(),
                "OTP page not reached!");
    }
}
