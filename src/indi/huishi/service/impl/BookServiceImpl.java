package indi.huishi.service.impl;

import indi.huishi.dao.BookDao;
import indi.huishi.dao.impl.BookDaoImpl;
import indi.huishi.pojo.Book;
import indi.huishi.pojo.Page;
import indi.huishi.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        //设置5属性

        // 每页数量
        page.setPageSize(pageSize);
        // 总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        // 总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        // 当前页
        page.setPageNo(pageNo);

        // 当前页数据
        Integer begin = (pageNo-1)*pageSize;//开始索引
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        //设置5属性
        // 每页数量
        page.setPageSize(pageSize);
        // 总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        // 总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        // 当前页
        page.setPageNo(pageNo);

        // 当前页数据
        Integer begin = (pageNo-1)*pageSize;//开始索引
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
