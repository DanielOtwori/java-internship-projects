package level3.librarymanagement.dao;

import level3.librarymanagement.db.DBConnection;
import level3.librarymanagement.model.IssuedBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssuedBookDAO {

    // 🔹 GET ALL ISSUED BOOKS (NOT RETURNED)
    public List<IssuedBook> getAllIssuedBooks() {

        List<IssuedBook> list = new ArrayList<>();

        String sql = "SELECT i.issue_id, b.title, u.first_name, u.last_name, i.issue_date, i.due_date " +
                "FROM issued_books i " +
                "JOIN books b ON i.book_id = b.book_id " +
                "JOIN users u ON i.user_id = u.user_id " +
                "WHERE i.status = 'ISSUED'";

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
                        rs.getDate("due_date")
                );

                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔹 RETURN BOOK (SIMPLE VERSION)
    public void returnBook(int issueId) {

        String sql = "UPDATE issued_books SET status='RETURNED', return_date=NOW() WHERE issue_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, issueId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 RETURN BOOK (FULL VERSION USED BY YOUR SERVLET)
    public void returnBook(int issueId, String returnDate, String condition, String notes) {

        String sql = "UPDATE issued_books SET status='RETURNED', return_date=?, condition=?, notes=? WHERE issue_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, returnDate);
            ps.setString(2, condition);
            ps.setString(3, notes);
            ps.setInt(4, issueId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}