# TimesPrime Payment Automation Suite

## Overview
This is a comprehensive Selenium-based test automation suite for the TimesPrime payment page. It automates all payment flows and validates various payment scenarios on the platform.

**Payment Page URL:** https://www.timesprime.com/payment/make-payment?origin=PAYMENT

## Java Runtime
- **Java Version:** 21 (LTS)
- **Maven Version:** Latest
- **Selenium Version:** 4.20.0
- **TestNG Version:** 7.9.0

## Project Structure

```
TimesPrimeAutomation/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/
│   │       │   ├── HomePage.java          # Home page interactions
│   │       │   ├── LoginPage.java         # Login page interactions
│   │       │   └── PaymentPage.java       # Payment page with all payment methods
│   │       └── utils/
│   │           └── BaseTest.java          # Base test setup with WebDriver configuration
│   └── test/
│       └── java/
│           └── tests/
│               ├── PaymentFlowTest.java              # Original payment flow test
│               ├── UPIPaymentFlowTest.java           # UPI payment tests
│               ├── CardPaymentFlowTest.java          # Credit/Debit card tests
│               ├── NetBankingPaymentFlowTest.java    # Net banking tests
│               ├── WalletAndEMIPaymentFlowTest.java  # Wallet and EMI tests
│               └── EndToEndPaymentFlowTest.java      # Comprehensive E2E tests
├── pom.xml                # Maven configuration (Java 21 target)
├── testng.xml             # TestNG suite configuration
└── README.md              # This file
```

## Payment Methods Automated

### 1. **UPI Payment Flow** (`UPIPaymentFlowTest.java`)
   - ✓ Select UPI payment method
   - ✓ Enter UPI ID
   - ✓ Initiate payment
   - ✓ Handle OTP verification
   - ✓ Multiple UPI attempts
   - ✓ Invalid UPI error handling

   **Test Cases:**
   - `testUPIPaymentFlow()` - Basic UPI flow
   - `testUPIPaymentWithOTPVerification()` - UPI with OTP
   - `testUPIPaymentFlowWithInvalidUPI()` - Invalid UPI handling
   - `testMultipleUPIAttempts()` - Multiple UPI IDs

### 2. **Credit/Debit Card Payment Flow** (`CardPaymentFlowTest.java`)
   - ✓ Select card type (Credit/Debit)
   - ✓ Enter card details (number, holder name, expiry, CVV)
   - ✓ Save card option
   - ✓ Process payment
   - ✓ Invalid card handling
   - ✓ Expired card detection
   - ✓ Incorrect CVV validation

   **Test Cases:**
   - `testCreditCardPaymentFlow()` - Credit card payment
   - `testDebitCardPaymentFlow()` - Debit card payment
   - `testCardPaymentWithInvalidCardNumber()` - Invalid card number
   - `testCardPaymentWithExpiredCard()` - Expired card
   - `testCardPaymentWithIncorrectCVV()` - Invalid CVV
   - `testMultipleCardPaymentAttempts()` - Multiple cards

### 3. **Net Banking Payment Flow** (`NetBankingPaymentFlowTest.java`)
   - ✓ Select net banking method
   - ✓ Choose bank (HDFC, ICICI, SBI, Axis, Kotak, etc.)
   - ✓ Redirect to bank portal
   - ✓ Multiple bank support
   - ✓ Retry mechanism
   - ✓ Timeout handling

   **Test Cases:**
   - `testNetBankingPaymentFlow()` - Basic net banking
   - `testNetBankingWithMultipleBanks()` - Multiple bank options
   - `testNetBankingPaymentFlowWithRetry()` - Retry logic
   - `testNetBankingTimeoutHandling()` - Timeout scenarios

### 4. **Wallet and EMI Payment Flow** (`WalletAndEMIPaymentFlowTest.java`)
   - ✓ Wallet payment method
   - ✓ Check wallet balance
   - ✓ EMI payment options
   - ✓ Multiple EMI tenures
   - ✓ Payment method availability check

   **Test Cases:**
   - `testWalletPaymentFlow()` - Basic wallet payment
   - `testEMIPaymentFlow()` - Basic EMI option
   - `testMultipleWalletAttempts()` - Wallet retries
   - `testPaymentMethodAvailability()` - Check all methods
   - `testEMIWithMultipleOptions()` - Multiple EMI tenures
   - `testWalletBalanceDisplay()` - Wallet balance verification

### 5. **End-to-End Payment Flow Tests** (`EndToEndPaymentFlowTest.java`)
   - ✓ Complete flow from home page
   - ✓ Direct payment page navigation
   - ✓ Page refresh handling
   - ✓ Page validation
   - ✓ Multiple payment attempts
   - ✓ Back/Forward navigation
   - ✓ Error handling
   - ✓ Concurrency testing
   - ✓ Comprehensive validation

   **Test Cases:**
   - `testCompletePaymentFlowFromHomePage()` - Home to payment
   - `testPaymentPageDirectNavigation()` - Direct navigation
   - `testPaymentFlowWithPageRefresh()` - Refresh handling
   - `testPaymentFlowPageValidation()` - Page validation
   - `testMultiplePaymentAttempts()` - Multiple attempts
   - `testPaymentFlowWithBackNavigation()` - Navigation handling
   - `testPaymentFlowErrorHandling()` - Error scenarios
   - `testPaymentFlowConcurrency()` - High-speed iterations
   - `testPaymentFlowWithWait()` - Extended waits
   - `testPaymentFlowComprehensiveValidation()` - Full validation

### 6. **Original Payment Flow Test** (`PaymentFlowTest.java`)
   - Basic payment flow verification

## Key Features

### PaymentPage Class
The `PaymentPage.java` class provides comprehensive methods for all payment operations:

```java
// Payment Method Selection
selectUPI()
selectCreditCard()
selectDebitCard()
selectNetBanking()
selectWallet()
selectEMI()

// UPI Payment
enterUPIId(String upiId)

// Card Payment
enterCardNumber(String cardNumber)
enterCardHolderName(String holderName)
enterExpiry(String expiry)
enterCVV(String cvv)

// Common Actions
clickPay()
clickProceed()
enterAmount(String amount)

// OTP Verification
enterOTP(String otp)
verifyOTP()

// Verification Methods
isPaymentMethodsDisplayed()
isOTPScreenDisplayed()
isPaymentSuccessful()
getErrorMessage()
saveCard(boolean shouldSave)
selectBank(String bankName)
```

## Setup and Installation

### Prerequisites
- Java 21 (LTS) installed
- Maven 3.8.9+ installed
- Chrome browser installed
- Selenium WebDriver 4.20.0

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd TimesPrimeAutomation
   ```

2. **Install Java 21 (if not installed)**
   ```bash
   # macOS with Homebrew
   brew install openjdk@21
   
   # Set JAVA_HOME
   export JAVA_HOME=/usr/local/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
   ```

3. **Build the project**
   ```bash
   export JAVA_HOME=/usr/local/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
   mvn clean install
   ```

4. **Verify setup**
   ```bash
   mvn --version
   java -version
   ```

## Running Tests

### Run All Tests
```bash
export JAVA_HOME=/usr/local/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
mvn clean test
```

### Run Specific Test Suite
```bash
# UPI Payment Tests
mvn clean test -Dtest=UPIPaymentFlowTest

# Card Payment Tests
mvn clean test -Dtest=CardPaymentFlowTest

# Net Banking Tests
mvn clean test -Dtest=NetBankingPaymentFlowTest

# Wallet and EMI Tests
mvn clean test -Dtest=WalletAndEMIPaymentFlowTest

# End-to-End Tests
mvn clean test -Dtest=EndToEndPaymentFlowTest
```

### Run Specific Test Case
```bash
mvn clean test -Dtest=UPIPaymentFlowTest#testUPIPaymentFlow
```

### Run with TestNG Suite
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

## Test Report Generation

### Maven Surefire Report
After running tests, generate the report:
```bash
mvn surefire-report:report
```

Reports will be available in:
```
target/surefire-reports/
target/site/surefire-report.html
```

### TestNG Report
```
target/surefire-reports/emailable-report.html
target/surefire-reports/index.html
```

## Configuration

### Update Test Data
Edit the test files to add your test data:

```java
// UPI IDs
paymentPage.enterUPIId("your-upi@bank");

// Card Numbers (use test cards)
paymentPage.enterCardNumber("4111111111111111");

// Banks
paymentPage.selectBank("HDFC Bank");
```

### Adjust Wait Times
Edit `PaymentPage.java` to modify timeout durations:

```java
private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
```

### Update Locators
Update XPath expressions in page objects if UI changes:

```java
private By upiOption = By.xpath("//updated-xpath-here");
```

## Dependencies

- **Selenium Java:** 4.20.0
- **TestNG:** 7.9.0
- **WebDriver Manager:** 5.6.0
- **Maven Surefire Plugin:** 3.1.2

## Browser Support

- Chrome (tested with version 142+)
- WebDriver Manager automatically handles ChromeDriver

## Known Limitations

1. Some locators may need adjustment based on live page updates
2. OTP verification uses test values (000000)
3. Bank redirect flows may require manual intervention in live environment
4. Payment cannot be fully processed without valid credentials

## Troubleshooting

### Issue: "No such element" errors
**Solution:** Update XPath locators in PageObject classes to match current UI

### Issue: Timeout waiting for elements
**Solution:** Increase wait time in PaymentPage constructor:
```java
this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
```

### Issue: Chrome Driver not found
**Solution:** WebDriver Manager should auto-download, but manually specify:
```bash
-Dwebdriver.chrome.driver=/path/to/chromedriver
```

### Issue: Java version mismatch
**Solution:** Ensure Java 21 is set:
```bash
export JAVA_HOME=/usr/local/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
```

## Best Practices

1. **Use Explicit Waits:** Don't rely on fixed sleep times
2. **Handle Exceptions:** Try-catch blocks for optional elements
3. **Data Validation:** Always verify expected state after actions
4. **Logging:** Use System.out.println for test tracking
5. **Test Isolation:** Each test should be independent
6. **Clean Data:** Use refresh/navigate between tests

## Future Enhancements

- [ ] Add Extent Reports for better test reporting
- [ ] Implement Page Factory pattern
- [ ] Add database validation checks
- [ ] Implement parallel test execution
- [ ] Add performance metrics
- [ ] Integrate with CI/CD pipeline
- [ ] Add video recording for failures
- [ ] Implement cross-browser testing

## Contributing

1. Create a new branch for features
2. Follow existing code structure
3. Add appropriate test cases
4. Update this README with new features
5. Ensure all tests pass before submitting

## License

This project is licensed under the MIT License

## Contact

For issues or questions about the test suite:
- Create a GitHub issue
- Contact the automation team

## Changelog

### Version 1.0 (Current)
- Initial setup with Java 21
- Complete UPI payment flow automation
- Credit/Debit card payment automation
- Net Banking payment automation
- Wallet and EMI payment automation
- End-to-End comprehensive tests
- Full documentation

---

**Last Updated:** November 13, 2025
**Java Version:** 21 (LTS)
**Test Framework:** Selenium + TestNG
