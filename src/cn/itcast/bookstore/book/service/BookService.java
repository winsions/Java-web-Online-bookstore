package cn.itcast.bookstore.book.service;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.book.daomain.Book;
import cn.itcast.bookstore.user.service.UserException;

import java.util.List;

/**
 * Created by winsion on 2017/3/30.
 */
public class BookService {
    BookDao bookDao = new BookDao();

    public List<Book> findAll() throws UserException {
        List<Book> books = bookDao.findAll();
        if (books == null) throw new UserException("没有图书");
        return books;
    }

    public Book load(String bid) throws UserException {

        Book book = bookDao.load(bid);
        if (book==null) throw new UserException("书一下价");
        return book;
    }

    public List<Book> findByCategory(String cid) throws UserException {
        List<Book> books = bookDao.findByCategory(cid);
        if (books == null) throw new UserException("没有这个类型的图书");
        return books;
    }

    public void delegateBook(String bid) {
        bookDao.delegateBook(bid);
    }

    public void edit(Book book) {
        bookDao.edit(book);
    }

    public void addBook(Book book) {
        bookDao.addBook(book);
    }
}
