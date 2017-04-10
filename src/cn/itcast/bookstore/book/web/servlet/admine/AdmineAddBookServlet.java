package cn.itcast.bookstore.book.web.servlet.admine;

import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.commons.CommonUtils;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by winsion on 2017/4/10.
 */
@WebServlet(name = "AdmineAddBookServlet")
public class AdmineAddBookServlet extends HttpServlet {

    BookService bookService = new BookService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//封装表单数据
        //上传图片,保存图片的地址
            //创建工厂

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);




        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);

        book.setCid(category.getCid());
        bookService.addBook(book);

    }


}
