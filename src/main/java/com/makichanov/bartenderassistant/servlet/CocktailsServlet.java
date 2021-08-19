package com.makichanov.bartenderassistant.servlet;

import java.io.*;
import java.util.List;

import com.makichanov.bartenderassistant.dao.CocktailDao;
import com.makichanov.bartenderassistant.dao.EntityTransaction;
import com.makichanov.bartenderassistant.dao.impl.CocktailDaoImpl;
import com.makichanov.bartenderassistant.entity.Cocktail;
import com.makichanov.bartenderassistant.exception.DaoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "cocktailsServlet", value = "/cocktails")
public class CocktailsServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        CocktailDao cocktailDao = new CocktailDaoImpl();
        List<Cocktail> cocktails = null;
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initTransaction(cocktailDao);
            cocktails = cocktailDao.findAll();
        } catch (DaoException e) {
            System.err.println("Ploho"); // TODO: 19.08.2021 replace with logger
        }
        request.setAttribute("cocktails", cocktails);
        getServletContext().getRequestDispatcher("/jsp/cocktails.jsp").forward(request, response);
    }

    public void destroy() {
    }
}