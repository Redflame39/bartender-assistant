package com.makichanov.bassistant.controller.util.validator.impl;

import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterValidatorTest {

    private static ParameterValidator validator;

    @BeforeAll
    static void beforeAll() {
        validator = ParameterValidatorImpl.getInstance();
    }

    @Test
    void validateUsernameTest() {
        String username = "Redflame";
        boolean valid = validator.validateUsername(username);
        assertTrue(valid);
    }

    @Test
    void validateFirstName() {
    }

    @Test
    void validateLastName() {
    }

    @Test
    void validateEmail() {
    }

    @Test
    void validatePassword() {
    }

    @Test
    void validatePositiveInt() {
    }

    @Test
    void validateCocktailName() {
    }

    @Test
    void validateCocktailInstructions() {
    }

    @Test
    void validateUserRole() {
    }

    @Test
    void validateReviewText() {
    }

    @Test
    void validateReviewMark() {
    }
}