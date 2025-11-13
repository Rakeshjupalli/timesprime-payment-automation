package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {

    private WebDriver driver;

    private By upiOption = By.xpath("//div[contains(text(),'UPI') or contains(text(),'Pay via UPI')]");
    private By payButton = By.xpath("//button[contains(text(),'Pay') or contains(text(),'Proceed to pay') or contains(text(),'Continue to pay')]");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseUPI() {
        driver.findElement(upiOption).click();
    }

    public void clickPay() {
        driver.findElement(payButton).click();
    }

    public boolean isOTPScreenDisplayed() {
        try {
            // Basic heuristics: page contains 'OTP' or url contains 'otp'
            return driver.getPageSource().toLowerCase().contains("otp") ||
                   driver.getCurrentUrl().toLowerCase().contains("otp");
        } catch (Exception e) {
            return false;
        }
    }
}
