package cn.itcast.bookstore.category.web.servlet;

import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.bookstore.category.service.CategoryCervice;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by winsion on 2017/3/30.
 */
@WebServlet(name = "CategoryServlet")
public class CategoryServlet extends BaseServlet {

    CategoryCervice categoryCervice = new CategoryCervice();

    public String findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categories = categoryCervice.findAll();

        request.setAttribute("categorys",categories);

        return "f:/jsps/left.jsp";

    }

}
