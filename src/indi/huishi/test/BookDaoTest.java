package indi.huishi.test;

import indi.huishi.dao.BookDao;
import indi.huishi.dao.impl.BookDaoImpl;
import indi.huishi.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
//    Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String imgPath
    @Test
    public void addBook(){
        int a = bookDao.addBook(new Book(21,"悲惨世界","雨果",new BigDecimal(60),100,23,null));
        System.out.println(a);
    }

    @Test
    public void query(){
        List<Book> bookList = bookDao.queryBooks();
        System.out.println(bookList);
    }

    @Test
    public void deleteBook(){
        int b = bookDao.deleteBookById(0);
        System.out.println(b);
    }

    @Test
    public void queryForPageTotalCount() {
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        System.out.println(pageTotalCount);
    }

    @Test
    public void queryForPageItems() {
        Integer begin = 3;
        Integer pageSize = 4;
        String sql = "select id, name, author, price, sales, stock, img_path from t_book limit ?,?";
        List<Book> bookList = bookDao.queryForPageItems(begin, pageSize);
        for (Book book:bookList) {
            System.out.println(book);
        }
        return;

    }

    @Test
    public void queryForPageTotalCountByPrice(){
        Integer integer = bookDao.queryForPageTotalCountByPrice(21, 1000);
        System.out.println(integer);
    }

    @Test
    public void queryForPageItemsByPrice(){
        List<Book> bookList = bookDao.queryForPageItemsByPrice(3, 4, 12, 100);
        for (Book book:bookList
             ) {
            System.out.println(book);
        }
    }
}
