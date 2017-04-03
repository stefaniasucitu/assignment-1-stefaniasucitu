package model.validation;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final int MIN_PASSWORD_LENGTH = 8;

    private final User user;
    private final List<String> errors;

    public UserValidator(User user) {
        this.user = user;
        errors = new ArrayList<>();
    }

    private static boolean containsDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate() {
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        return errors.isEmpty();
    }

    public void validateUsername(String username) {
        if (!Pattern.compile(EMAIL_VALIDATION_REGEX).matcher(username).matches()) {
            errors.add("Invalid Email!");
        }
    }

    public void validatePassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            errors.add("Password too short!");
        }
        if (!containsSpecialCharacters(password)) {
            errors.add("Password must contain at least one special character");
        }
        if (!containsDigit(password)) {
            errors.add("Password must contain at least one digit!0");
        }

    }

    private boolean containsSpecialCharacters(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        return m.find();
    }

}
