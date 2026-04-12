package level3.librarymanagement.model;

import java.sql.Timestamp;

public class Transaction {

    private int issueId;
    private int bookId;
    private int userId;
    private Timestamp issueDate;
    private Timestamp returnDate;
    private String status;

    public Transaction() {}

    public Transaction(int bookId, int userId, String status) {
        this.bookId = bookId;
        this.userId = userId;
        this.status = status;
    }

    public int getIssueId() { return issueId; }
    public int getBookId() { return bookId; }
    public int getUserId() { return userId; }
    public Timestamp getIssueDate() { return issueDate; }
    public Timestamp getReturnDate() { return returnDate; }
    public String getStatus() { return status; }

    public void setIssueId(int issueId) { this.issueId = issueId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setReturnDate(Timestamp returnDate) { this.returnDate = returnDate; }
    public void setStatus(String status) { this.status = status; }
}

