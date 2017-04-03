package model.validation;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Stefi on 02-Apr-17.
 */
public class ClientValidator {
    private static final String NAME_VALIDATION_REGEX = "^[a-zA-Z]+$";
    private static final int CNP_LENGTH = 13;
    private static final int CARD_NR_LENGTH = 10;

    private final Client client;
    private final List<String> errors;

    public ClientValidator(Client client) {
        this.client = client;
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate() {
        validateName(client.getFirstName());
        validateName(client.getLastName());
        validateCARD(client.getIdCard());
        validateCNP(client.getCNP());
        return errors.isEmpty();
    }


    public void validateName(String name) {
        if (!Pattern.compile(NAME_VALIDATION_REGEX).matcher(name).matches()) {
            errors.add("Invalid characters in name!");
        }
    }

    public void validateCNP(String cnp) {
        if (cnp.length() != CNP_LENGTH) {
            errors.add("CNP of invalid length!");
        }
    }


    public void validateCARD(String card) {
        if (card.length() != CARD_NR_LENGTH) {
            errors.add("Card number of invalid length!");
        }

    }
}
