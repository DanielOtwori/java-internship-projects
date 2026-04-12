package level3.librarymanagement.dao;

import level3.librarymanagement.db.DBConnection;
import level3.librarymanagement.model.IssuedBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssuedBookDAO {

    // GET ALL ISSUED BOOKS (INCLUDING STATUS + DUE DATE)
    public List<IssuedBook> getAllIssuedBooks() {

        List<IssuedBook> list = new ArrayList<>();

        String sql = "SELECT i.issue_id, b.title, u.first_name, u.last_name, " +
                "i.issue_date, i.due_date, i.status " +
                "FROM issued_books i " +
                "JOIN books b ON i.book_id = b.book_id " +
                "JOIN users u ON i.user_id = u.user_id " +
                "ORDER BY i.issue_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");

                IssuedBook book = new IssuedBook(
                        rs.getInt("issue_id"),
                        rs.getString("title"),
                        fullName,
                        rs.getDate("issue_date"),
                        rs.getDate("due_date"),
                        rs.getString("status")
                );

                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //GET ONLY CURRENTLY ISSUED BOOKS (FOR DASHBOARD)
    public List<IssuedBook> getActiveIssuedBooks() {

        List<IssuedBook> list = new ArrayList<>();

        String sql = "SELECT i.issue_id, b.title, u.first_name, u.last_name, " +
                "i.issue_date, i.due_date, i.status " +
                "FROM issued_books i " +
                "JOIN books b ON i.book_id = b.book_id " +
                "JOIN users u ON i.user_id = u.user_id " +
                "WHERE i.status = 'ISSUED' " +
                "ORDER BY i.issue_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");

                IssuedBook book = new IssuedBook(
                        rs.getInt("issue_id"),
                        rs.getString("title"),
                        fullName,
                        rs.getDate("issue_date"),
                        rs.getDate("due_date"),
                        rs.getString("status")
                );

                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // RETURN BOOK (CALLS TransactionDAO LOGIC IF NEEDED)
    public boolean returnBook(int issueId) {

        String sql = "UPDATE issued_books SET status='RETURNED', return_date=NOW() WHERE issue_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, issueId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}