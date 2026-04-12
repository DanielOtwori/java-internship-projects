package level3.librarymanagement.dao;

import level3.librarymanagement.db.DBConnection;

import java.sql.*;

public class TransactionDAO {

    // BORROW BOOK
    public void borrowBook(int userId, int bookId) {

        String checkSql = "SELECT available_copies FROM books WHERE book_id = ?";
        String insertSql = "INSERT INTO book_issued (book_id, user_id, status) VALUES (?, ?, ?)";
        String updateSql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("available_copies") > 0) {

                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, userId);
                insertStmt.setString(3, "BORROWED");
                insertStmt.executeUpdate();

                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                conn.commit();
                System.out.println("Book borrowed successfully!");

            } else {
                System.out.println("Book not available!");
                conn.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // RETURN BOOK
    public void returnBook(int issueId, int bookId) {

        String updateTransaction = "UPDATE book_issued SET return_date = CURRENT_TIMESTAMP, status = 'RETURNED' WHERE issue_id = ?";
        String updateBook = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            PreparedStatement stmt1 = conn.prepareStatement(updateTransaction);
            stmt1.setInt(1, issueId);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement(updateBook);
            stmt2.setInt(1, bookId);
            stmt2.executeUpdate();

            conn.commit();
            System.out.println("Book returned successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DASHBOARD METHODS
    //Count borrowed books
    public int getBorrowedBooks() {
        int count = 0;

        String sql = "SELECT COUNT(*) FROM book_issued WHERE status = 'BORROWED'";

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

    //Count returned books
    public int getReturnedBooks() {
        int count = 0;

        String sql = "SELECT COUNT(*) FROM book_issued WHERE status = 'RETURNED'";

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
}