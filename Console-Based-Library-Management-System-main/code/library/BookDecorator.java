package library;

public class BookDecorator {
    private Book book;
    private boolean isOverdue;
    private double overdueFee;

    public BookDecorator(Book book) {
        this.book = book;
        this.isOverdue = false;
        this.overdueFee = 0.0;
    }

    public void setOverdue(boolean isOverdue) {
        this.isOverdue = isOverdue;
    }

    public boolean isOverdue() {
        return this.isOverdue;
    }

    public void setOverdueFee(double overdueFee) {
        this.overdueFee = overdueFee;
    }

    public double getOverdueFee() {
        return this.overdueFee;
    }

    public void displayInfo() {
        book.displayInfo();
        System.out.println("Overdue: " + (isOverdue ? "Yes" : "No"));
        if (isOverdue) {
            System.out.println("Overdue Fee: $" + overdueFee);
        }
    }

    public Book getBook() {
        return book;
    }
}
