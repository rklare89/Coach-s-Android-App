package project.reserver.capstone_robertklare;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanInputTest {

    @Test
    public void testSanitizeWithAlphanumericString() {
        String input = "abc123";
        String result = sanitize(input);
        assertEquals(input, result);
    }

    @Test
    public void testSanitizeWithSpecialCharacters() {

        String input = "!@#$%^&*()";
        String result = sanitize(input);
        assertEquals("", result);
    }

    @Test
    public void testSanitizeWithMixedCharacters() {

        String input = "abc123!@#";
        String result = sanitize(input);
        assertEquals("abc123", result);
    }

    @Test
    public void testSanitizeWithWhitespace() {

        String input = "  abc 123  ";
        String result = sanitize(input);
        assertEquals(input, result);
    }

    @Test
    public void testSanitizeWithNullInput() {

        String input = null;
        String result = sanitize(input);
        assertNull(result);
    }

    private String sanitize(String string) {
        String regex = "[^a-zA-Z0-9\\s]";
        return string == null ? null : string.replaceAll(regex, "");
    }

    @Test
    public void testRemoveLettersWithAlphanumericString() {
        String input = "abc123";
        String result = removeLetters(input);
        assertEquals("123", result);
    }

    @Test
    public void testRemoveLettersWithSpecialCharacters() {
        String input = "!@#$%^&*()";
        String result = removeLetters(input);
        assertEquals("", result);
    }

    @Test
    public void testRemoveLettersWithMixedCharacters() {
        String input = "abc123!@#";
        String result = removeLetters(input);
        assertEquals("123", result);
    }

    @Test
    public void testRemoveLettersWithWhitespace() {
        String input = "  abc 123  ";
        String result = removeLetters(input);
        assertEquals("123", result);
    }

    @Test
    public void testRemoveLettersWithNullInput() {
        String input = null;
        String result = removeLetters(input);
        assertNull(result);
    }

    private String removeLetters(String string) {
        String regex = "\\D";
        return string == null ? null : string.replaceAll(regex, "");
    }

    @Test
    public void testValidEmail() {
        String validEmail = "user@example.com";
        boolean result = isValidEmail(validEmail);
        assertTrue(result);
    }

    @Test
    public void testInvalidEmailMissingAtSymbol() {
        String invalidEmail = "userexample.com";
        boolean result = isValidEmail(invalidEmail);
        assertFalse(result);
    }

    @Test
    public void testInvalidEmailMissingDomain() {
        String invalidEmail = "user@.com";
        boolean result = isValidEmail(invalidEmail);
        assertFalse(result);
    }

    @Test
    public void testInvalidEmailMissingUsername() {
        String invalidEmail = "@example.com";
        boolean result = isValidEmail(invalidEmail);
        assertFalse(result);
    }

    @Test
    public void testInvalidEmailNullInput() {

        String nullEmail = null;
        boolean result = isValidEmail(nullEmail);
        assertFalse(result);
    }

    @Test
    public void testInvalidEmailEmptyInput() {

        String emptyEmail = "";
        boolean result = isValidEmail(emptyEmail);
        assertFalse(result);
    }

    @Test
    public void testInvalidEmailWhitespaceInput() {

        String whitespaceEmail = "   ";
        boolean result = isValidEmail(whitespaceEmail);
        assertFalse(result);
    }

    private static boolean isValidEmail(String email) {
        final String EMAIL_REGULAR_EXPRESSION = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (email == null) {
            return false;
        }
        Pattern p = Pattern.compile(EMAIL_REGULAR_EXPRESSION);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @Test
    public void testValidPhoneNumberWithDashes() {

        String validPhone = "123-456-7890";
        boolean result = CleanInput.isValidPhone(validPhone);
        assertTrue(result);
    }

    @Test
    public void testValidPhoneNumberWithDots() {

        String validPhone = "123.456.7890";
        boolean result = CleanInput.isValidPhone(validPhone);
        assertTrue(result);
    }

    @Test
    public void testValidPhoneNumberWithSpaces() {

        String validPhone = "123 456 7890";
        boolean result = CleanInput.isValidPhone(validPhone);
        assertTrue(result);
    }

    @Test
    public void testValidPhoneNumberWithParentheses() {

        String validPhone = "(123) 456-7890";
        boolean result = CleanInput.isValidPhone(validPhone);
        assertTrue(result);
    }

    @Test
    public void testInvalidPhoneNumberMissingDigits() {

        String invalidPhone = "12-456-789";
        boolean result = CleanInput.isValidPhone(invalidPhone);
        assertFalse(result);
    }

    @Test
    public void testInvalidPhoneNumberNullInput() {

        String nullPhone = null;
        boolean result = CleanInput.isValidPhone(nullPhone);
        assertFalse(result);
    }

    @Test
    public void testInvalidPhoneNumberEmptyInput() {

        String emptyPhone = "";
        boolean result = CleanInput.isValidPhone(emptyPhone);
        assertFalse(result);
    }

    @Test
    public void testInvalidPhoneNumberWhitespaceInput() {

        String whitespacePhone = "   ";
        boolean result = CleanInput.isValidPhone(whitespacePhone);
        assertFalse(result);
    }


}