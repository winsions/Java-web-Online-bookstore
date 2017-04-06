package cn.itcast.bookstore.order.daomain;

import cn.itcast.bookstore.book.daomain.Book;

/**
 * Created by winsion on 2017/3/31.
 */
public class OrderItem {

    private String iid;
    private double subtotal;
    private int count;
    private Order order;
    private Book book;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
