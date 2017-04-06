package cn.itcast.bookstore.book.web.servlet;

import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by winsion on 2017/3/30.
 */
@WebServlet(name = "BookServlet")
public class BookServlet extends BaseServlet {

    BookService bookService = new BookService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {

        List<Book> books = null;
        try {
            books = bookService.findAll();
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            return "f:/jsps/body.jsp";
        }

        request.setAttribute("books", books);
        return "f:/jsps/book/list.jsp";
    }

    public String bookdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {

      Book book = bookService.load(request.getParameter("bid"));

        request.setAttribute("book", book);
        return "f:/jsps/book/desc.jsp";
    }

    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Book> books = null;
        try {
            books = bookService.findByCategory(request.getParameter("cid"));
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            return "f:/jsps/body.jsp";
        }

        request.setAttribute("books",books);
        return "f:/jsps/book/list.jsp";

    }

}