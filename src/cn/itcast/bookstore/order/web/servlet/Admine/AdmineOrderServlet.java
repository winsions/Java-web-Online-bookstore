package cn.itcast.bookstore.order.web.servlet.Admine;

import cn.itcast.bookstore.cart.daomain.Cart;
import cn.itcast.bookstore.order.daomain.Order;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by winsion on 2017/4/10.
 */
@WebServlet(name = "AdmineOrderServlet")
public class AdmineOrderServlet extends BaseServlet {

    OrderService orderService = new OrderService();

    public String findAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


       List<Order> orders = orderService.findAllorder();
       request.setAttribute("orders",orders);
        return "f:/adminjsps/admin/order/list.jsp";

    }

    public String findUnpayOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Order> orders = orderService.findorderByState(1);
        request.setAttribute("orders",orders);
        return "f:/adminjsps/admin/order/list.jsp";

    }

    public String findPayOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Order> orders = orderService.findorderByState(2);
        request.setAttribute("orders",orders);
        return "f:/adminjsps/admin/order/list.jsp";

    }

    public String findUnAcessOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Order> orders = orderService.findorderByState(3);
        request.setAttribute("orders",orders);
        return "f:/adminjsps/admin/order/list.jsp";

    }

    public String findAcessOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Order> orders = orderService.findorderByState(4);
        request.setAttribute("orders",orders);
        return "f:/adminjsps/admin/order/list.jsp";

    }


    /*
    发货
     */
    public String sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        orderService.sendOrder(request.getParameter("oid"),3);
        return findAllOrder(request,response);

    }

}
