package level3.librarymanagement.dao;

import level3.librarymanagement.model.Book;
import level3.librarymanagement.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // ADD BOOK
    public void addBook(Book book) {

        String sql = "INSERT INTO books (title, author, category, isbn, publisher, total_copies, available_copies, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getPublisher());
            ps.setInt(6, book.getTotalCopies());
            ps.setInt(7, book.getAvailableCopies());
            ps.setString(8, book.getStatus());

            ps.executeUpdate();
            System.out.println("Book added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL BOOKS
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies"),
                        rs.getString("status")
                );

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    // DASHBOARD METHOD
    public int getTotalBooks() {
        int count = 0;

        String sql = "SELECT COUNT(*) FROM books";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
    //Updates Book
    public void updateBook(int id, String title, String author, String isbn,
                           String category, String publisher, int copies) {

        String sql = "UPDATE books SET title=?, author=?, isbn=?, category=?, publisher=?, total_copies=?, available_copies=? WHERE book_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, isbn);
            ps.setString(4, category);
            ps.setString(5, publisher);
            ps.setInt(6, copies);
            ps.setInt(7, copies); // you can adjust logic later
            ps.setInt(8, id);

            ps.executeUpdate();

            System.out.println("Book updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Book getBookById(int id) {
        Book book = null;

        String sql = "SELECT * FROM books WHERE book_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies"),
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }
}