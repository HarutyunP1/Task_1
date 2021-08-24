package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
    public static String randomEmail() {
        String generatedString = RandomStringUtils.randomAlphabetic(8);
        String email = generatedString + "@gmail.com";
        return email;
    }
    public static String randomPassword() {
        String generatedString = RandomStringUtils.randomAlphanumeric(6);
        return generatedString;
    }
    public static String randomLastName() {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        return generatedString;
    }
    public static String randomFirstName() {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        return generatedString;
    }
}
