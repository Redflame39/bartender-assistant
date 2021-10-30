package com.makichanov.bassistant.model.dao.impl;

import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.model.dao.ReviewDao;
import com.makichanov.bassistant.model.entity.Comment;
import com.makichanov.bassistant.model.entity.Review;
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

class ReviewDaoImplTest {

    private static Review review1;
    private static Review review2;
    private static Review review3;
    private static Comment comment1;
    private static Comment comment2;
    @Mock
    private static ReviewDao reviewDao;
    private MockitoSession session;

    @BeforeAll
    public static void init() {
        review1 = new Review.ReviewBuilder()
                .setId(1)
                .setUserId(5)
                .setCocktailId(100)
                .setComment("Good")
                .setRate(4)
                .createReview();
        review2 = new Review.ReviewBuilder()
                .setId(2)
                .setUserId(5)
                .setCocktailId(45)
                .setComment("Great")
                .setRate(5)
                .createReview();
        review3 = new Review.ReviewBuilder()
                .setId(3)
                .setUserId(10)
                .setCocktailId(100)
                .setComment("Bad")
                .setRate(2)
                .createReview();
        comment1= new Comment.CommentBuilder()
                .setReviewId(1)
                .setCommentText("Good")
                .setRate(4)
                .setAuthorId(5)
                .setAuthorName("AuthorName")
                .setAuthorImage("Image")
                .createComment();
        comment2= new Comment.CommentBuilder()
                .setReviewId(3)
                .setCommentText("Bad")
                .setRate(2)
                .setAuthorId(10)
                .setAuthorName("Username")
                .setAuthorImage("Image")
                .createComment();
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
        List<Review> reviews = List.of(review1, review2, review3);
        when(reviewDao.findAll(anyInt(), anyInt()))
                .thenReturn(reviews);
        List<Review> expected = List.of(review1, review2, review3);
        List<Review> actual = reviewDao.findAll(10, 3);
        assertEquals(expected, actual);
    }

    @Test
    void findAllCommentsTest() throws DaoException {
        List<Comment> comments = List.of(comment1, comment2);
        when(reviewDao.findAllComments(100))
                .thenReturn(comments);
        List<Comment> expected = List.of(comment1, comment2);
        List<Comment> actual = reviewDao.findAllComments(100);
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() throws DaoException {
        Optional<Review> review = Optional.of(review1);
        when(reviewDao.findById(1))
                .thenReturn(review);
        Optional<Review> expected = Optional.of(review1);
        Optional<Review> actual = reviewDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void createReviewTest() throws DaoException {
        when(reviewDao.create(anyInt(), anyInt(), anyString(), anyInt()))
                .thenReturn(true);
        boolean created = reviewDao.create(1, 2, "3", 4);
        assertTrue(created);
    }

    @Test
    void removeReviewTest() throws DaoException {
        when(reviewDao.remove(anyInt()))
                .thenReturn(true);
        boolean removed = reviewDao.remove(1);
        assertTrue(removed);
    }

    @Test
    void updateReviewTest() throws DaoException {
        when(reviewDao.update(anyInt(), eq(review1)))
                .thenReturn(review2);
        Review expected = review2;
        Review actual = reviewDao.update(2, review1);
        assertEquals(expected, actual);
    }

    @Test
    void countUserCocktailReviewsTest() throws DaoException {
        when(reviewDao.countUserCocktailReviews(5, 100))
                .thenReturn(2);
        int expected = 2;
        int actual = reviewDao.countUserCocktailReviews(5, 100);
        assertEquals(expected, actual);
    }
}