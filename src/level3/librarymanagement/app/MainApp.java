package level3.librarymanagement.app;

import level3.librarymanagement.dao.BookDAO;
import level3.librarymanagement.dao.TransactionDAO;
import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.model.Book;
import level3.librarymanagement.model.User;

public class MainApp {
    public static void main(String[] args) {

            TransactionDAO tdao = new TransactionDAO();

            int userId = 1;
            int bookId = 1;
            int issueId = 1;

            System.out.println("----- BORROW BOOK -----");
            tdao.borrowBook(userId, bookId);

            System.out.println("----- RETURN BOOK -----");
            tdao.returnBook(issueId, bookId);



        //BOOK TEST
        BookDAO bookDAO = new BookDAO();

        Book book = new Book(
                "Clean Code",
                "Robert Martin",
                "Programming",
                "123456",
                "Prentice Hall",
                10,
                10,
                "AVAILABLE"
        );

        bookDAO.addBook(book);

        System.out.println("\n--- BOOKS ---");
        bookDAO.getAllBooks().forEach(System.out::println);


        //USER TEST
        UserDAO userDAO = new UserDAO();

        User user = new User(
                "John",
                "Doe",
                "john@example.com",
                "1234",
                "USER",
                "0700000000",
                "Mersin"
        );

        userDAO.addUser(user);

        System.out.println("\n--- USERS ---");
        userDAO.getAllUsers().forEach(u ->
                System.out.println(u.getFirstName() + " " + u.getLastName())
        );
    }
}