package cn.itcast.bookstore.user.service;

import cn.itcast.bookstore.user.damain.User;
import cn.itcast.bookstore.user.dao.UserDao;

/**
 * Created by winsion on 2017/3/29.
 */
public class UserService {
    UserDao userDao = new UserDao();

    //注册功能
    public void regist(User user) throws UserException {
        //效验用户名,
        User u = userDao.findUsername(user.getUsername());
        if (u != null) throw new UserException("用户名已被注册！");
        u = userDao.findEmail(user.getEmail());
        //效验 邮箱
        if (u != null) throw new UserException("邮箱已被注册");

        //插入用户到数据库
        userDao.addUser(user);
    }

    public User login(User user) throws UserException {
         //效验用户名,
        User user1 = userDao.findUsername(user.getUsername());
        if (user1 == null) throw new UserException("用户名未注册");

        user1 = userDao.login(user);
        if (user1 == null)  throw new UserException("用户名或者密码错误");
        System.out.println(user1.isState());
        if (!user1.isState()) throw new UserException("用户尚未激活");
        return user1;
    }
}
