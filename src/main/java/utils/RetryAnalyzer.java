package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * RetryAnalyzer - Automatically retries failed tests
 * Useful for handling flaky tests due to network issues or timing problems
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 2; // Retry up to 2 times

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY) {
            System.out.println("\n⚠️  Test failed: " + result.getMethod().getMethodName());
            System.out.println("🔄 Retrying... (Attempt " + (retryCount + 1) + " of " + MAX_RETRY + ")\n");
            retryCount++;
            return true;
        }
        return false;
    }
}
