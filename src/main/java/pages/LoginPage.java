package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // Locator for mobile input and continue button - may need tuning for the live site
    private By mobileInput = By.xpath("//input[@type='tel' or @name='mobile' or contains(@placeholder,'Mobile')]");
    private By continueBtn = By.xpath("//button[contains(text(),'Continue') or contains(text(),'Proceed') or contains(text(),'Get OTP')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterMobile(String mobile) {
        driver.findElement(mobileInput).sendKeys(mobile);
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }
}
