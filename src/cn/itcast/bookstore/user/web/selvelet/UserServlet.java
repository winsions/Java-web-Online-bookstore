package cn.itcast.bookstore.user.web.selvelet;

import cn.itcast.bookstore.cart.daomain.Cart;
import cn.itcast.bookstore.user.damain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

/**
 * Created by winsion on 2017/3/29.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserService();

    public String regist(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //封装表单数据库
        User user = CommonUtils.toBean(req.getParameterMap(), User.class);

        //输入校验,创建一个map 手机错误信息
        Map<String, String> errors = new HashMap<String, String>();

        errors = checkOut(user, errors);

        //判断map是否为空
        if (errors.size() > 0) {
            //保存错误信息保存表单数剧
            req.setAttribute("errors", errors);
            req.setAttribute("user", user);
            return "f:/jsps/user/regist.jsp";
        }

        //效验
        try {
            //添加uid
            user.setUid(CommonUtils.uuid());
            user.setCode(CommonUtils.uuid() + CommonUtils.uuid());
            userService.regist(user);
        } catch (UserException e) {
            //保存异常信息
            req.setAttribute("msg", e.getMessage());
            req.setAttribute("user", user);
            return "f:/jsps/user/regist.jsp";
        }


        //注册成功
        req.setAttribute("msg", "注册成功");
        //添加链接

//        List<String> link = new ArrayList<>();

        return "/jsps/msg.jsp";
    }


    /*登录*/


    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //封装表单数据
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);

//        创建一个map保存错误信息
        Map<String, String> errors = new HashMap<String, String>();


        errors = checkOut(user, errors);

        if (errors.size() > 1) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            return "f:/jsps/user/login.jsp";
        }
        //登录
        try {
            user = userService.login(user);
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute("session_user", user);
            //给用户添加一辆购物车
            session.setAttribute("car",new Cart());
        } catch (UserException e) {
            //用户名密码错误
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("user", user);
            return "/jsps/user/login.jsp";
        }



        return "/jsps/main.jsp";
    }


    //效验用户名和密码
    public Map<String, String> checkOut(User user, Map<String, String> errors) {


        String username = user.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errors.put("username", "用户名不能为空！");
        } else if (username.length() < 3 || username.length() > 10) {
            errors.put("username", "用户名长度必须在3~10之间！");
        }

        String password = user.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "密码不能为空！");
        } else if (password.length() < 3 || password.length() > 10) {
            errors.put("password", "密码长度必须在3~10之间！");
        }

        String email = user.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Email不能为空！");
        } else if (!email.matches("\\w+@\\w+\\.\\w+")) {
            errors.put("email", "Email格式错误！");
        }
        return errors;

    }

    /*退出登录*/

    public String quit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().invalidate();
        return "r:/index.jsp";
    }

}
