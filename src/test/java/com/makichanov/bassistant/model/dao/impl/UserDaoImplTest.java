package com.makichanov.bassistant.model.dao.impl;

import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.model.dao.UserDao;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDaoImplTest {

    private static User user1;
    private static User user2;
    private static User user3;

    @Mock
    private UserDao userDao;
    private MockitoSession session;

    @BeforeAll
    public static void init() {
        user1 = new User.UserBuilder()
                .setUserId(1)
                .setUsername("Username")
                .setRole(Role.BARTENDER)
                .setEmail("mail@mail")
                .setActivated(true)
                .setFirstName("Vladimir")
                .setLastName("Ivanov")
                .setCocktailsCreated(5)
                .setAverageCocktailsRate(4.4)
                .createUser();
        user2 = new User.UserBuilder()
                .setUserId(2)
                .setUsername("Redflame")
                .setRole(Role.CLIENT)
                .setEmail("qwe@qwe")
                .setActivated(true)
                .setFirstName("Aleksandr")
                .setLastName("Petrov")
                .setCocktailsCreated(2)
                .setAverageCocktailsRate(3.25)
                .createUser();
        user3 = new User.UserBuilder()
                .setUserId(3)
                .setUsername("Penetrator9000")
                .setRole(Role.CLIENT)
                .setEmail("random@mail")
                .setActivated(true)
                .setFirstName("Pavel")
                .setLastName("Efimov")
                .setCocktailsCreated(7)
                .setAverageCocktailsRate(4.1)
                .createUser();
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
    void findAll() throws DaoException {
        List<User> users = List.of(user1, user2, user3);
        when(userDao.findAll(anyInt(), eq(3)))
                .thenReturn(users);
        List<User> expected = List.of(user1, user2, user3);
        List<User> actual = userDao.findAll(10, 3);
        assertEquals(expected, actual);
    }

    @Test
    void findById() throws DaoException {
        Optional<User> user = Optional.of(user1);
        when(userDao.findById(2))
                .thenReturn(user);
        Optional<User> expected = Optional.of(user1);
        Optional<User> actual = userDao.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void findByUsername() throws DaoException {
        Optional<User> user = Optional.of(user2);
        when(userDao.findByUsername("Redflame"))
                .thenReturn(user);
        Optional<User> expected = Optional.of(user2);
        Optional<User> actual = userDao.findByUsername("Redflame");
        assertEquals(expected, actual);
    }

    @Test
    void findByEmail() throws DaoException {
        Optional<User> user = Optional.of(user2);
        when(userDao.findByEmail("qwe@qwe"))
                .thenReturn(user);
        Optional<User> expected = Optional.of(user2);
        Optional<User> actual = userDao.findByEmail("qwe@qwe");
    }
}