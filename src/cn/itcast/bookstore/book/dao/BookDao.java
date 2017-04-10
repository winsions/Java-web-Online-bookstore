package cn.itcast.bookstore.book.dao;

import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by winsion on 2017/3/30.
 */
public class BookDao {
    TxQueryRunner txQueryRunner = new TxQueryRunner();

    public List<Book> findAll() {
        String sql = "select * from book order by cid";
        try {
            return txQueryRunner.query(sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book load(String bid) {

        String sql = "select * from book where bid=?";
        try {
            return txQueryRunner.query(sql, new BeanHandler<Book>(Book.class), bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByCategory(String cid) {
        String sql = "select * from book where cid=?";
        try {
            return txQueryRunner.query(sql,new BeanListHandler<Book>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findBookCountByCid(String cid) {

        String sql = "select count(*) from book where cid=?";

        try {
           Number number = (Number)txQueryRunner.query(sql,new ScalarHandler(),cid);
           return number.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delegateBook(String bid) {
        String sql = "delete from book where bid=?";
        try {
            txQueryRunner.update(sql,bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Book book) {
        String sql = "update book set bname=?, price=?,author=?, image=?, cid=? where bid=? ";
        Object[] params = {book.getBname(), book.getPrice(),
                book.getAuthor(), book.getImage(),
                book.getCid(), book.getBid()};
        try {
            txQueryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(Book book) {
        String sql = "insert into book values (?,?,?,?,?,?)";//bname=?, price=?,author=?, image=?, cid=? , bid=? ";
        Object[] params = {book.getBid(),book.getBname(), book.getPrice(),
                book.getAuthor(), book.getImage(),
                book.getCid() };
        try {
            txQueryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
