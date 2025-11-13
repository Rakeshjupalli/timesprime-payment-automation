package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * PerformanceMonitor - Tracks and measures test execution performance
 * Collects timing metrics for optimization analysis
 */
public class PerformanceMonitor {

    private static Map<String, Long> startTimes = new HashMap<>();
    private static Map<String, Long> endTimes = new HashMap<>();
    private static Map<String, Long> durations = new HashMap<>();
    private static Map<String, Integer> actionCounts = new HashMap<>();

    /**
     * Start monitoring a test/action
     */
    public static void startMonitoring(String testName) {
        long startTime = System.currentTimeMillis();
        startTimes.put(testName, startTime);
        System.out.println("⏱️  Started: " + testName + " at " + new java.util.Date(startTime));
    }

    /**
     * Stop monitoring and record duration
     */
    public static long stopMonitoring(String testName) {
        long endTime = System.currentTimeMillis();
        endTimes.put(testName, endTime);
        
        Long startTime = startTimes.get(testName);
        if (startTime != null) {
            long duration = endTime - startTime;
            durations.put(testName, duration);
            System.out.println("⏸️  Completed: " + testName + " | Duration: " + duration + "ms");
            return duration;
        }
        return 0;
    }

    /**
     * Get duration in seconds
     */
    public static double getDurationInSeconds(String testName) {
        Long duration = durations.get(testName);
        return duration != null ? duration / 1000.0 : 0;
    }

    /**
     * Get duration in milliseconds
     */
    public static long getDurationInMilliseconds(String testName) {
        return durations.getOrDefault(testName, 0L);
    }

    /**
     * Track action count
     */
    public static void incrementActionCount(String actionName) {
        int count = actionCounts.getOrDefault(actionName, 0);
        actionCounts.put(actionName, count + 1);
    }

    /**
     * Get action count
     */
    public static int getActionCount(String actionName) {
        return actionCounts.getOrDefault(actionName, 0);
    }

    /**
     * Get average time per action
     */
    public static double getAverageTimePerAction(String testName, String actionName) {
        long duration = getDurationInMilliseconds(testName);
        int count = getActionCount(actionName);
        return count > 0 ? (double) duration / count : 0;
    }

    /**
     * Print performance summary
     */
    public static void printPerformanceSummary() {
        System.out.println("\n=== Performance Summary ===");
        
        if (durations.isEmpty()) {
            System.out.println("No performance data available");
            return;
        }

        long totalTime = 0;
        for (String testName : durations.keySet()) {
            long duration = durations.get(testName);
            totalTime += duration;
            System.out.println(String.format("%-40s: %7d ms (%.2f sec)", 
                testName, duration, duration / 1000.0));
        }

        System.out.println("---------------------------");
        System.out.println(String.format("%-40s: %7d ms (%.2f sec)", 
            "TOTAL", totalTime, totalTime / 1000.0));
        System.out.println("==========================\n");
    }

    /**
     * Print action summary
     */
    public static void printActionSummary() {
        System.out.println("\n=== Action Summary ===");
        
        if (actionCounts.isEmpty()) {
            System.out.println("No action data available");
            return;
        }

        int totalActions = 0;
        for (String action : actionCounts.keySet()) {
            int count = actionCounts.get(action);
            totalActions += count;
            System.out.println(action + ": " + count + " times");
        }

        System.out.println("------------------");
        System.out.println("Total Actions: " + totalActions);
        System.out.println("====================\n");
    }

    /**
     * Clear all monitoring data
     */
    public static void clearMonitoringData() {
        startTimes.clear();
        endTimes.clear();
        durations.clear();
        actionCounts.clear();
        System.out.println("✅ Performance monitoring data cleared");
    }

    /**
     * Get slowest test
     */
    public static String getSlowestTest() {
        return durations.entrySet().stream()
            .max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
            .map(Map.Entry::getKey)
            .orElse("N/A");
    }

    /**
     * Get fastest test
     */
    public static String getFastestTest() {
        return durations.entrySet().stream()
            .min((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
            .map(Map.Entry::getKey)
            .orElse("N/A");
    }

    /**
     * Get average test duration
     */
    public static double getAverageTestDuration() {
        if (durations.isEmpty()) return 0;
        long sum = durations.values().stream().mapToLong(Long::longValue).sum();
        return (double) sum / durations.size() / 1000; // In seconds
    }

    /**
     * Get all metrics as report
     */
    public static String getMetricsReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n╔════════════════════════════════════════╗\n");
        report.append("║      Performance Metrics Report        ║\n");
        report.append("╚════════════════════════════════════════╝\n\n");

        report.append("📊 Test Execution Times:\n");
        for (String test : durations.keySet()) {
            long duration = durations.get(test);
            report.append(String.format("  • %s: %d ms\n", test, duration));
        }

        report.append(String.format("\n⏱️  Average Duration: %.2f sec\n", getAverageTestDuration()));
        report.append(String.format("🐢 Slowest Test: %s\n", getSlowestTest()));
        report.append(String.format("🐇 Fastest Test: %s\n", getFastestTest()));
        report.append(String.format("📈 Total Tests: %d\n", durations.size()));
        report.append(String.format("📉 Total Duration: %.2f sec\n\n", 
            durations.values().stream().mapToLong(Long::longValue).sum() / 1000.0));

        return report.toString();
    }
}
