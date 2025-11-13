package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * TestDataManager - Centralized test data management for payment automation
 * Provides all test data including card numbers, UPI IDs, and credentials
 */
public class TestDataManager {

    // UPI Test Data
    public static final String VALID_UPI_1 = "user@upi";
    public static final String VALID_UPI_2 = "testuser@okhdfcbank";
    public static final String VALID_UPI_3 = "automation@ibl";
    public static final String INVALID_UPI = "invalid@upi";
    public static final String EMPTY_UPI = "";

    // Credit Card Test Data
    public static final String VALID_CREDIT_CARD = "4111111111111111";
    public static final String VALID_CREDIT_CARD_2 = "4012888888881881";
    public static final String INVALID_CREDIT_CARD = "1234567890123456";
    public static final String EXPIRED_CREDIT_CARD = "4111111111111111"; // Same, but expired date

    // Debit Card Test Data
    public static final String VALID_DEBIT_CARD = "5555555555554444";
    public static final String VALID_DEBIT_CARD_2 = "5105105105105100";
    public static final String INVALID_DEBIT_CARD = "5555555555555555";

    // Card Details
    public static final String VALID_CARDHOLDER_NAME = "TEST USER";
    public static final String VALID_CARDHOLDER_NAME_2 = "AUTOMATION TEST";
    public static final String VALID_EXPIRY_DATE = "12/25";
    public static final String VALID_EXPIRY_DATE_2 = "06/26";
    public static final String EXPIRED_EXPIRY_DATE = "01/20";
    public static final String FUTURE_EXPIRY_DATE = "12/28";
    public static final String VALID_CVV = "123";
    public static final String VALID_CVV_2 = "456";
    public static final String INVALID_CVV = "000";
    public static final String INVALID_CVV_2 = "12";

    // Mobile Numbers
    public static final String VALID_MOBILE_1 = "9999999999";
    public static final String VALID_MOBILE_2 = "9876543210";
    public static final String VALID_MOBILE_3 = "8765432109";
    public static final String INVALID_MOBILE = "12345";
    public static final String EMPTY_MOBILE = "";

    // OTP Test Data
    public static final String TEST_OTP = "000000";
    public static final String VALID_OTP_1 = "123456";
    public static final String VALID_OTP_2 = "654321";
    public static final String INVALID_OTP = "999999";

    // Bank Names
    public static final String HDFC_BANK = "HDFC Bank";
    public static final String ICICI_BANK = "ICICI Bank";
    public static final String SBI_BANK = "SBI";
    public static final String AXIS_BANK = "Axis Bank";
    public static final String KOTAK_BANK = "Kotak Mahindra Bank";
    public static final String PUNJAB_NATIONAL_BANK = "PNB";
    public static final String BANK_OF_BARODA = "Bank of Baroda";

    // Amount Test Data
    public static final String SMALL_AMOUNT = "100";
    public static final String MEDIUM_AMOUNT = "1000";
    public static final String LARGE_AMOUNT = "10000";
    public static final String INVALID_AMOUNT = "-100";
    public static final String ZERO_AMOUNT = "0";

    // URLs
    public static final String PAYMENT_PAGE_URL = "https://www.timesprime.com/payment/make-payment?origin=PAYMENT";
    public static final String HOME_PAGE_URL = "https://www.timesprime.com";
    public static final String LOGIN_PAGE_URL = "https://www.timesprime.com/login";

    // Timeouts (in seconds)
    public static final int DEFAULT_WAIT_TIME = 10;
    public static final int SHORT_WAIT_TIME = 5;
    public static final int LONG_WAIT_TIME = 30;
    public static final int EXTENDED_WAIT_TIME = 60;

    // Test Metadata
    private static Map<String, String> testMetadata = new HashMap<>();

    /**
     * Get all valid UPI IDs for testing
     */
    public static String[] getValidUPIIds() {
        return new String[]{VALID_UPI_1, VALID_UPI_2, VALID_UPI_3};
    }

    /**
     * Get all valid credit card numbers
     */
    public static String[] getValidCreditCards() {
        return new String[]{VALID_CREDIT_CARD, VALID_CREDIT_CARD_2};
    }

    /**
     * Get all valid debit card numbers
     */
    public static String[] getValidDebitCards() {
        return new String[]{VALID_DEBIT_CARD, VALID_DEBIT_CARD_2};
    }

    /**
     * Get all cardholder names
     */
    public static String[] getCardholderNames() {
        return new String[]{VALID_CARDHOLDER_NAME, VALID_CARDHOLDER_NAME_2};
    }

    /**
     * Get all expiry dates (valid and invalid)
     */
    public static String[] getExpiryDates() {
        return new String[]{VALID_EXPIRY_DATE, VALID_EXPIRY_DATE_2, EXPIRED_EXPIRY_DATE};
    }

    /**
     * Get all CVV values (valid and invalid)
     */
    public static String[] getCVVValues() {
        return new String[]{VALID_CVV, VALID_CVV_2, INVALID_CVV};
    }

    /**
     * Get all mobile numbers
     */
    public static String[] getMobileNumbers() {
        return new String[]{VALID_MOBILE_1, VALID_MOBILE_2, VALID_MOBILE_3};
    }

    /**
     * Get all bank names
     */
    public static String[] getBankNames() {
        return new String[]{HDFC_BANK, ICICI_BANK, SBI_BANK, AXIS_BANK, KOTAK_BANK};
    }

    /**
     * Get all test amounts
     */
    public static String[] getTestAmounts() {
        return new String[]{SMALL_AMOUNT, MEDIUM_AMOUNT, LARGE_AMOUNT};
    }

    /**
     * Get random UPI ID from valid list
     */
    public static String getRandomValidUPI() {
        String[] upis = getValidUPIIds();
        return upis[(int) (Math.random() * upis.length)];
    }

    /**
     * Get random credit card from valid list
     */
    public static String getRandomValidCreditCard() {
        String[] cards = getValidCreditCards();
        return cards[(int) (Math.random() * cards.length)];
    }

    /**
     * Get random debit card from valid list
     */
    public static String getRandomValidDebitCard() {
        String[] cards = getValidDebitCards();
        return cards[(int) (Math.random() * cards.length)];
    }

    /**
     * Get random cardholder name
     */
    public static String getRandomCardholderName() {
        String[] names = getCardholderNames();
        return names[(int) (Math.random() * names.length)];
    }

    /**
     * Get random expiry date
     */
    public static String getRandomExpiryDate() {
        String[] dates = getExpiryDates();
        return dates[(int) (Math.random() * dates.length)];
    }

    /**
     * Get random CVV
     */
    public static String getRandomCVV() {
        String[] cvvs = getCVVValues();
        return cvvs[(int) (Math.random() * cvvs.length)];
    }

    /**
     * Get random mobile number
     */
    public static String getRandomMobileNumber() {
        String[] mobiles = getMobileNumbers();
        return mobiles[(int) (Math.random() * mobiles.length)];
    }

    /**
     * Get random bank name
     */
    public static String getRandomBankName() {
        String[] banks = getBankNames();
        return banks[(int) (Math.random() * banks.length)];
    }

    /**
     * Get random amount
     */
    public static String getRandomAmount() {
        String[] amounts = getTestAmounts();
        return amounts[(int) (Math.random() * amounts.length)];
    }

    /**
     * Store test metadata
     */
    public static void setTestMetadata(String key, String value) {
        testMetadata.put(key, value);
    }

    /**
     * Get test metadata
     */
    public static String getTestMetadata(String key) {
        return testMetadata.getOrDefault(key, "");
    }

    /**
     * Clear all metadata
     */
    public static void clearMetadata() {
        testMetadata.clear();
    }

    /**
     * Print all test data summary
     */
    public static void printTestDataSummary() {
        System.out.println("\n=== Test Data Summary ===");
        System.out.println("Valid UPIs: " + getValidUPIIds().length);
        System.out.println("Valid Credit Cards: " + getValidCreditCards().length);
        System.out.println("Valid Debit Cards: " + getValidDebitCards().length);
        System.out.println("Cardholder Names: " + getCardholderNames().length);
        System.out.println("Mobile Numbers: " + getMobileNumbers().length);
        System.out.println("Bank Names: " + getBankNames().length);
        System.out.println("Test Amounts: " + getTestAmounts().length);
        System.out.println("========================\n");
    }
}
