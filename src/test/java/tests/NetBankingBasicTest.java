package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import pages.PaymentPage;

public class NetBankingBasicTest extends BaseTest {

    private PaymentPage paymentPage;

    @BeforeMethod
    public void setup() {
        paymentPage = new PaymentPage(driver);
    }

    @Test
    public void openPageAndClickNetBanking() throws Exception {

        System.out.println("=== Opening TimesPrime Payment Page ===");

        // Step 1: Open the payment page URL
        driver.get("https://sandbox.assets.juspay.in/payment-page/signature/picasso-019a97d01dc3000000000000ecb1edf8");

        // wait to load page fully (reduce to 5 sec)
        Thread.sleep(30000);

        System.out.println("=== Clicking NetBanking Option ===");

        // Step 2: Click NetBanking on left sidebar
        driver.findElement(By.xpath("//article[normalize-space()='Netbanking']")).click();

        System.out.println("=== NetBanking Clicked Successfully ===");
        
        // Wait for 20 seconds after clicking
        Thread.sleep(20000);
        
        System.out.println("=== Waited 20 seconds after clicking NetBanking ===");
    }
}
