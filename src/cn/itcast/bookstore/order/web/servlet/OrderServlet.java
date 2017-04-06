package cn.itcast.bookstore.order.web.servlet;

import cn.itcast.bookstore.cart.daomain.Cart;
import cn.itcast.bookstore.cart.daomain.CartItem;
import cn.itcast.bookstore.order.daomain.Order;
import cn.itcast.bookstore.order.daomain.OrderItem;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.bookstore.user.damain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by winsion on 2017/3/31.
 */
@WebServlet(name = "OrderServlet")
public class OrderServlet extends BaseServlet {

    OrderService orderService = new OrderService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //根据购物车生成订单和订单条目
        Cart cart = (Cart) request.getSession().getAttribute("car");

        //生成订单
        Order order = new Order();
        order.setOid(CommonUtils.uuid());
        order.setOrdertime(new Date());
        order.setState(1);
        User user = (User) request.getSession().getAttribute("session_user");
        order.setOwner(user);
        order.setTotal(cart.getTotal());
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (CartItem cartItem : cart.getAllCartItem()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setIid(CommonUtils.uuid());
            orderItem.setBook(cartItem.getBook());
            orderItem.setCount(cartItem.getCount());
            orderItem.setOrder(order);
            orderItem.setSubtotal(cartItem.getCartItemTotal());
            orderItems.add(orderItem);
        }


        order.setOrderItems(orderItems);


        //清空购物车
        cart.clean();

        orderService.add(order);

        request.setAttribute("order", order);
        return "f:/jsps/order/desc.jsp";
    }

    public String findAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("session_user");

        List<Order> orders = orderService.findAllorder(user.getUid());

        request.setAttribute("orders", orders);

        return "f:/jsps/order/list.jsp";


    }


    /**
     * 点击付款加载订单
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String loadOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Order order = orderService.finfOrderByOid(request.getParameter("oid"));

        request.setAttribute("order", order);

        return "f:/jsps/order/desc.jsp";


    }

    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        //根据订单好查询订单状态
        try {
            orderService.findOrderState(oid);
        } catch (UserException e) {
            request.setAttribute("msg","订单异常");
            return "f:/jsps/order/msg.jsp";
        }
        request.setAttribute("msg","确认收货成功");
        return "f:/jsps/order/msg.jsp";


    }
}
