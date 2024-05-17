package library;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.io.Console;

public class LibraryManagementSystem {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Admin admin = new Admin(ADMIN_USERNAME, ADMIN_PASSWORD);
        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";
        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";
        while (true) {
            System.out.println(boldTextStart +"======================================================================"+ boldTextEnd);
            System.out.println(boldTextStart + "**********Welcome to the Library Management System**********" + boldTextEnd);
            System.out.println(boldTextStart +"======================================================================"+ boldTextEnd);
            System.out.println(boldTextStart +"1. Sign Up"+ boldTextEnd);
            System.out.println(boldTextStart +"2. Login"+ boldTextEnd);
            System.out.println(boldTextStart +"3. Administrator "+ boldTextEnd);
            System.out.println(boldTextStart +"4. Exit"+ boldTextEnd);
            System.out.print(boldTextStart +"\033[0;33mEnter your choice:  \033[0m"+ boldTextEnd);
            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println(redColorStart +"Invalid input. Please enter a number."+ resetColor);
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    registerUser(scanner, dbConnection);
                    break;
                case 2:
                    loginUser(scanner, dbConnection);
                    break;
                case 3:
                    adminMode(scanner, dbConnection, admin);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println(redColorStart +"Invalid choice. Please try again."+ resetColor);
            }
        }
    }
    private static void adminMode(Scanner scanner, DatabaseConnection dbConnection, Admin admin) {

        boolean authenticated = false;

        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";
        while (!authenticated) {
            System.out.print(boldTextStart + "\u001b[0;33mEnter admin username:  \u001b[0m" + boldTextEnd);
            String username = scanner.nextLine();
            System.out.print(boldTextStart + "\u001b[0;33mEnter admin password:  \u001b[0m" + boldTextEnd);
            String password = scanner.nextLine();
            if (admin.authenticate(username, password)) {
                authenticated = true;
                System.out.println(greenColorStart + "Admin login successful." + resetGreenColor);
            } else {
                System.out.println(redColorStart + "Admin login failed: Invalid username or password. " +
                        "Please try again or enter 'exit' to return to the main menu." + resetColor);
                System.out.print(boldTextStart + "\u001b[0;33mEnter 'exit' to return to the main menu, or press Enter to retry: " +
                        "\u001b[0m" + boldTextEnd);
                String exitOption = scanner.nextLine();
                if (exitOption.equalsIgnoreCase("exit")) {
                    return; // 退出到重新选择页面
                }
            }
        }

        while (true) {
            System.out.println(boldTextStart +"=============================================="+ boldTextEnd);
            System.out.println(boldTextStart +"Admin Mode"+ boldTextEnd);
            System.out.println(boldTextStart +"=============================================="+ boldTextEnd);
            System.out.println(boldTextStart +"1. Modify user account information"+ boldTextEnd);
            System.out.println(boldTextStart +"2. Add book"+ boldTextEnd);
            System.out.println(boldTextStart +"3. Remove book"+ boldTextEnd);
            System.out.println(boldTextStart +"4. Add ISBN"+ boldTextEnd);
            System.out.println(boldTextStart +"5. Add new Administrator"+ boldTextEnd);
            System.out.println(boldTextStart +"6. Add tags"+ boldTextEnd);
            System.out.println(boldTextStart +"7. Exit admin mode"+ boldTextEnd);
            System.out.print(boldTextStart +"\033[0;33mEnter your choice:  \033[0m"+ boldTextEnd);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    // Modify user account information
                    System.out.print(boldTextStart +"\033[0;33mEnter username to modify: \033[0m"+ boldTextEnd);
                    String modifyUsername = scanner.nextLine();
                    System.out.print(boldTextStart +"\033[0;33mEnter new phone number: \033[0m"+ boldTextEnd);
                    String newPhoneNum = scanner.nextLine();
                    if (admin.modifyUserInfo(modifyUsername, newPhoneNum)) {
                        System.out.println(greenColorStart +"User account information modified successfully."+ resetGreenColor);
                    } else {
                        System.out.println(redColorStart +"Failed to modify user account information: User not found."+ resetColor);
                    }
                    break;
                case 2:
                    // Add book
                    System.out.println(boldTextStart +"\033[0;33mCurrent Books in Database: \033[0m"+ boldTextEnd);
                    dbConnection.displayBooks();
                    System.out.print(boldTextStart +"\033[0;33mEnter book title:  \033[0m"+ boldTextEnd);
                    String title = scanner.nextLine();
                    System.out.print(boldTextStart +"\033[0;33mEnter author name:  \033[0m"+ boldTextEnd);
                    String author = scanner.nextLine();
                    System.out.print(boldTextStart +"\033[0;33mEnter publication date:  \033[0m"+ boldTextEnd);
                    String publicationDate = scanner.nextLine();
                    System.out.print(boldTextStart +"\033[0;33mEnter ISBN:  \033[0m"+ boldTextEnd);
                    String isbn = scanner.nextLine();
                    System.out.print(boldTextStart +"\033[0;33mEnter tags (comma-separated):  \033[0m"+ boldTextEnd);
                    String tagsInput = scanner.nextLine();
                    List<String> tags = Arrays.asList(tagsInput.split(","));
                    if (admin.addBook(title, author, publicationDate, isbn, tags)) {
                        System.out.println(greenColorStart +"Book added successfully."+ resetGreenColor);
                    } else {
                        System.out.println(redColorStart +"Failed to add books."+ resetColor);
                    }
                    break;
                case 3:
                    // Remove book
                    System.out.println(boldTextStart +"\033[0;33mCurrent Books in Database:\033[0m"+ boldTextEnd);
                    dbConnection.displayBooks();
                    System.out.print(boldTextStart +"\033[0;33mEnter book title to remove: \033[0m"+ boldTextEnd);
                    String removeTitle = scanner.nextLine();
                    if (admin.removeBook(removeTitle)) {
                        System.out.println(greenColorStart +"Book removed successfully."+ resetGreenColor);
                    } else {
                        System.out.println(redColorStart +"Book not found."+ resetColor);
                    }
                    break;
                case 4:
                    // Add ISBN
                    System.out.println(boldTextStart +"\033[0;33mCurrent Books in Database: \033[0m"+ boldTextEnd);
                    dbConnection.displayBooks();
                    System.out.print(boldTextStart +"\033[0;33mEnter book title: \033[0m"+ boldTextEnd);
                    String isbnTitle = scanner.nextLine();
                    System.out.print(boldTextStart +"\033[0;33mEnter ISBN: \033[0m"+ boldTextEnd);
                    String newIsbn = scanner.nextLine();
                    if (admin.addISBN(isbnTitle, newIsbn)) {
                        System.out.println(greenColorStart +"ISBN added successfully."+ resetGreenColor);
                    } else {
                        System.out.println(redColorStart +"Failed to add ISBN: Book not found."+ resetColor);
                    }
                    break;
                case 5:
                    // Register new admin
                    System.out.print(boldTextStart +"\033[0;33mEnter new admin username: \033[0m"+ boldTextEnd);
                    String newAdminUsername = scanner.nextLine();
                    System.out.print(boldTextStart +"\033[0;33mEnter new admin password: \033[0m"+ boldTextEnd);
                    String newAdminPassword = scanner.nextLine();
                    if (admin.registerAdmin(newAdminUsername, newAdminPassword)) {
                        System.out.println(greenColorStart +"Admin registered successfully."+ resetGreenColor);
                    } else {
                        System.out.println(redColorStart +"Failed to register admin: Admin already exists."+ resetColor);
                    }
                    break;
                case 6:
                    System.out.println(boldTextStart +"\033[0;33mCurrent Books in Database:\033[0m"+ boldTextEnd);
                    dbConnection.displayBooks();
                    System.out.print(boldTextStart +"\033[0;33mEnter book title: \033[0m"+ boldTextEnd);
                    String tagsTitle = scanner.nextLine();
                    System.out.print(boldTextStart +"\033[0;33mEnter tags to add (comma-separated): \033[0m"+ boldTextEnd);
                    String newTagsInput = scanner.nextLine();
                    List<String> newTags = Arrays.asList(newTagsInput.split(","));
                    if (admin.addTags(tagsTitle, newTags)) {
                        System.out.println(greenColorStart +"Tags added successfully."+ resetGreenColor);
                    } else {
                        System.out.println(redColorStart +"Failed to add tags: Book not found."+ resetColor);
                    }
                    break;
                case 7:
                    System.out.println(greenColorStart +"Exiting admin mode..."+ resetGreenColor);
                    return;
                default:
                    System.out.println(redColorStart +"Invalid choice. Please try again."+ resetColor);

            }
        }
    }

    private static void registerUser(Scanner scanner, DatabaseConnection dbConnection) {
        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";

        System.out.print(boldTextStart +"\033[0;33mEnter user type (Student/Faculty): \033[0m"+ boldTextEnd);
        String userType = scanner.nextLine();

        System.out.print(boldTextStart +"\033[0;33mEnter user name: \033[0m"+ boldTextEnd);
        String userName = scanner.nextLine();

        System.out.print(boldTextStart +"\033[0;33mEnter Pasword: \033[0m"+ boldTextEnd);
        String phoneNum = scanner.nextLine().replace("-", "").replace(" ", "");

        // Using Factory Method generate new user
        User newUser = UserFactory.createUser(userType, userName, phoneNum);

        // Using Singleton DatabaseConnection instance generate new user
        dbConnection.addUser(newUser);

        System.out.println(greenColorStart +"User successfully registered:"+ resetGreenColor);
        newUser.displayInfo();
    }

    private static void loginUser(Scanner scanner, DatabaseConnection dbConnection) {
        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";

        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print(boldTextStart + "\033[0;33mEnter user name for login: \033[0m" + boldTextEnd);
            String userName = scanner.nextLine();

            System.out.print(boldTextStart + "\033[0;33mEnter Password: \033[0m" + boldTextEnd);
            String password = scanner.nextLine(); // 直接从控制台获取密码

            User user = dbConnection.getUser(userName);

            if (user != null && user.getPassword().equals(password)) {
                loggedIn = true;
                System.out.println(greenColorStart + "Login successful:" + resetGreenColor);
                user.displayInfo();
                manageBooks(scanner, dbConnection, user);
            } else {
                System.out.println(redColorStart + "Login failed: User not found or password does not match. Please try again or enter 'exit' to return to the main menu." + resetColor);
                System.out.print(boldTextStart + "\033[0;33mEnter 'exit' to return to the main menu, or press Enter to retry: \033[0m" + boldTextEnd);
                String exitOption = scanner.nextLine();
                if (exitOption.equalsIgnoreCase("exit")) {
                    return; // 退出到重新选择页面
                }
            }
        }
    }



    private static void manageBooks(Scanner scanner, DatabaseConnection dbConnection, User user) {
        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";
        if (user instanceof Faculty) {
            while (true) {
                System.out.println(boldTextStart +"\n1. Add Book"+ boldTextEnd);
                System.out.println(boldTextStart +"2. Remove Book"+ boldTextEnd);
                System.out.println(boldTextStart +"3. View Books"+ boldTextEnd);
                System.out.println(boldTextStart +"4. Logout"+ boldTextEnd);
                System.out.print(boldTextStart +"Enter your choice: "+ boldTextEnd);
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addBook(scanner, dbConnection);
                        break;
                    case 2:
                        removeBook(scanner, dbConnection);
                        break;
                    case 3:
                        dbConnection.displayBooks();
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println(redColorStart +"Invalid choice. Please try again."+ resetColor);
                }
            }
        } else if (user instanceof Student) {
            manageStudentActions(scanner, dbConnection, user);
        }
    }

    private static void manageStudentActions(Scanner scanner, DatabaseConnection dbConnection, User user) {
        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";
        while (true) {
            System.out.println(boldTextStart +"\n1. Borrow Book"+ boldTextEnd);
            System.out.println(boldTextStart +"2. Return Book"+ boldTextEnd);
            System.out.println(boldTextStart +"3. View Borrowed Books"+ boldTextEnd);
            System.out.println(boldTextStart +"4. Logout+ boldTextEnd");
            System.out.print(boldTextStart +"\033[0;33mEnter your choice: \033[0m"+ boldTextEnd);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    borrowBook(scanner, dbConnection, user);
                    break;
                case 2:
                    returnBook(scanner, dbConnection, user);
                    break;
                case 3:
                    viewBorrowedBooks(dbConnection, user);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println(redColorStart +"Invalid choice. Please try again."+ resetColor);
            }
        }
    }

    private static void addBook(Scanner scanner, DatabaseConnection dbConnection) {
        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";

        System.out.print(boldTextStart +"\033[0;33mEnter book title: \033[0m"+ boldTextEnd);
        String title = scanner.nextLine();
        System.out.print(boldTextStart +"\033[0;33mEnter author name: \033[0m"+ boldTextEnd);
        String author = scanner.nextLine();
        System.out.print(boldTextStart +"\033[0;33mEnter publication date: \033[0m"+ boldTextEnd);
        String publicationDate = scanner.nextLine();
        System.out.print(boldTextStart +"\033[0;33mEnter ISBN: \033[0m"+ boldTextEnd);
        String isbn = scanner.nextLine();

        Book book = new Book(title, author, publicationDate, isbn);
        dbConnection.addBook(book);

        System.out.println(greenColorStart +"Book added successfully."+ resetGreenColor);
    }

    private static void removeBook(Scanner scanner, DatabaseConnection dbConnection) {

        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";

        System.out.print((boldTextStart +"\033[0;33mEnter book title to remove: \033[0m"+ boldTextEnd));
        String title = scanner.nextLine();

        if (dbConnection.removeBook(title)) {
            System.out.println((boldTextStart +"\033[0;33mBook removed successfully.\033[0m"+ boldTextEnd));
        } else {
            System.out.println((redColorStart +"Book not found."+ resetColor));
        }
    }

    private static void borrowBook(Scanner scanner, DatabaseConnection dbConnection, User user) {
        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";

        System.out.println((boldTextStart +"\033[0;33mAvailable Books:\033[0m"+ boldTextEnd));
        dbConnection.displayBooks();

        System.out.print((boldTextStart +"\033[0;33mEnter book title to borrow: \033[0m"+ boldTextEnd));
        String title = scanner.nextLine();
        if (dbConnection.borrowBook(title, user.getName())) {
            System.out.println(greenColorStart +title + " has been borrowed successfully."+ resetGreenColor);
        } else {
            System.out.println(redColorStart +"Failed to borrow " + title + "."+ resetColor);
        }
    }

    private static void returnBook(Scanner scanner, DatabaseConnection dbConnection, User user) {

        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";

        System.out.print(boldTextStart +"\033[0;33mEnter book title to return: \033[0m"+ boldTextEnd);
        String title = scanner.nextLine();
        if (dbConnection.returnBook(title, user.getName())) {
            System.out.println(greenColorStart +title + " has been returned successfully."+ resetGreenColor);
        } else {
            System.out.println(redColorStart +"Failed to return " + title + "."+ resetColor);
        }
    }

    private static void viewBorrowedBooks(DatabaseConnection dbConnection, User user) {

        String redColorStart = "\u001B[31m";
        String resetColor = "\u001B[0m";

        String boldTextStart = "\u001B[1m";
        String boldTextEnd = "\u001B[0m";

        String greenColorStart = "\u001B[32m";
        String resetGreenColor = "\u001B[0m";



        List<BookDecorator> borrowedBooks = dbConnection.getBorrowedBooks(user.getName());
        if (borrowedBooks.isEmpty()) {
            System.out.println(redColorStart +"You have not borrowed any books."+ resetColor);
        } else {
            System.out.println("Borrowed Books:");
            for (BookDecorator bookDecorator : borrowedBooks) {
                bookDecorator.displayInfo();
            }
        }
    }

}
