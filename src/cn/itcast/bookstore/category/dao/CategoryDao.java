package cn.itcast.bookstore.category.dao;

import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by winsion on 2017/3/30.
 */
public class CategoryDao {
    TxQueryRunner txQueryRunner = new TxQueryRunner();

    public List<Category> findAll() {

        String sql = "select * from category";
        try {
            return txQueryRunner.query(sql,new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delegateCategoryByCid(String cid) {
        String sql = "delete  from category where cid=?";
        try {
             txQueryRunner.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Category load(String cid) {
        String sql = "select * from category where cid=?";
        try {
           return  txQueryRunner.query(sql,new BeanHandler<Category>(Category.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeCategoryName(Category category) {
        String sql = "update category set cname=? where cid=?";
        try {
            txQueryRunner.update(sql,category.getCname(),category.getCid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategory(Category category) {
        String sql = "insert into category values (?,?)";
        try {
            txQueryRunner.update(sql,category.getCid(),category.getCname());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
