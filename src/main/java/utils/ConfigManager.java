package utils;

/**
 * ConfigManager - Centralized configuration management for the test suite
 * Handles environment-specific settings and properties
 */
public class ConfigManager {

    // Browser Configuration
    public static final String BROWSER = System.getProperty("browser", "chrome");
    public static final boolean HEADLESS_MODE = Boolean.parseBoolean(
        System.getProperty("headless", "false")
    );
    public static final boolean MAXIMIZE_WINDOW = Boolean.parseBoolean(
        System.getProperty("maximize", "true")
    );

    // Environment Configuration
    public static final String ENVIRONMENT = System.getProperty("env", "staging");
    public static final String BASE_URL = getBaseUrl(ENVIRONMENT);
    public static final String PAYMENT_URL = BASE_URL + "/payment/make-payment?origin=PAYMENT";

    // Timeout Configuration
    public static final int IMPLICIT_WAIT = Integer.parseInt(
        System.getProperty("implicitWait", "10")
    );
    public static final int EXPLICIT_WAIT = Integer.parseInt(
        System.getProperty("explicitWait", "10")
    );
    public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(
        System.getProperty("pageLoadTimeout", "30")
    );

    // Logging Configuration
    public static final boolean DETAILED_LOGGING = Boolean.parseBoolean(
        System.getProperty("logging", "true")
    );
    public static final boolean PERFORMANCE_TRACKING = Boolean.parseBoolean(
        System.getProperty("tracking", "false")
    );

    // Screenshot Configuration
    public static final boolean SCREENSHOT_ON_FAILURE = Boolean.parseBoolean(
        System.getProperty("screenshot", "true")
    );
    public static final boolean SCREENSHOT_ON_SUCCESS = Boolean.parseBoolean(
        System.getProperty("screenshotSuccess", "false")
    );

    // Retry Configuration
    public static final int MAX_RETRIES = Integer.parseInt(
        System.getProperty("retries", "2")
    );
    public static final boolean RETRY_ENABLED = Boolean.parseBoolean(
        System.getProperty("retryEnabled", "true")
    );

    // Parallel Execution
    public static final boolean PARALLEL_EXECUTION = Boolean.parseBoolean(
        System.getProperty("parallel", "false")
    );
    public static final int THREAD_COUNT = Integer.parseInt(
        System.getProperty("threads", "1")
    );

    // Test Data Configuration
    public static final boolean USE_TEST_DATA = Boolean.parseBoolean(
        System.getProperty("testData", "true")
    );

    /**
     * Get base URL based on environment
     */
    private static String getBaseUrl(String env) {
        switch (env.toLowerCase()) {
            case "production":
            case "prod":
                return "https://www.timesprime.com";
            case "staging":
                return "https://staging.timesprime.com";
            case "qa":
                return "https://qa.timesprime.com";
            case "local":
                return "http://localhost:3000";
            default:
                return "https://www.timesprime.com";
        }
    }

    /**
     * Print all configuration
     */
    public static void printConfiguration() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    Test Configuration Summary          ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        System.out.println("🌐 Environment Settings:");
        System.out.println("  • Environment: " + ENVIRONMENT);
        System.out.println("  • Base URL: " + BASE_URL);
        System.out.println("  • Payment URL: " + PAYMENT_URL);

        System.out.println("\n🖥️  Browser Settings:");
        System.out.println("  • Browser: " + BROWSER);
        System.out.println("  • Headless Mode: " + HEADLESS_MODE);
        System.out.println("  • Maximize Window: " + MAXIMIZE_WINDOW);

        System.out.println("\n⏱️  Timeout Settings:");
        System.out.println("  • Implicit Wait: " + IMPLICIT_WAIT + " seconds");
        System.out.println("  • Explicit Wait: " + EXPLICIT_WAIT + " seconds");
        System.out.println("  • Page Load Timeout: " + PAGE_LOAD_TIMEOUT + " seconds");

        System.out.println("\n📸 Screenshot Settings:");
        System.out.println("  • Screenshot on Failure: " + SCREENSHOT_ON_FAILURE);
        System.out.println("  • Screenshot on Success: " + SCREENSHOT_ON_SUCCESS);

        System.out.println("\n🔄 Retry Settings:");
        System.out.println("  • Max Retries: " + MAX_RETRIES);
        System.out.println("  • Retry Enabled: " + RETRY_ENABLED);

        System.out.println("\n⚡ Execution Settings:");
        System.out.println("  • Parallel Execution: " + PARALLEL_EXECUTION);
        System.out.println("  • Thread Count: " + THREAD_COUNT);
        System.out.println("  • Detailed Logging: " + DETAILED_LOGGING);
        System.out.println("  • Performance Tracking: " + PERFORMANCE_TRACKING);

        System.out.println("\n════════════════════════════════════════\n");
    }

    /**
     * Get browser configuration
     */
    public static String getBrowserConfig() {
        StringBuilder config = new StringBuilder();
        config.append("Browser: ").append(BROWSER).append(" | ");
        config.append("Headless: ").append(HEADLESS_MODE).append(" | ");
        config.append("Max Window: ").append(MAXIMIZE_WINDOW);
        return config.toString();
    }

    /**
     * Get timeout configuration
     */
    public static String getTimeoutConfig() {
        return String.format("Implicit: %ds | Explicit: %ds | PageLoad: %ds",
            IMPLICIT_WAIT, EXPLICIT_WAIT, PAGE_LOAD_TIMEOUT);
    }

    /**
     * Get retry configuration
     */
    public static String getRetryConfig() {
        return String.format("Retry: %s | Max: %d times",
            RETRY_ENABLED ? "ENABLED" : "DISABLED", MAX_RETRIES);
    }

    /**
     * Get execution configuration
     */
    public static String getExecutionConfig() {
        return String.format("Mode: %s | Threads: %d",
            PARALLEL_EXECUTION ? "PARALLEL" : "SEQUENTIAL", THREAD_COUNT);
    }

    /**
     * Validate configuration
     */
    public static boolean validateConfiguration() {
        System.out.println("\n🔍 Validating Configuration...");

        // Validate environment
        if (!isValidEnvironment(ENVIRONMENT)) {
            System.out.println("❌ Invalid environment: " + ENVIRONMENT);
            return false;
        }

        // Validate browser
        if (!isValidBrowser(BROWSER)) {
            System.out.println("❌ Invalid browser: " + BROWSER);
            return false;
        }

        // Validate timeouts
        if (IMPLICIT_WAIT <= 0 || EXPLICIT_WAIT <= 0) {
            System.out.println("❌ Invalid timeout values");
            return false;
        }

        // Validate thread count
        if (THREAD_COUNT <= 0) {
            System.out.println("❌ Invalid thread count: " + THREAD_COUNT);
            return false;
        }

        System.out.println("✅ Configuration validation passed\n");
        return true;
    }

    /**
     * Check if environment is valid
     */
    private static boolean isValidEnvironment(String env) {
        return env.equalsIgnoreCase("production") ||
               env.equalsIgnoreCase("staging") ||
               env.equalsIgnoreCase("qa") ||
               env.equalsIgnoreCase("local");
    }

    /**
     * Check if browser is valid
     */
    private static boolean isValidBrowser(String browser) {
        return browser.equalsIgnoreCase("chrome") ||
               browser.equalsIgnoreCase("firefox") ||
               browser.equalsIgnoreCase("edge") ||
               browser.equalsIgnoreCase("safari");
    }

    /**
     * Get system information
     */
    public static void printSystemInfo() {
        System.out.println("\n📱 System Information:");
        System.out.println("  • OS: " + System.getProperty("os.name"));
        System.out.println("  • OS Version: " + System.getProperty("os.version"));
        System.out.println("  • Java Version: " + System.getProperty("java.version"));
        System.out.println("  • Java Vendor: " + System.getProperty("java.vendor"));
        System.out.println("  • User: " + System.getProperty("user.name"));
        System.out.println();
    }
}
