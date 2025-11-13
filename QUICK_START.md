# Quick Start Guide - TimesPrime Payment Automation

## 🚀 5-Minute Setup

### Step 1: Verify Java 21 Installation
```bash
java -version
# Output should show: openjdk version "21.0.9"
```

If not installed:
```bash
brew install openjdk@21
export JAVA_HOME=/usr/local/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
```

### Step 2: Build Project
```bash
cd /Users/rakesh.jupalli/Downloads/TimesPrimeAutomation
export JAVA_HOME=/usr/local/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
mvn clean install
```

### Step 3: Run Tests
```bash
# Run all payment tests
mvn clean test

# Run specific payment flow
mvn clean test -Dtest=UPIPaymentFlowTest
mvn clean test -Dtest=CardPaymentFlowTest
mvn clean test -Dtest=NetBankingPaymentFlowTest

# Run specific test
mvn clean test -Dtest=UPIPaymentFlowTest#testUPIPaymentFlow
```

### Step 4: View Results
```bash
# Generate report
mvn surefire-report:report

# View report
open target/site/surefire-report.html
```

## 📋 Available Test Suites

| Suite | Command | Tests | Coverage |
|-------|---------|-------|----------|
| UPI | `mvn test -Dtest=UPIPaymentFlowTest` | 4 | UPI payments, OTP, invalid UPI |
| Card | `mvn test -Dtest=CardPaymentFlowTest` | 6 | Credit/Debit, validation, errors |
| Net Banking | `mvn test -Dtest=NetBankingPaymentFlowTest` | 4 | Multiple banks, retry, timeout |
| Wallet/EMI | `mvn test -Dtest=WalletAndEMIPaymentFlowTest` | 6 | Wallet, EMI, availability |
| End-to-End | `mvn test -Dtest=EndToEndPaymentFlowTest` | 10 | Full flows, navigation, errors |
| All | `mvn clean test` | 31 | Complete payment automation |

## 🎯 Test Coverage

### Payment Methods Automated
- ✅ UPI (4 tests)
- ✅ Credit Card (3 tests)
- ✅ Debit Card (3 tests)
- ✅ Net Banking (4 tests)
- ✅ Wallet (3 tests)
- ✅ EMI (2 tests)
- ✅ End-to-End Flows (10 tests)

### Scenarios Covered
- ✅ Valid payment flows
- ✅ Invalid card numbers
- ✅ Expired cards
- ✅ Invalid CVV
- ✅ OTP verification
- ✅ Multiple payment attempts
- ✅ Bank selection
- ✅ Wallet balance
- ✅ Error handling
- ✅ Page navigation

## 📁 Project Structure

```
TimesPrimeAutomation/
├── src/main/java/pages/
│   ├── HomePage.java          # Home page navigation
│   ├── LoginPage.java         # Login/OTP entry
│   └── PaymentPage.java       # All payment methods
├── src/test/java/tests/
│   ├── UPIPaymentFlowTest.java
│   ├── CardPaymentFlowTest.java
│   ├── NetBankingPaymentFlowTest.java
│   ├── WalletAndEMIPaymentFlowTest.java
│   └── EndToEndPaymentFlowTest.java
├── pom.xml                    # Java 21 + Dependencies
├── testng.xml                 # Test configuration
└── src/main/java/utils/
    └── BaseTest.java          # WebDriver setup
```

## 🔧 Common Commands

### Build & Compile
```bash
mvn clean compile           # Compile source code
mvn clean test-compile      # Compile tests
mvn clean install           # Build entire project
```

### Run Tests
```bash
mvn test                    # All tests
mvn test -X                 # With debug info
mvn test -DsuiteXmlFile=testng.xml  # Via TestNG suite
```

### Generate Reports
```bash
mvn surefire-report:report
mvn site:deploy
```

### Clean & Reset
```bash
mvn clean                   # Remove target directory
mvn dependency:tree         # Show dependencies
mvn versions:display-dependency-updates  # Check updates
```

## 🐛 Troubleshooting

### Issue: "Java version mismatch"
```bash
export JAVA_HOME=/usr/local/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
```

### Issue: "No such element"
- Update XPath locators in `PaymentPage.java`
- Check if page UI has changed

### Issue: "Timeout waiting for element"
- Increase wait time in PaymentPage constructor
- Change `Duration.ofSeconds(10)` to `Duration.ofSeconds(20)`

### Issue: "Chrome driver not found"
- WebDriver Manager auto-downloads driver
- Or manually set: `-Dwebdriver.chrome.driver=/path/to/chromedriver`

### Issue: "Cannot find tests"
```bash
mvn clean test-compile      # Recompile tests
mvn test                    # Run again
```

## 📊 Test Reports Location

After running tests:
```
target/surefire-reports/
├── emailable-report.html      # Email-friendly report
├── index.html                 # Main HTML report
├── testng-results.xml         # XML results
└── TimesPrimeSuite/
    └── PaymentFlowTest.html   # Detailed test report
```

## 💡 Tips & Tricks

### Run with verbose output
```bash
mvn test -e                 # Show full stacktrace
mvn test -X                 # Debug mode
```

### Run single test class
```bash
mvn test -Dtest=UPIPaymentFlowTest
```

### Skip tests during build
```bash
mvn install -DskipTests
```

### Run tests in parallel
Edit `pom.xml`:
```xml
<parallel>methods</parallel>
<threadCount>4</threadCount>
```

### Set custom timeout
```bash
mvn test -Dtimeout=60000
```

## 📖 Documentation

- **Full Guide**: `PAYMENT_AUTOMATION_GUIDE.md`
- **Implementation Details**: `IMPLEMENTATION_SUMMARY.md`
- **This Guide**: `QUICK_START.md`

## ✨ Key Features

✅ Java 21 LTS
✅ Selenium 4.20.0
✅ TestNG 7.9.0
✅ 31 Test Cases
✅ 6 Payment Methods
✅ Page Object Model
✅ Explicit Waits
✅ Error Handling
✅ Comprehensive Logging
✅ Ready for CI/CD

## 🔄 Sample Execution Flow

```
1. Clean build
   └─ mvn clean install

2. Run specific suite
   └─ mvn test -Dtest=UPIPaymentFlowTest

3. Test execution
   └─ Launches Chrome
   └─ Navigates to payment page
   └─ Selects payment method
   └─ Enters payment details
   └─ Verifies completion

4. Generate report
   └─ mvn surefire-report:report

5. View results
   └─ open target/site/surefire-report.html
```

## 📞 Need Help?

1. Check `PAYMENT_AUTOMATION_GUIDE.md` for detailed documentation
2. Review test code comments in test classes
3. Check `PaymentPage.java` for available methods
4. Verify Java 21 is installed: `java -version`
5. Ensure Chrome browser is installed
6. Check Maven version: `mvn -version`

## 🎉 You're Ready!

Your TimesPrime payment automation suite is ready to use:

1. All payment flows automated ✅
2. Java 21 compatible ✅
3. Tests compiled successfully ✅
4. Ready for execution ✅

**Start testing with:**
```bash
export JAVA_HOME=/usr/local/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
mvn clean test
```

---

**Updated**: November 13, 2025
**Java**: 21 LTS
**Status**: Ready for Production ✅
