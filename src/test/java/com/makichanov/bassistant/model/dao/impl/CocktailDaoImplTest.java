package com.makichanov.bassistant.model.dao.impl;

import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.model.dao.CocktailDao;
import com.makichanov.bassistant.model.entity.Cocktail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CocktailDaoImplTest {

    private static Cocktail cocktailTest;
    private static Cocktail cocktailWhiteRussian;
    private static Cocktail cocktailBloodyMary;
    private static Cocktail cocktailUnapproved;
    @Mock
    private CocktailDao cocktailDao;
    private MockitoSession session;

    @BeforeAll
    public static void init() {
        cocktailTest = new Cocktail.CocktailBuilder()
                .setName("qwe")
                .setId(1)
                .setUserId(5)
                .setInstructions("sample instructions")
                .setImageSource("picture.png")
                .setAverageMark(4.5)
                .createCocktail();
        cocktailWhiteRussian = new Cocktail.CocktailBuilder()
                .setName("White Russian")
                .setId(2)
                .setUserId(5)
                .setInstructions("Make with love.")
                .setImageSource("WhiteRussian.jpg")
                .setAverageMark(4.7)
                .createCocktail();
        cocktailBloodyMary = new Cocktail.CocktailBuilder()
                .setName("Bloody Mary")
                .setId(3)
                .setUserId(10)
                .setInstructions("Hard and spicy.")
                .setImageSource("BloodyMary.jpg")
                .setAverageMark(3.8)
                .createCocktail();
        cocktailUnapproved = new Cocktail.CocktailBuilder()
                .setName("Unapproved")
                .setId(4)
                .setUserId(15)
                .setInstructions("Why so?")
                .setImageSource("")
                .setAverageMark(0)
                .createCocktail();
    }

    @BeforeEach
    public void beforeMethod() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @AfterEach
    public void afterMethod() {
        session.finishMocking();
    }

    @Test
    void findAllTest() throws DaoException {
        List<Cocktail> cocktails = List.of(cocktailTest, cocktailWhiteRussian, cocktailBloodyMary);
        when(cocktailDao.findAll(anyInt(), eq(3)))
                .thenReturn(cocktails);
        List<Cocktail> expected = List.of(cocktailTest, cocktailWhiteRussian, cocktailBloodyMary);
        List<Cocktail> actual = cocktailDao.findAll(10, 3);
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() throws DaoException {
        Optional<Cocktail> cocktail = Optional.of(cocktailWhiteRussian);
        when(cocktailDao.findById(argThat(arg -> arg == cocktailWhiteRussian.getId())))
                .thenReturn(cocktail);
        Optional<Cocktail> expected = Optional.of(cocktailWhiteRussian);
        Optional<Cocktail> actual = cocktailDao.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void findByUserIDTest() throws DaoException {
        List<Cocktail> cocktails = List.of(cocktailTest, cocktailWhiteRussian);
        when(cocktailDao.findByUserID(eq(2), anyInt(), eq(2)))
                .thenReturn(cocktails);
        List<Cocktail> expected = List.of(cocktailTest, cocktailWhiteRussian);
        List<Cocktail> actual = cocktailDao.findByUserID(2, 10, 2);
        assertEquals(expected, actual);
    }

    @Test
    void findByNameRegexpTest() throws DaoException {
        List<Cocktail> cocktails = List.of(cocktailWhiteRussian);
        when(cocktailDao.findByNameRegexp("White"))
                .thenReturn(cocktails);
        List<Cocktail> expected = List.of(cocktailWhiteRussian);
        List<Cocktail> actual = cocktailDao.findByNameRegexp("White");
        assertEquals(expected, actual);
    }

    @Test
    void findAllUnapprovedCocktailsTest() throws DaoException {
        List<Cocktail> cocktails = List.of(cocktailUnapproved);
        when(cocktailDao.findAllUnapprovedCocktails(anyInt(), anyInt()))
                .thenReturn(cocktails);
        List<Cocktail> expected = List.of(cocktailUnapproved);
        List<Cocktail> actual = cocktailDao.findAllUnapprovedCocktails(10, 10);
        assertEquals(expected, actual);
    }

    @Test
    void findByNameTest() throws DaoException {
        Optional<Cocktail> cocktail = Optional.of(cocktailBloodyMary);
        when(cocktailDao.findByName(cocktailBloodyMary.getName()))
                .thenReturn(cocktail);
        Optional<Cocktail> expected = Optional.of(cocktailBloodyMary);
        Optional<Cocktail> actual = cocktailDao.findByName("Bloody Mary");
        assertEquals(expected, actual);
    }

    @Test
    void createTest() throws DaoException {
        when(cocktailDao.create(anyString(), anyInt(), anyString(), anyBoolean()))
                .thenReturn(true);
        boolean created = cocktailDao.create("Cocktail", 5, "qwe", false);
        assertTrue(created);
    }

    @Test
    void removeTest() throws DaoException {
        when(cocktailDao.remove(anyInt()))
                .thenReturn(true);
        boolean created = cocktailDao.remove(1);
        assertTrue(created);
    }

    @Test
    void updateTest() throws DaoException {
        when(cocktailDao.update(anyInt(), eq(cocktailTest)))
                .thenReturn(cocktailWhiteRussian);
        Cocktail expected = cocktailWhiteRussian;
        Cocktail actual = cocktailDao.update(2, cocktailTest);
        assertEquals(expected, actual);
    }

    @Test
    void updateImageTest() throws DaoException {
        when(cocktailDao.updateImage(anyInt(), anyString()))
                .thenReturn(true);
        boolean updated = cocktailDao.updateImage(2, "newPicture.png");
        assertTrue(updated);
    }

    @Test
    void updateApprovedStatusTest() throws DaoException {
        when(cocktailDao.updateApprovedStatus(anyInt(), anyBoolean()))
                .thenReturn(true);
        boolean updated = cocktailDao.updateApprovedStatus(2, true);
        assertTrue(updated);
    }

    @Test
    void countTotalCocktailsTest() throws DaoException {
        when(cocktailDao.countTotalCocktails())
                .thenReturn(OptionalInt.of(4));
        int expected = 4;
        int actual = cocktailDao.countTotalCocktails().getAsInt();
        assertEquals(expected, actual);
    }

    @Test
    void countCocktailsByUserIdTest() throws DaoException {
        when(cocktailDao.countCocktailsByUserId(5))
                .thenReturn(OptionalInt.of(2));
        int expected = 2;
        int actual = cocktailDao.countCocktailsByUserId(5).getAsInt();
        assertEquals(expected, actual);
    }

    @Test
    void countUnapprovedCocktails() throws DaoException {
        when(cocktailDao.countUnapprovedCocktails())
                .thenReturn(OptionalInt.of(1));
        int expected = 1;
        int actual = cocktailDao.countUnapprovedCocktails().getAsInt();
        assertEquals(expected, actual);
    }
}