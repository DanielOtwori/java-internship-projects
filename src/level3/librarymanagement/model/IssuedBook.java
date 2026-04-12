package level3.librarymanagement.model;

import java.sql.Date;

public class IssuedBook {

    private int issueId;
    private String bookTitle;
    private String userName;
    private Date issueDate;
    private Date dueDate;
    private String status;

    //FULL CONSTRUCTOR
    public IssuedBook(int issueId, String bookTitle, String userName,
                      Date issueDate, Date dueDate, String status) {
        this.issueId = issueId;
        this.bookTitle = bookTitle;
        this.userName = userName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // DEFAULT CONSTRUCTOR
    public IssuedBook() {
    }

    // GETTERS
    public int getIssueId() { return issueId; }
    public String getBookTitle() { return bookTitle; }
    public String getUserName() { return userName; }
    public Date getIssueDate() { return issueDate; }
    public Date getDueDate() { return dueDate; }
    public String getStatus() { return status; }

    // SETTERS
    public void setIssueId(int issueId) { this.issueId = issueId; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public void setStatus(String status) { this.status = status; }
}