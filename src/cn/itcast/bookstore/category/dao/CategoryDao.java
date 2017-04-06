package cn.itcast.bookstore.category.dao;

import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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


}
