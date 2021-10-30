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
        String firstName = "Ilya";
        boolean valid = validator.validateFirstName(firstName);
        assertTrue(valid);
    }

    @Test
    void validateLastName() {
        String lastName = "Valentine";
        boolean valid = validator.validateLastName(lastName);
        assertTrue(valid);
    }

    @Test
    void validateEmail() {
        String email = "example@email.by";
        boolean valid = validator.validateEmail(email);
        assertTrue(valid);
    }

    @Test
    void validateEmailIncorrectValue() {
        String email = "incorrect email";
        boolean valid = validator.validateEmail(email);
        assertFalse(valid);
    }

    @Test
    void validatePassword() {
        String password = "a(_1qEyu?q~";
        boolean valid = validator.validatePassword(password);
        assertTrue(valid);
    }

    @Test
    void validatePositiveInt() {
        String value = "17";
        boolean valid = validator.validatePositiveInt(value);
        assertTrue(valid);
    }

    @Test
    void validatePositiveIntIncorrectValue() {
        String value = "t5";
        boolean valid = validator.validatePositiveInt(value);
        assertFalse(valid);
    }

    @Test
    void validateCocktailName() {
        String cocktailName = "Old fashioned";
        boolean valid = validator.validateCocktailName(cocktailName);
        assertTrue(valid);
    }

    @Test
    void validateCocktailInstructions() {
        String cocktailInstructions = """
                Необходимые ингредиенты
                Бурбон Woodford Reserve 50 мл
                Ангостура биттер 1 мл
                Тростниковый сахар в кубиках 5 г
                Коктейльная вишня красная 5 г
                Апельсиновая цедра 1 шт
                Лед в кубиках 120 г
                Положи в рокс кубик тростникового сахара, пропитанный ангостурой биттером 1 дэш, и подави мадлером
                Наполни рокс кубиками льда
                Найлей бурбон 50 мл и аккуратно размешай коктейльной ложкой
                Укрась апельсиновой цедрой и коктейльной вишней на шпажке
                """;
        boolean valid = validator.validateCocktailInstructions(cocktailInstructions);
        assertTrue(valid);
    }

    @Test
    void validateCocktailInstructionsShortText() {
        String cocktailInstructions = "qqq";
        boolean valid = validator.validateCocktailInstructions(cocktailInstructions);
        assertFalse(valid);
    }

    @Test
    void validateCocktailInstructionsForbiddenSymbols() {
        String cocktailInstructions = "Sample [ instructions <>>";
        boolean valid = validator.validateCocktailInstructions(cocktailInstructions);
        assertFalse(valid);
    }

    @Test
    void validateUserRole() {
        String roleName = "bartender";
        boolean valid = validator.validateUserRole(roleName);
        assertTrue(valid);
    }

    @Test
    void validateUserRoleIncorrectValue() {
        String roleName = "immortal god";
        boolean valid = validator.validateUserRole(roleName);
        assertFalse(valid);
    }

    @Test
    void validateReviewText() {
        String cocktailReview = """
                Добавляю по 4 дэша обычной и апельсиновой ангостуры, вместо апельсина.
                """;
        boolean valid = validator.validateReviewText(cocktailReview);
        assertTrue(valid);
    }

    @Test
    void validateReviewMark() {
        String reviewMark = "5";
        boolean valid = validator.validateReviewMark(reviewMark);
        assertTrue(valid);
    }

    @Test
    void validateReviewMarkIncorrectValue() {
        String reviewMark = "qqq";
        boolean valid = validator.validateReviewMark(reviewMark);
        assertFalse(valid);
    }
}