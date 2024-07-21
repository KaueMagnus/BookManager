package view;

import model.Book;
import service.BookService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryMenu {
    private BookService bookService;
    private Scanner scanner;

    public LibraryMenu() {
        this.bookService = new BookService();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;

        do {
            System.out.println("\n==========BookManager==========");
            System.out.println("1 - Add Book");
            System.out.println("2 - List Books");
            System.out.println("3 - Update Book");
            System.out.println("4 - Delete Book");
            System.out.println("5 - Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    System.out.println("\nLeaving the program...");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (option != 5);
    }

    private void addBook() {
        System.out.println("\n===== Add Book =====");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Number of pages: ");
        int numberOfPages = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setNumberOfPages(numberOfPages);

        try {
            bookService.addBook(book);
            System.out.println("Book added successfully!");
        } catch (SQLException exception) {
            System.out.println("Error adding book: " + exception.getMessage());
        }
    }

    private void listBooks() {
        System.out.println("\n===== List Books =====");
        try {
            List<Book> books = bookService.listBooks();
            for (Book book : books) {
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Number of pages: " + book.getNumberOfPages());
                System.out.println("===============================");
            }
        } catch (SQLException exception) {
            System.out.println("Error listing books: " + exception.getMessage());
        }
    }

    private void updateBook() {
        System.out.println("\n===== Update Book =====");
        System.out.print("Book Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            if (bookService.bookExist(id)) {
                System.out.print("New title: ");
                String title = scanner.nextLine();
                System.out.print("New author: ");
                String author = scanner.nextLine();
                System.out.print("New number of pages: ");
                int numberOfPages = scanner.nextInt();
                scanner.nextLine();

                Book book = new Book();
                book.setId(id);
                book.setTitle(title);
                book.setAuthor(author);
                book.setNumberOfPages(numberOfPages);

                bookService.updateBook(book);
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book ID does not exist!");
            }
        } catch (SQLException exception) {
            System.err.println("Error updating book: " + exception.getMessage());
        }
    }

    private void deleteBook() {
        System.out.println("\n===== Delete Book =====");
        System.out.print("Book Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            if (bookService.bookExist(id)) {
                bookService.deleteBook(id);
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book ID does not exist!");
            }
        } catch (SQLException exception) {
            System.out.println("Error deleting book: " + exception.getMessage());
        }
    }
}
