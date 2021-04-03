package indi.huishi.test;

import indi.huishi.pojo.Book;
import indi.huishi.pojo.Page;
import indi.huishi.service.BookService;
import indi.huishi.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void query(){
        List<Book> bookList = bookService.queryBooks();
        System.out.println(bookList);
    }

    @Test
    public void page() {
        int pageNo = 2;
        int pageSize = 4;
        Page<Book> page = bookService.page(pageNo, pageSize);
        System.out.println(page);
    }

    @Test
    public void pageByPrice() {
        Page<Book> bookPage = bookService.pageByPrice(4, 4, 12, 144);
        System.out.println(bookPage);
    }
}
