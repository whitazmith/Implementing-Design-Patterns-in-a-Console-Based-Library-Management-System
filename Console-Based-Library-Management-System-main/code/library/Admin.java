package library;

import java.awt.*;
import java.util.List;
public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean registerUser(String username, String password) {
        return true;
    }

    public boolean modifyUserInfo(String username, String newPhoneNum) {
        User user = DatabaseConnection.getInstance().getUser(username);
        if (user != null) {
            user.setPhoneNum(newPhoneNum);
            return true;
        } else {
            return false;
        }
    }

    public boolean addBook(String title, String author, String publicationDate, String isbn, List<String> tags) {
        Book book = new Book(title, author, publicationDate, isbn);
        book.setTags(tags);
        DatabaseConnection.getInstance().addBook(book);
        return true;
    }

    public boolean removeBook(String title) {
        return DatabaseConnection.getInstance().removeBook(title);
    }

    public boolean addISBN(String title, String isbn) {
        Book book = DatabaseConnection.getInstance().getBook(title);
        if (book != null) {
            book.setIsbn(isbn);
            return true;
        } else {
            return false;
        }
    }

    public boolean addTags(String title, List<String> tags) {
        Book book = DatabaseConnection.getInstance().getBook(title);
        if (book != null) {
            book.getTags().addAll(tags);
            return true;
        } else {
            return false;
        }
    }
    public boolean registerAdmin(String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
        DatabaseConnection.getInstance().registerAdmin(newUsername, newPassword);
        return true;

    }
    public boolean authenticateAdmin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

}
