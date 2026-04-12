package level3.librarymanagement.model;

import java.sql.Date;

public class IssuedBook {

    private int issueId;
    private String bookTitle;
    private String userName;
    private Date issueDate;
    private Date dueDate;

    public IssuedBook(int issueId, String bookTitle, String userName,
                      Date issueDate, Date dueDate) {
        this.issueId = issueId;
        this.bookTitle = bookTitle;
        this.userName = userName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getIssueId() { return issueId; }
    public String getBookTitle() { return bookTitle; }
    public String getUserName() { return userName; }
    public Date getIssueDate() { return issueDate; }
    public Date getDueDate() { return dueDate; }
}