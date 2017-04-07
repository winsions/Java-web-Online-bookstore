package cn.itcast.bookstore.category.service;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.daomain.Category;
import cn.itcast.bookstore.user.service.UserException;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * Created by winsion on 2017/3/30.
 */
public class CategoryCervice {
    CategoryDao categoryDao = new CategoryDao();
    BookDao bookDao = new BookDao();

    public List<Category> findAll() {

        return categoryDao.findAll();
    }

    public void delegateCategoryByCid(String cid) throws UserException {

      int count =  bookDao.findBookCountByCid(cid);
      if (count > 0) throw new UserException("该分类下还有图书不能删除");
      //删除该类型
        categoryDao.delegateCategoryByCid(cid);
    }

    public Category editCategory(String cid) {
        return   categoryDao.load(cid);
    }

    public void changeCategoryName(Category category) {

        categoryDao.changeCategoryName(category);
    }

    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }
}
