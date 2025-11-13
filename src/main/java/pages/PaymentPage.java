package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Payment method locators
    private By upiOption = By.xpath("//div[contains(text(),'UPI')] | //label[contains(text(),'UPI')] | //button[contains(text(),'UPI')]");
    private By creditCardOption = By.xpath("//div[contains(text(),'Credit Card')] | //label[contains(text(),'Credit Card')] | //button[contains(text(),'Credit Card')]");
    private By debitCardOption = By.xpath("//div[contains(text(),'Debit Card')] | //label[contains(text(),'Debit Card')] | //button[contains(text(),'Debit Card')]");
    private By netBankingOption = By.xpath("//div[contains(text(),'Net Banking')] | //label[contains(text(),'Net Banking')] | //button[contains(text(),'Net Banking')]");
    private By walletOption = By.xpath("//div[contains(text(),'Wallet')] | //label[contains(text(),'Wallet')] | //button[contains(text(),'Wallet')]");
    private By emiOption = By.xpath("//div[contains(text(),'EMI')] | //label[contains(text(),'EMI')] | //button[contains(text(),'EMI')]");

    // Common payment form fields - Using specific test IDs
    private By payButton = By.xpath("//*[@data-testid='msg_text']");
    private By proceedButton = By.xpath("//button[contains(text(),'Proceed')] | //button[contains(text(),'Continue')]");
    private By amountField = By.xpath("//input[@placeholder*='Amount' or @placeholder*='amount' or @name='amount']");
    private By orderIdField = By.xpath("//input[@placeholder*='Order' or @name='orderId']");

    // Card payment fields - Using multiple locator strategies
    private By cardNumberField = By.xpath("//*[@data-testid='edt_card_number'] | //*[@id='50000133'] | //*[@id='50000131'] | //*[@class='editText'] | //*[@configpath='Screen.AddCard|cardNumber|editTextConfig']");
    private By cardHolderField = By.xpath("//input[@placeholder*='Cardholder' or @placeholder*='Name' or @name='cardHolder']");
    private By expiryField = By.xpath("//*[@data-testid='edt_expiry_date']");
    private By cvvField = By.xpath("//*[@data-testid='edt_cvv']");

    // UPI fields
    private By upiIdField = By.xpath("//input[@placeholder*='UPI' or @placeholder*='upi' or @name='upiId']");

    // Net Banking
    private By bankSelectDropdown = By.xpath("//select[@name='bank'] | //div[contains(@class,'bank-select')]");

    // OTP and verification
    private By otpField = By.xpath("//input[@placeholder*='OTP' or @name='otp']");
    private By verifyOtpButton = By.xpath("//button[contains(text(),'Verify')] | //button[contains(text(),'Submit OTP')]");

    // Wallet/savings options
    private By walletAmountDisplay = By.xpath("//div[contains(text(),'Balance')] | //span[contains(text(),'Wallet')]");
    private By saveCardCheckbox = By.xpath("//input[@type='checkbox' and (contains(@class,'save') or @name*='save')]");

    // Error/Success messages
    private By errorMessage = By.xpath("//div[contains(@class,'error') or contains(@class,'alert-danger')] | //span[@class*='error']");
    private By successMessage = By.xpath("//div[contains(@class,'success') or contains(@class,'alert-success')] | //span[@class*='success']");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Payment Method Selection Methods
    public void selectUPI() throws Exception {
        try {
            WebElement upi = wait.until(ExpectedConditions.elementToBeClickable(upiOption));
            upi.click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new Exception("Failed to select UPI payment method: " + e.getMessage());
        }
    }

    public void selectCreditCard() throws Exception {
        try {
            WebElement creditCard = wait.until(ExpectedConditions.elementToBeClickable(creditCardOption));
            creditCard.click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new Exception("Failed to select Credit Card payment method: " + e.getMessage());
        }
    }

    public void selectDebitCard() throws Exception {
        try {
            WebElement debitCard = wait.until(ExpectedConditions.elementToBeClickable(debitCardOption));
            debitCard.click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new Exception("Failed to select Debit Card payment method: " + e.getMessage());
        }
    }

    public void selectNetBanking() throws Exception {
        try {
            WebElement netBanking = wait.until(ExpectedConditions.elementToBeClickable(netBankingOption));
            netBanking.click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new Exception("Failed to select Net Banking payment method: " + e.getMessage());
        }
    }

    public void selectWallet() throws Exception {
        try {
            WebElement wallet = wait.until(ExpectedConditions.elementToBeClickable(walletOption));
            wallet.click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new Exception("Failed to select Wallet payment method: " + e.getMessage());
        }
    }

    public void selectEMI() throws Exception {
        try {
            WebElement emi = wait.until(ExpectedConditions.elementToBeClickable(emiOption));
            emi.click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new Exception("Failed to select EMI payment method: " + e.getMessage());
        }
    }

    // UPI Payment Flow
    public void enterUPIId(String upiId) throws Exception {
        try {
            WebElement upiInput = wait.until(ExpectedConditions.visibilityOfElementLocated(upiIdField));
            upiInput.clear();
            upiInput.sendKeys(upiId);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new Exception("Failed to enter UPI ID: " + e.getMessage());
        }
    }

    // Card Payment Flow
    public void clickCardNumberField() throws Exception {
        try {
            WebElement cardInput = wait.until(ExpectedConditions.elementToBeClickable(cardNumberField));
            cardInput.click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new Exception("Failed to click card number field: " + e.getMessage());
        }
    }

    public void enterCardNumber(String cardNumber) throws Exception {
        try {
            WebElement cardInput = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberField));
            // Don't clear - field might not support it, just send keys
            cardInput.sendKeys(cardNumber);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new Exception("Failed to enter card number: " + e.getMessage());
        }
    }

    public void enterCardHolderName(String holderName) throws Exception {
        try {
            WebElement holderInput = driver.findElement(cardHolderField);
            holderInput.clear();
            holderInput.sendKeys(holderName);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new Exception("Failed to enter cardholder name: " + e.getMessage());
        }
    }

    public void enterExpiry(String expiry) throws Exception {
        try {
            WebElement expiryInput = driver.findElement(expiryField);
            expiryInput.clear();
            expiryInput.sendKeys(expiry);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new Exception("Failed to enter expiry date: " + e.getMessage());
        }
    }

    public void enterCVV(String cvv) throws Exception {
        try {
            WebElement cvvInput = driver.findElement(cvvField);
            cvvInput.clear();
            cvvInput.sendKeys(cvv);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new Exception("Failed to enter CVV: " + e.getMessage());
        }
    }

    // Common Payment Actions
    public void clickPay() throws Exception {
        try {
            WebElement pay = wait.until(ExpectedConditions.elementToBeClickable(payButton));
            pay.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new Exception("Failed to click Pay button: " + e.getMessage());
        }
    }

    public void clickProceed() throws Exception {
        try {
            WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(proceedButton));
            proceed.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new Exception("Failed to click Proceed button: " + e.getMessage());
        }
    }

    public void enterAmount(String amount) throws Exception {
        try {
            WebElement amountInput = driver.findElement(amountField);
            amountInput.clear();
            amountInput.sendKeys(amount);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new Exception("Failed to enter amount: " + e.getMessage());
        }
    }

    // OTP Verification
    public void enterOTP(String otp) throws Exception {
        try {
            WebElement otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(otpField));
            otpInput.clear();
            otpInput.sendKeys(otp);
            Thread.sleep(300);
        } catch (Exception e) {
            throw new Exception("Failed to enter OTP: " + e.getMessage());
        }
    }

    public void verifyOTP() throws Exception {
        try {
            WebElement verifyBtn = wait.until(ExpectedConditions.elementToBeClickable(verifyOtpButton));
            verifyBtn.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new Exception("Failed to verify OTP: " + e.getMessage());
        }
    }

    // Verification Methods
    public boolean isPaymentMethodsDisplayed() {
        try {
            return driver.findElements(upiOption).size() > 0 ||
                   driver.findElements(creditCardOption).size() > 0 ||
                   driver.findElements(debitCardOption).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOTPScreenDisplayed() {
        try {
            return driver.getPageSource().toLowerCase().contains("otp") ||
                   driver.getCurrentUrl().toLowerCase().contains("otp") ||
                   driver.findElements(otpField).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPaymentSuccessful() {
        try {
            return driver.getPageSource().toLowerCase().contains("success") ||
                   driver.getPageSource().toLowerCase().contains("completed") ||
                   driver.findElements(successMessage).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            List<WebElement> errors = driver.findElements(errorMessage);
            if (!errors.isEmpty()) {
                return errors.get(0).getText();
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    public boolean saveCard(boolean shouldSave) {
        try {
            List<WebElement> checkboxes = driver.findElements(saveCardCheckbox);
            if (!checkboxes.isEmpty()) {
                WebElement checkbox = checkboxes.get(0);
                boolean isChecked = checkbox.isSelected();
                if ((shouldSave && !isChecked) || (!shouldSave && isChecked)) {
                    checkbox.click();
                }
                return true;
            }
        } catch (Exception e) {
            // ignore
        }
        return false;
    }

    public void selectBank(String bankName) throws Exception {
        try {
            WebElement bankDropdown = driver.findElement(bankSelectDropdown);
            bankDropdown.click();
            Thread.sleep(500);
            By bankOption = By.xpath("//option[contains(text(),'" + bankName + "')] | //div[contains(text(),'" + bankName + "')]");
            WebElement bank = wait.until(ExpectedConditions.elementToBeClickable(bankOption));
            bank.click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new Exception("Failed to select bank: " + e.getMessage());
        }
    }

    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForPageLoad() throws Exception {
        // Increase wait when payment page opens (give login/OTP UI time to appear)
        Thread.sleep(5000);
    }
}
