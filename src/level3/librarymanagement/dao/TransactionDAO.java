package level3.librarymanagement.dao;

import level3.librarymanagement.db.DBConnection;

import java.sql.*;

public class TransactionDAO {

    // BORROW BOOK (WITH DUE DATE)
    public boolean borrowBook(int userId, int bookId, String dueDate) {

        String checkSql = "SELECT available_copies FROM books WHERE book_id = ?";
        String insertSql = "INSERT INTO issued_books (book_id, user_id, issue_date, due_date, status) VALUES (?, ?, NOW(), ?, ?)";
        String updateSql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // CHECK AVAILABLE COPIES
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

                checkStmt.setInt(1, bookId);
                ResultSet rs = checkStmt.executeQuery();

                if (!rs.next() || rs.getInt("available_copies") <= 0) {
                    conn.rollback();
                    return false;
                }
            }

            // INSERT INTO issued_books (WITH due_date)
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, userId);
                insertStmt.setDate(3, Date.valueOf(dueDate)); // 🔥 FIX
                insertStmt.setString(4, "ISSUED");

                insertStmt.executeUpdate();
            }

            // UPDATE books
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();

            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    // RETURN BOOK
    public boolean returnBook(int issueId) {

        String getBookSql = "SELECT book_id, status FROM issued_books WHERE issue_id = ?";
        String updateIssueSql = "UPDATE issued_books SET return_date = NOW(), status = 'RETURNED' WHERE issue_id = ?";
        String updateBookSql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            int bookId;

            // GET BOOK + CHECK STATUS
            try (PreparedStatement getStmt = conn.prepareStatement(getBookSql)) {

                getStmt.setInt(1, issueId);
                ResultSet rs = getStmt.executeQuery();

                if (!rs.next()) {
                    conn.rollback();
                    return false;
                }

                // Already returned
                if ("RETURNED".equalsIgnoreCase(rs.getString("status"))) {
                    conn.rollback();
                    return false;
                }

                bookId = rs.getInt("book_id");
            }

            // UPDATE issued_books
            try (PreparedStatement stmt1 = conn.prepareStatement(updateIssueSql)) {

                stmt1.setInt(1, issueId);
                stmt1.executeUpdate();
            }

            // UPDATE books
            try (PreparedStatement stmt2 = conn.prepareStatement(updateBookSql)) {

                stmt2.setInt(1, bookId);
                stmt2.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();

            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    // DASHBOARD STATS

    public int getBorrowedBooks() {

        int count = 0;
        String sql = "SELECT COUNT(*) FROM issued_books WHERE status = 'ISSUED'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) count = rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public int getReturnedBooks() {

        int count = 0;
        String sql = "SELECT COUNT(*) FROM issued_books WHERE status = 'RETURNED'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) count = rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}