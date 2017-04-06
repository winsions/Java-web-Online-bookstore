package cn.itcast.bookstore.category.service;

import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.daomain.Category;

import java.util.List;

/**
 * Created by winsion on 2017/3/30.
 */
public class CategoryCervice {
    CategoryDao categoryDao = new CategoryDao();

    public List<Category> findAll() {

        return categoryDao.findAll();
    }

}
