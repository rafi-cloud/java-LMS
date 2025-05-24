import java.util.ArrayList;

public class Member {
    private int memberID;
    private String name;
    private String membershipType;
    private ArrayList<Book> borrowedBooks;

    public Member() {
        this.memberID = 0;
        this.name = "";
        this.membershipType = "Regular";
        this.borrowedBooks = new ArrayList<>();
    }

    public Member(int memberID, String name, String membershipType) {
        this.memberID = memberID;
        this.name = name;
        this.membershipType = membershipType;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberID() { return memberID; }
    public void setMemberID(int memberID) { this.memberID = memberID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }

    public boolean addBook(Book book) {
        int limit = switch (membershipType) {
            case "Premium" -> 5;
            case "Guest" -> 1;
            default -> 3;
        };
        if (borrowedBooks.size() < limit) {
            borrowedBooks.add(book);
            return true;
        }
        return false;
    }

    public void removeBook(Book book) {
        borrowedBooks.remove(book);
    }

    public double calculateLateFee(Book book, int daysOverdue) {
        if (daysOverdue <= 0) return 0;
        return book.getPrice() * 0.01 * daysOverdue;
    }

    @Override
    public String toString() {
        return "ID: " + memberID + " | Name: " + name + " | Type: " + membershipType + "\nBorrowed Books: " + borrowedBooks;
    }
}
