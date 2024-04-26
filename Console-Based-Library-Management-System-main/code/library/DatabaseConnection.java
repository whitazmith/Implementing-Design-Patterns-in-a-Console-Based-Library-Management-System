package library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.Period;

public class DatabaseConnection {
  private static DatabaseConnection instance;
  private Map<String, User> users;
  private Map<String, Book> books;
  private Map<String, List<Book>> userBorrowedBooks;
  private Map<String, Integer> userBorrowedBookCount;
  private Map<String, LocalDate> borrowedDates;
  private Map<String, String> admins;


  private DatabaseConnection() {
    users = new HashMap<>();
    books = new HashMap<>();
    userBorrowedBooks = new HashMap<>();
    userBorrowedBookCount = new HashMap<>();
    borrowedDates = new HashMap<>();
    admins = new HashMap<>();
    admins.put("admin", "admin123");
  }

  public static DatabaseConnection getInstance() {
    if (instance == null) {
      instance = new DatabaseConnection();
    }
    return instance;
  }

  public void addUser(User user) {
    users.put(user.getName(), user);
  }

  public User getUser(String name) {
    return users.get(name);
  }

  public void addBook(Book book) {
    books.put(book.getTitle(), book);
  }

  public boolean removeBook(String title) {
    return books.remove(title) != null;
  }

  public Book getBook(String title) {
    return books.get(title);
  }

  public void displayBooks() {
    if (books.isEmpty()) {
      System.out.println("No books available.");
    } else {
      books.values().forEach(book -> book.displayInfo());
    }
  }

  public boolean borrowBook(String title, String userName) {
    Book book = books.get(title);
    if (book != null && book.isAvailable() && userBorrowedBookCount.getOrDefault(userName, 0) < 10) {
      book.setAvailable(false);
      List<Book> borrowedBooks = userBorrowedBooks.computeIfAbsent(userName, k -> new ArrayList<>());
      borrowedBooks.add(book);
      borrowedDates.put(book.getTitle(), LocalDate.now());
      userBorrowedBookCount.put(userName, userBorrowedBookCount.getOrDefault(userName, 0) + 1);
      return true;
    }
    return false;
  }

  public boolean returnBook(String title, String userName) {
    List<Book> borrowedBooks = userBorrowedBooks.get(userName);
    if (borrowedBooks != null) {
      for (Book book : borrowedBooks) {
        if (book.getTitle().equals(title)) {
          book.setAvailable(true);
          borrowedBooks.remove(book);
          userBorrowedBookCount.put(userName, userBorrowedBookCount.getOrDefault(userName, 0) - 1);
          return true;
        }
      }
    }
    return false;
  }

  public List<BookDecorator> getBorrowedBooks(String userName) {
    List<Book> originalBorrowedBooks = userBorrowedBooks.getOrDefault(userName, Collections.emptyList());
    List<BookDecorator> decoratedBooks = new ArrayList<>();
    for (Book book : originalBorrowedBooks) {
      BookDecorator decoratedBook = new BookDecorator(book);
      LocalDate borrowedDate = borrowedDates.get(book.getTitle());
      if (borrowedDate != null) {
        Period period = Period.between(borrowedDate, LocalDate.now());
        if (period.getDays() > 30) {
          int daysOverdue = period.getDays() - 30;
          double overdueFee = daysOverdue * 1.0;
          decoratedBook.setOverdue(true);
          decoratedBook.setOverdueFee(overdueFee);
        }
      }
      decoratedBooks.add(decoratedBook);
    }
    return decoratedBooks;
  }
  public boolean authenticateAdmin(String username, String password) {
    String storedPassword = admins.get(username);
    return storedPassword != null && storedPassword.equals(password);
  }
  public void addAdmin(String username, String password) {
    admins.put(username, password);
  }
  public boolean registerAdmin(String newUsername, String newPassword) {
    admins.put(newUsername, newPassword);
    return true;
  }
  public boolean addTags(String title, List<String> tags) {
    Book book = books.get(title);
    if (book != null) {
      book.getTags().addAll(tags);
      return true;
    } else {
      return false;
    }
  }

}

