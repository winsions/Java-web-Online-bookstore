package cn.itcast.bookstore.user.dao;

import cn.itcast.bookstore.user.damain.User;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * Created by winsion on 2017/3/29.
 */
public class UserDao {

    //持久层,数据库
    private TxQueryRunner txQueryRunner = new TxQueryRunner();

    public User findUsername(String username) {

        //验证 名字和邮箱是否注册
        String sql = "select * from tb_user where username=?";
        try {

            return txQueryRunner.query(sql,new BeanHandler<User>(User.class),username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public User findEmail(String email) {

        //验证 名字和邮箱是否注册
        String sql = "select * from tb_user where email=?";
        try {
            return txQueryRunner.query(sql,new BeanHandler<User>(User.class),email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 添加user到数据库
     * @param user
     */
    public void addUser(User user) {

        String sql = "insert into tb_user values(?,?,?,?,?,?)";
        Object[] param = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.isState()};

        try {
            txQueryRunner.update(sql,param);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //登录
    public User login(User user) {
        String sql = "select * from tb_user where username=? and password=?";
        Object[] param = {user.getUsername(),user.getPassword()};

        try {
           return txQueryRunner.query(sql,new BeanHandler<User>(User.class),param);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
