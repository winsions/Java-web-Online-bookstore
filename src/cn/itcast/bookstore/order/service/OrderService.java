package cn.itcast.bookstore.order.service;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.daomain.Order;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.jdbc.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by winsion on 2017/3/31.
 */
public class OrderService {

    OrderDao orderDao = new OrderDao();

    public void add(Order order){

        //开启事物

        try {
            JdbcUtils.beginTransaction();
            //添加订单
            orderDao.addOrder(order);

            //添加订单条目
            orderDao.addOrderItem(order.getOrderItems());


            JdbcUtils.commitTransaction();
        } catch (SQLException e) {
            //回滚事物
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException e1) {
                throw new RuntimeException("事物回滚失败");
            }

            throw new RuntimeException(e);
        }
    }

    public List<Order> findAllorder(String uid) {


      return   orderDao.findAllOrder(uid);
    }


    public Order finfOrderByOid(String oid) {

        return orderDao.finfOrderByOid(oid);
    }

    public void findOrderState(String oid, int i1, int i) throws UserException {

        int state = orderDao.findOrderState(oid);
        if (state != i1) {
            throw new UserException("订单异常");
        }
        //修改订单状态为i
        orderDao.changOrderState(oid,i);
    }


    public List<Order> findAllorder() {
        List<Order> orders  = orderDao.findAllOrder();
        return orders;
    }

    /*

     */
    public void sendOrder(String oid, int i) {

        orderDao.changOrderState(oid,i);
    }

    public List<Order> findorderByState(int i) {
        List<Order> orders  =  orderDao.findorderByState(i);
        return orders;
    }
}
