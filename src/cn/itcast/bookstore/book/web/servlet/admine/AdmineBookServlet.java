package cn.itcast.bookstore.book.web.servlet.admine;

import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.bookstore.category.service.CategoryCervice;
import cn.itcast.bookstore.category.web.servlet.CategoryServlet;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by winsion on 2017/4/7.
 */
@WebServlet(name = "AdmineBookServlet")
public class AdmineBookServlet extends BaseServlet {
    BookService bookService = new BookService();
    CategoryCervice categoryCervice = new CategoryCervice();

    public String findAllBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {

        List<Book> books = bookService.findAll();

        request.setAttribute("books", books);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    /*
    book详情
     */
    public String loadBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {

        Book book = bookService.load(request.getParameter("bid"));
        List<Category>  categories = categoryCervice.findAll();

        request.setAttribute("book", book);
        request.setAttribute("categories",categories);
        return "f:/adminjsps/admin/book/desc.jsp";
    }
}
