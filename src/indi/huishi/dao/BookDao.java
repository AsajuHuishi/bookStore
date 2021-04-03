package indi.huishi.dao;

import indi.huishi.pojo.Book;

import java.util.List;

public interface BookDao {
    // 增删改查，查询所有
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    List<Book> queryForPageItems(Integer begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(Integer begin, int pageSize, int min, int max);
}
