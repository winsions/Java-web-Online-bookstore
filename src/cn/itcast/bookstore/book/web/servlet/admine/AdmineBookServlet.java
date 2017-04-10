package cn.itcast.bookstore.book.web.servlet.admine;

import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.bookstore.category.service.CategoryCervice;
import cn.itcast.bookstore.category.web.servlet.CategoryServlet;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.commons.CommonUtils;
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
        List<Category> categories = categoryCervice.findAll();

        request.setAttribute("book", book);
        request.setAttribute("categories", categories);
        return "f:/adminjsps/admin/book/desc.jsp";
    }


    /*
    删除图书
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {

        bookService.delegateBook(request.getParameter("bid"));
        return findAllBook(request, response);
    }

    /*
    编辑图书
     */
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {

        //封装表单数据
        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);

        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);

        book.setCid(category.getCid());
        bookService.edit(book);
        return findAllBook(request, response);
    }

    /*
    添加图书
     */
    public String addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {

        //封装表单数据
        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);

        //上传图片,保存图片的地址

        book.setBid(CommonUtils.uuid());

        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);

        book.setCid(category.getCid());
        bookService.addBook(book);


        return findAllBook(request, response);
    }


    public String preAddBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {

        //获取分类
        List<Category> categories =  categoryCervice.findAll();
        request.setAttribute("categorys",categories);
        return "f:/adminjsps/admin/book/add.jsp";
    }


}
