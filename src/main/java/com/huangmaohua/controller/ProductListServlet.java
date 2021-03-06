package com.huangmaohua.controller;

import com.huangmaohua.dao.ProductDao;
import com.huangmaohua.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="ProductListServlet",value="/admin/productList")
public class ProductListServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        try {
            List<Product> productList= productDao.findAll(con);
            req.setAttribute("productList",productList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String path = "/WEB-INF/views/admin/productList.jsp";
        req.getRequestDispatcher(path).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
