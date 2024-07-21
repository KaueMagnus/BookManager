package service;

import repository.BookRepository;
import model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    public boolean bookExist(int id) throws SQLException {
        return bookRepository.bookExists(id);
    }

    public void addBook(Book book) throws SQLException {
        bookRepository.addBook(book);
    }

    public List<Book> listBooks() throws SQLException {
        return bookRepository.listBooks();
    }

    public void updateBook(Book book) throws SQLException {
        this.bookRepository.updateBook(book);
    }

    public void deleteBook(int id) throws SQLException {
        this.bookRepository.deleteBook(id);
    }
}
