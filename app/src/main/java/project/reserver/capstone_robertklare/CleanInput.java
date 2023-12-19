package project.reserver.capstone_robertklare;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanInput {
    public static String sanitize(String string) {

        String regex = "[^a-zA-Z0-9\\s]";
        return string.replaceAll(regex, "");
    }

    public static String removeLetters(String string) {
        String regex = "\\D";
        return string.replaceAll(regex, "");
    }

    public static boolean isValidEmail(String email) {
        final String EMAIL_REGULAR_EXPRESSION = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (email == null) {
            return false;
        }
        Pattern p = Pattern.compile(EMAIL_REGULAR_EXPRESSION);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }

        final String PHONE_REGULAR_EXPRESSION = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

        Pattern p = Pattern.compile(PHONE_REGULAR_EXPRESSION);
        Matcher m = p.matcher(phone);
        return m.matches();
    }



}
