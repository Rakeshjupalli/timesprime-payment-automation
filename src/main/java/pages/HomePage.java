package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    // This locator may need adjustment based on TimesPrime's current UI
    private By joinNow = By.xpath("//button[contains(text(),'Join') or contains(text(),'Subscribe') or contains(text(),'Buy')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickJoinNow() {
        driver.findElement(joinNow).click();
    }
}
