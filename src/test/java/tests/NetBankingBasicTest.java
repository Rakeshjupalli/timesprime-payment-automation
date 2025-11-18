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

        // wait to load page fully
        Thread.sleep(20000);

        System.out.println("=== Clicking NetBanking Option ===");

        // Step 2: Click NetBanking on left sidebar
        driver.findElement(By.xpath("//article[normalize-space()='Netbanking']")).click();

        System.out.println("=== NetBanking Clicked Successfully ===");
        
        // Wait for bank list to load
        Thread.sleep(3000);
        
        System.out.println("=== Selecting Canara Bank ===");
        
        // Step 3: Select Canara Bank from the list
        driver.findElement(By.xpath("//*[contains(text(),'Canara Bank')]")).click();
        
        System.out.println("=== Canara Bank Selected Successfully ===");
        
        // Wait for Proceed to Pay button to appear
        Thread.sleep(5000);
        
        System.out.println("=== Clicking Proceed to Pay Button ===");
        
        // Step 4: Click Proceed to Pay button with multiple selectors
        boolean buttonClicked = false;
        String[] selectors = {
            "//*[@data-testid='btn_pay']",
            "//*[@class='textView']//article[contains(text(),'Proceed to pay')]",
            "//article[normalize-space()='Proceed to pay ₹10,000']",
            "//*[contains(., 'Proceed') and contains(., 'pay')]"
        };
        
        for (String selector : selectors) {
            try {
                driver.findElement(By.xpath(selector)).click();
                System.out.println("=== Proceed to Pay Button Clicked Successfully using: " + selector + " ===");
                buttonClicked = true;
                break;
            } catch (Exception e) {
                // Try next selector
                System.out.println("=== Selector failed: " + selector + " ===");
            }
        }
        
        if (!buttonClicked) {
            System.out.println("=== ERROR: Proceed to Pay button not found with any selector ===");
        }
        
        // Wait to observe the result
        Thread.sleep(20000);
        
        System.out.println("=== Test Completed - NetBanking flow ===");
    }
}
