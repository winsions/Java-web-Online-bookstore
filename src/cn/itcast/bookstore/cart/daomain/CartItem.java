package cn.itcast.bookstore.cart.daomain;

import cn.itcast.bookstore.book.daomain.Book;

import java.math.BigDecimal;

/**
 * Created by winsion on 2017/3/31.
 */
public class CartItem {

    private Book book;
    private int count;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //小计条目的价钱
    public double getCartItemTotal(){
        BigDecimal bigDecimal = new BigDecimal(count);
        BigDecimal bigDecimal1 = new BigDecimal(book.getPrice());
        return bigDecimal.multiply(bigDecimal1).doubleValue();

    }


}
