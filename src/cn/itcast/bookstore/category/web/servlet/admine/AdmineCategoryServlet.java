package cn.itcast.bookstore.category.web.servlet.admine;

import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.bookstore.category.service.CategoryCervice;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.sun.org.apache.xml.internal.security.encryption.CipherData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by winsion on 2017/4/7.
 */
@WebServlet(name = "AdmineCategoryServlet")
public class AdmineCategoryServlet extends BaseServlet {
    CategoryCervice categoryCervice = new CategoryCervice();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categories = categoryCervice.findAll();

        request.setAttribute("categorys", categories);

        return "f:/adminjsps/admin/category/list.jsp";

    }


    /**
     * 删除种类
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String delegateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            categoryCervice.delegateCategoryByCid(request.getParameter("cid"));
            return findAll(request, response);
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            return "f:/adminjsps/msg.jsp";
        }


    }

    /**
     * 修稿分类名
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Category category = categoryCervice.editCategory(request.getParameter("cid"));
        request.setAttribute("category", category);
        return "f:/adminjsps/admin/category/mod.jsp";


    }

    /**
     * 改变类型
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String changeCategoryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //封装表单数据
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        categoryCervice.changeCategoryName(category);

        return findAll(request, response);


    }


    public String addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //封装表单数据
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);

        category.setCid(CommonUtils.uuid());
        categoryCervice.addCategory(category);

        return findAll(request, response);


    }
}
