# TimesPrime-Automation

Java + Selenium + TestNG Maven project to automate the TimesPrime payment journey until the OTP screen.

## Prerequisites

1. Java 17 (JDK)
2. Maven
3. Git (optional, for pushing to GitHub)
4. Chrome browser

## How to run

1. Open project in VS Code (File → Open Folder → `TimesPrimeAutomation`)
2. In terminal run:
   ```
   mvn clean test
   ```
3. The test will launch Chrome, perform the flow and assert OTP screen is reached.

## Notes

- WebDriverManager is included — you don't need a separate chromedriver binary.
- Update the mobile number in `PaymentFlowTest.java` if required.
- Record the run using OBS / screen recorder to create the requested video.

