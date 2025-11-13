package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Navigation locators
    private By joinNow = By.xpath("//button[contains(text(),'Join')] | //a[contains(text(),'Subscribe')] | //button[contains(text(),'Buy')]");
    private By paymentLink = By.xpath("//a[contains(text(),'Payment')] | //a[contains(@href,'payment')]");
    private By loginSignupLink = By.xpath("//a[contains(text(),'Login')] | //button[contains(text(),'Sign Up')]");
    private By makePaymentButton = By.xpath("//button[contains(text(),'Make Payment')] | //a[contains(text(),'Make Payment')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickJoinNow() {
        driver.findElement(joinNow).click();
    }

    public void navigateToPayment() {
        try {
            driver.get("https://www.timesprime.com/payment/make-payment?origin=PAYMENT");
            // Increased sleep to allow login/payment page to fully load before interactions
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickMakePayment() throws Exception {
        try {
            driver.findElement(makePaymentButton).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new Exception("Failed to click Make Payment button: " + e.getMessage());
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitForPageLoad() throws Exception {
        // Increase default page load sleep to give the payment/login UI extra time
        Thread.sleep(5000);
    }
}
