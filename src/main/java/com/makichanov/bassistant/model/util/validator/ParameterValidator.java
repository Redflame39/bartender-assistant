package com.makichanov.bassistant.model.util.validator;

public interface ParameterValidator {

    boolean validateUsername(String username);

    boolean validateFirstName(String firstName);

    boolean validateLastName(String lastName);

    boolean validateEmail(String email);

    boolean validatePassword(String password);

    boolean validateId(String id);

}
