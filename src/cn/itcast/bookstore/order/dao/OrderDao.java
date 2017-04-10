package cn.itcast.bookstore.order.dao;

import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.bookstore.order.daomain.Order;
import cn.itcast.bookstore.order.daomain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by winsion on 2017/3/31.
 */
public class OrderDao {
    TxQueryRunner txQueryRunner = new TxQueryRunner();


    public void addOrder(Order order) {

        String sql = "insert into orders values (?,?,?,?,?,?)";
        System.out.println(order.toString());
        /*
             * 处理util的Date转换成sql的Timestamp
			 */
        Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
        Object[] param = {order.getOid(), timestamp, order.getTotal(),
                order.getState(), order.getOwner().getUid(), order.getAddress()};
        try {
            txQueryRunner.update(sql, param);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void addOrderItem(List<OrderItem> orderItems) {

        for (OrderItem orderItem : orderItems) {

            String sql = "insert into orderitem values (?,?,?,?,?)";

            Object[] param = {orderItem.getIid(), orderItem.getCount(),
                    orderItem.getSubtotal(), orderItem.getOrder().getOid(), orderItem.getBook().getBid()};

            try {
                txQueryRunner.update(sql, param);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public List<Order> findAllOrder(String uid) {

        String sql = "select * from orders where uid=?";
        try {
            List<Order> orders = txQueryRunner.query(sql, new BeanListHandler<Order>(Order.class), uid);
            for (Order order : orders) {
                //根据订单加载出订单条目
                loadOrderitem(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 加载订单条目
     *
     * @param order
     */
    private void loadOrderitem(Order order) throws SQLException {

        String sql = "select * from orderitem i, book b  where i.bid= b.bid and oid=?";
        List<Map<String, Object>> mapList = txQueryRunner.query(sql, new MapListHandler(), order.getOid());

        List<OrderItem> orderItems = toOrerItems(mapList);

        order.setOrderItems(orderItems);
    }

    /**
     * 根据maplist生成orderItem和Book 简历关系
     *
     * @param mapList
     * @return
     */
    private List<OrderItem> toOrerItems(List<Map<String, Object>> mapList) {

        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (Map<String, Object> stringObjectMap : mapList) {

            OrderItem orderItem = CommonUtils.toBean(stringObjectMap, OrderItem.class);
            Book book = CommonUtils.toBean(stringObjectMap, Book.class);
            orderItem.setBook(book);
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    public Order finfOrderByOid(String oid) {

        String sql = "select * from orders where oid=?";
        try {
            Order order = txQueryRunner.query(sql, new BeanHandler<Order>(Order.class), oid);
            loadOrderitem(order);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public int findOrderState(String oid) {

        String sql = "select state from orders where oid=?";

        try {
            return (int) txQueryRunner.query(sql, new ScalarHandler(), oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changOrderState(String oid, int i) {
        String sql = "update orders set state=? where oid=?";

        try {
            txQueryRunner.update(sql, i, oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Order> findAllOrder() {
        String sql = "select *from orders ";
        try {
          List<Order> orders =  txQueryRunner.query(sql,new BeanListHandler<Order>(Order.class));
            for (Order order :orders) {
                loadOrderitem(order);
            }
            return orders;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Order> findorderByState(int i) {
        String sql = "select * from orders where state=?";
        try {
            List<Order> orders =  txQueryRunner.query(sql,new BeanListHandler<Order>(Order.class),(Number)i);
            for (Order order :orders) {
                loadOrderitem(order);
            }


            return orders;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
