package cn.itcast.bookstore.cart.web.servlet;

import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.cart.daomain.Cart;
import cn.itcast.bookstore.cart.daomain.CartItem;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by winsion on 2017/3/31.
 */
@WebServlet(name = "CartServlet")
public class CartServlet extends BaseServlet {
    /*
    查找购物条目
     */
    public String findAllCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("car");

        cart.getAllCartItem();
        return "f:/jsps/cart/list.jsp";
    }

    /*
   删除购物条目
    */
    public String delegate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("car");

        cart.delegate(request.getParameter("bid"));
        return "f:/jsps/cart/list.jsp";
    }

    /*
   清空购物条目
    */
    public String clean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("car");
        cart.clean();
        return "f:/jsps/cart/list.jsp";
    }


    /*
   添加购物条目
    */
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取

        Cart cart = (Cart) request.getSession().getAttribute("car");
        String bid = request.getParameter("bid");
        Book book = null;
        try {
            book = new BookService().load(bid);
        } catch (UserException e) {
            throw new RuntimeException(e.getMessage());
        }
        CartItem item = new CartItem();
        int count = Integer.parseInt(request.getParameter("count"));
        item.setCount(count);
        item.setBook(book);
        cart.addCartItem(item);
        return "f:/jsps/cart/list.jsp";
    }

}
