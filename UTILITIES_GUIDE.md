# TimesPrime Payment Automation - Utilities Documentation

## 🛠️ Utilities Overview

The automation suite includes comprehensive utility classes to support testing, monitoring, and configuration management.

---

## 1️⃣ TestDataManager (255 lines)

**Location:** `src/main/java/utils/TestDataManager.java`

### Purpose
Centralized management of all test data used across test cases.

### Features
- ✅ Pre-defined test data constants
- ✅ Random data generators
- ✅ Test metadata storage
- ✅ Data summarization

### Available Test Data

#### UPI IDs
```java
// Valid UPIs
TestDataManager.VALID_UPI_1;        // "user@upi"
TestDataManager.VALID_UPI_2;        // "testuser@okhdfcbank"
TestDataManager.VALID_UPI_3;        // "automation@ibl"

// Invalid UPI
TestDataManager.INVALID_UPI;        // "invalid@upi"
```

#### Credit Cards
```java
TestDataManager.VALID_CREDIT_CARD;      // "4111111111111111"
TestDataManager.VALID_CREDIT_CARD_2;    // "4012888888881881"
TestDataManager.INVALID_CREDIT_CARD;    // "1234567890123456"
```

#### Debit Cards
```java
TestDataManager.VALID_DEBIT_CARD;       // "5555555555554444"
TestDataManager.VALID_DEBIT_CARD_2;     // "5105105105105100"
TestDataManager.INVALID_DEBIT_CARD;     // "5555555555555555"
```

#### Card Details
```java
TestDataManager.VALID_CARDHOLDER_NAME;      // "TEST USER"
TestDataManager.VALID_EXPIRY_DATE;          // "12/25"
TestDataManager.EXPIRED_EXPIRY_DATE;        // "01/20"
TestDataManager.VALID_CVV;                  // "123"
TestDataManager.INVALID_CVV;                // "000"
```

#### Mobile Numbers
```java
TestDataManager.VALID_MOBILE_1;     // "9999999999"
TestDataManager.VALID_MOBILE_2;     // "9876543210"
TestDataManager.INVALID_MOBILE;     // "12345"
```

#### Banks
```java
TestDataManager.HDFC_BANK;          // "HDFC Bank"
TestDataManager.ICICI_BANK;         // "ICICI Bank"
TestDataManager.SBI_BANK;           // "SBI"
TestDataManager.AXIS_BANK;          // "Axis Bank"
TestDataManager.KOTAK_BANK;         // "Kotak Mahindra Bank"
```

### Key Methods

#### Get Arrays
```java
String[] upiIds = TestDataManager.getValidUPIIds();
String[] cards = TestDataManager.getValidCreditCards();
String[] banks = TestDataManager.getBankNames();
String[] amounts = TestDataManager.getTestAmounts();
```

#### Random Data Generators
```java
String randomUPI = TestDataManager.getRandomValidUPI();
String randomCard = TestDataManager.getRandomValidCreditCard();
String randomBank = TestDataManager.getRandomBankName();
String randomAmount = TestDataManager.getRandomAmount();
```

#### Metadata Management
```java
TestDataManager.setTestMetadata("key", "value");
String value = TestDataManager.getTestMetadata("key");
TestDataManager.clearMetadata();
```

#### Summary Printing
```java
TestDataManager.printTestDataSummary();
// Output:
// === Test Data Summary ===
// Valid UPIs: 3
// Valid Credit Cards: 2
// Valid Debit Cards: 2
// ... etc
```

### Usage Example
```java
@Test
public void testPaymentWithRandomData() {
    String upi = TestDataManager.getRandomValidUPI();
    String amount = TestDataManager.getRandomAmount();
    
    paymentPage.selectUPI();
    paymentPage.enterUPIId(upi);
    paymentPage.enterAmount(amount);
    paymentPage.clickPay();
}
```

---

## 2️⃣ RetryAnalyzer (25 lines)

**Location:** `src/main/java/utils/RetryAnalyzer.java`

### Purpose
Automatically retries failed tests for handling flaky scenarios.

### Features
- ✅ Automatic test retry
- ✅ Configurable retry count
- ✅ Retry logging
- ✅ Max 2 retry attempts

### Configuration
```java
private static final int MAX_RETRY = 2; // Retry up to 2 times
```

### Usage in Test Class
```java
import org.testng.annotations.Test;

@Test(retryAnalyzer = RetryAnalyzer.class)
public void testUPIPayment() throws Exception {
    // Test code
    // If fails, automatically retried up to 2 times
}
```

### Output
```
⚠️  Test failed: testUPIPayment
🔄 Retrying... (Attempt 1 of 2)
```

---

## 3️⃣ ScreenshotUtil (100 lines)

**Location:** `src/main/java/utils/ScreenshotUtil.java`

### Purpose
Captures screenshots for debugging and test reporting.

### Features
- ✅ Screenshot capture with timestamp
- ✅ Failure screenshot capture
- ✅ Success screenshot capture
- ✅ Screenshot directory management
- ✅ Screenshot counting

### Directory Structure
```
TimesPrimeAutomation/
├── screenshots/
│   ├── testName_2025-11-13_13-30-45.png
│   ├── testName_FAILURE_2025-11-13_13-31-02.png
│   └── testName_SUCCESS_2025-11-13_13-31-15.png
```

### Key Methods

#### Capture Screenshots
```java
// Basic screenshot
ScreenshotUtil.captureScreenshot(driver, "testName");

// On failure
ScreenshotUtil.captureScreenshotOnFailure(driver, "testName");

// On success
ScreenshotUtil.captureScreenshotOnSuccess(driver, "testName");
```

#### Utility Methods
```java
String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "test");

String dir = ScreenshotUtil.getScreenshotDir();
int count = ScreenshotUtil.countScreenshots();
ScreenshotUtil.clearScreenshots();
```

### Usage in Test
```java
@Test
public void testCardPayment() {
    try {
        paymentPage.selectCreditCard();
        paymentPage.enterCardDetails(...);
        paymentPage.clickPay();
        ScreenshotUtil.captureScreenshot(driver, "CardPayment_Success");
    } catch (Exception e) {
        ScreenshotUtil.captureScreenshotOnFailure(driver, "CardPayment");
        throw e;
    }
}
```

---

## 4️⃣ PerformanceMonitor (194 lines)

**Location:** `src/main/java/utils/PerformanceMonitor.java`

### Purpose
Tracks and measures test execution performance metrics.

### Features
- ✅ Test execution timing
- ✅ Action counting
- ✅ Average time calculations
- ✅ Performance reporting
- ✅ Slowest/fastest test tracking

### Key Methods

#### Monitoring
```java
// Start monitoring
PerformanceMonitor.startMonitoring("testName");

// Perform test actions
Thread.sleep(1000);

// Stop monitoring and get duration
long durationMs = PerformanceMonitor.stopMonitoring("testName");
```

#### Duration Queries
```java
long ms = PerformanceMonitor.getDurationInMilliseconds("testName");
double seconds = PerformanceMonitor.getDurationInSeconds("testName");
```

#### Action Tracking
```java
PerformanceMonitor.incrementActionCount("cardEntered");
PerformanceMonitor.incrementActionCount("cardEntered");
int count = PerformanceMonitor.getActionCount("cardEntered"); // Returns 2
```

#### Performance Analysis
```java
String slowest = PerformanceMonitor.getSlowestTest();
String fastest = PerformanceMonitor.getFastestTest();
double average = PerformanceMonitor.getAverageTestDuration();
```

#### Reports
```java
// Print summary
PerformanceMonitor.printPerformanceSummary();

// Print action summary
PerformanceMonitor.printActionSummary();

// Get detailed metrics report
String report = PerformanceMonitor.getMetricsReport();
System.out.println(report);
```

### Usage Example
```java
@BeforeMethod
public void setup() {
    PerformanceMonitor.startMonitoring("UPIPaymentTest");
}

@Test
public void testUPIPayment() throws Exception {
    paymentPage.selectUPI();
    PerformanceMonitor.incrementActionCount("selectUPI");
    
    paymentPage.enterUPIId("user@upi");
    PerformanceMonitor.incrementActionCount("enterUPI");
    
    paymentPage.clickPay();
    PerformanceMonitor.incrementActionCount("clickPay");
}

@AfterMethod
public void teardown() {
    PerformanceMonitor.stopMonitoring("UPIPaymentTest");
}

@AfterClass
public void report() {
    PerformanceMonitor.printPerformanceSummary();
    System.out.println(PerformanceMonitor.getMetricsReport());
}
```

### Output Example
```
⏱️  Started: UPIPaymentTest at Wed Nov 13 13:30:00 2025
⏸️  Completed: UPIPaymentTest | Duration: 2345ms

╔════════════════════════════════════════╗
║      Performance Metrics Report        ║
╚════════════════════════════════════════╝

📊 Test Execution Times:
  • UPIPaymentTest: 2345 ms

⏱️  Average Duration: 2.35 sec
🐢 Slowest Test: UPIPaymentTest
🐇 Fastest Test: UPIPaymentTest
📈 Total Tests: 1
📉 Total Duration: 2.35 sec
```

---

## 5️⃣ ConfigManager (231 lines)

**Location:** `src/main/java/utils/ConfigManager.java`

### Purpose
Centralized configuration and environment management.

### Features
- ✅ Environment-based configuration
- ✅ Browser settings management
- ✅ Timeout configuration
- ✅ Feature toggles
- ✅ Configuration validation

### Configuration Properties

#### Environment Settings
```java
// Options: production, staging, qa, local
ConfigManager.ENVIRONMENT;          // Current environment
ConfigManager.BASE_URL;             // Environment-specific URL
ConfigManager.PAYMENT_URL;          // Payment page URL
```

#### Browser Settings
```java
ConfigManager.BROWSER;              // "chrome", "firefox", "edge"
ConfigManager.HEADLESS_MODE;        // true/false
ConfigManager.MAXIMIZE_WINDOW;      // true/false
```

#### Timeout Settings (seconds)
```java
ConfigManager.IMPLICIT_WAIT;        // 10
ConfigManager.EXPLICIT_WAIT;        // 10
ConfigManager.PAGE_LOAD_TIMEOUT;    // 30
```

#### Feature Toggles
```java
ConfigManager.SCREENSHOT_ON_FAILURE;    // true/false
ConfigManager.RETRY_ENABLED;            // true/false
ConfigManager.PARALLEL_EXECUTION;       // true/false
ConfigManager.PERFORMANCE_TRACKING;     // true/false
```

### Key Methods

#### Print Configuration
```java
ConfigManager.printConfiguration();
ConfigManager.printSystemInfo();
```

#### Configuration Strings
```java
String browser = ConfigManager.getBrowserConfig();
String timeout = ConfigManager.getTimeoutConfig();
String retry = ConfigManager.getRetryConfig();
String execution = ConfigManager.getExecutionConfig();
```

#### Validation
```java
boolean valid = ConfigManager.validateConfiguration();
```

### Environment Setup

Set via System Properties:
```bash
# Run with specific environment
mvn test -Denv=staging

# Headless mode
mvn test -Dheadless=true

# Disable retries
mvn test -DretryEnabled=false

# Parallel execution
mvn test -Dparallel=true -Dthreads=4
```

### Configuration File
```properties
# test.properties
environment=staging
browser=chrome
headless_mode=false
explicit_wait=10
screenshot_on_failure=true
retry_enabled=true
```

### Usage Example
```java
@BeforeClass
public void configSetup() {
    ConfigManager.printConfiguration();
    ConfigManager.printSystemInfo();
    ConfigManager.validateConfiguration();
}

@BeforeMethod
public void setup() {
    DesiredCapabilities caps = new DesiredCapabilities();
    
    // Use configuration settings
    if (ConfigManager.HEADLESS_MODE) {
        // Set headless options
    }
    
    if (ConfigManager.MAXIMIZE_WINDOW) {
        driver.manage().window().maximize();
    }
    
    // Set timeouts from config
    driver.manage().timeouts().implicitlyWait(
        Duration.ofSeconds(ConfigManager.IMPLICIT_WAIT)
    );
}
```

---

## 6️⃣ CI/CD Integration

**Location:** `.github/workflows/test.yml`

### Purpose
Automated testing on GitHub Actions.

### Features
- ✅ Java 21 LTS support
- ✅ Maven build and test
- ✅ Multiple test suites execution
- ✅ Screenshot and report collection
- ✅ Scheduled daily runs
- ✅ Artifact uploads

### Workflow Triggers
```yaml
# On push to main/develop
on:
  push:
    branches: [ main, develop ]

# On pull request
  pull_request:
    branches: [ main ]

# Daily at 2 AM UTC
  schedule:
    - cron: '0 2 * * *'
```

### Test Execution
```yaml
- Run all tests
- Run UPI Payment Tests
- Run Card Payment Tests
- Run Net Banking Tests
- Run Wallet & EMI Tests
- Run End-to-End Tests
```

### Artifacts Collected
- Screenshots (on failure)
- Test reports (HTML, XML)
- Build artifacts (JAR)

---

## 7️⃣ Test Configuration File

**Location:** `test.properties`

### Content
```properties
environment=staging
browser=chrome
headless_mode=false
implicit_wait=10
explicit_wait=10
page_load_timeout=30
screenshot_on_failure=true
retry_enabled=true
max_retries=2
parallel_execution=false
thread_count=1
```

### Loading Properties
```java
Properties props = new Properties();
props.load(new FileInputStream("test.properties"));
String env = props.getProperty("environment");
```

---

## 📊 Combined Utilities Usage Example

```java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void testCompletePaymentFlow() throws Exception {
    // Start performance monitoring
    PerformanceMonitor.startMonitoring("CompletePaymentFlow");
    
    try {
        // Use configuration
        System.out.println("Environment: " + ConfigManager.ENVIRONMENT);
        System.out.println("Browser: " + ConfigManager.BROWSER);
        
        // Use test data
        String upi = TestDataManager.getRandomValidUPI();
        String amount = TestDataManager.getRandomAmount();
        
        // Execute payment flow
        paymentPage.selectUPI();
        PerformanceMonitor.incrementActionCount("selectUPI");
        
        paymentPage.enterUPIId(upi);
        PerformanceMonitor.incrementActionCount("enterUPI");
        
        paymentPage.enterAmount(amount);
        PerformanceMonitor.incrementActionCount("enterAmount");
        
        paymentPage.clickPay();
        PerformanceMonitor.incrementActionCount("clickPay");
        
        // Capture screenshot on success
        if (ConfigManager.SCREENSHOT_ON_SUCCESS) {
            ScreenshotUtil.captureScreenshotOnSuccess(driver, "PaymentFlow");
        }
        
        Assert.assertTrue(paymentPage.isOTPScreenDisplayed());
        
    } catch (Exception e) {
        // Capture screenshot on failure
        if (ConfigManager.SCREENSHOT_ON_FAILURE) {
            ScreenshotUtil.captureScreenshotOnFailure(driver, "PaymentFlow");
        }
        throw e;
    } finally {
        // Stop monitoring and get duration
        long duration = PerformanceMonitor.stopMonitoring("CompletePaymentFlow");
        System.out.println("Test completed in: " + duration + "ms");
    }
}
```

---

## 🎯 Best Practices

1. **Use TestDataManager** for consistent test data
2. **Enable retries** for flaky elements
3. **Capture screenshots** on failures for debugging
4. **Monitor performance** to identify bottlenecks
5. **Validate configuration** before test execution
6. **Use CI/CD** for automated testing
7. **Track metrics** for optimization

---

## 📝 Summary

| Utility | Lines | Purpose |
|---------|-------|---------|
| TestDataManager | 255 | Test data management |
| PerformanceMonitor | 194 | Performance tracking |
| ConfigManager | 231 | Configuration management |
| ScreenshotUtil | 100 | Screenshot capture |
| RetryAnalyzer | 25 | Test retry logic |
| **Total** | **831** | **Comprehensive support** |

All utilities compile successfully with Java 21 and are production-ready! ✅
