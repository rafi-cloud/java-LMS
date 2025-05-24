import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }

    public void removeBook(String ISBN) {
        books.removeIf(book -> book.getISBN().equals(ISBN));
    }

    public Book findBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) return book;
        }
        return null;
    }

    public void registerMember(Member member) { members.add(member); }

    public void removeMember(int memberID) {
        members.removeIf(member -> member.getMemberID() == memberID);
    }

    public Member findMember(int memberID) {
        for (Member member : members) {
            if (member.getMemberID() == memberID) return member;
        }
        return null;
    }

    public boolean borrowBook(int memberID, String ISBN) {
        Member member = findMember(memberID);
        Book book = findBook(ISBN);
        if (member != null && book != null && member.addBook(book)) {
            books.remove(book);
            return true;
        }
        return false;
    }

    public double returnBook(int memberID, String ISBN, int daysOverdue) {
        Member member = findMember(memberID);
        if (member != null) {
            for (Book book : member.getBorrowedBooks()) {
                if (book.getISBN().equals(ISBN)) {
                    double fee = member.calculateLateFee(book, daysOverdue);
                    member.removeBook(book);
                    books.add(book);
                    return fee;
                }
            }
        }
        return -1;
    }

    public ArrayList<Book> getBooks() { return books; }
}
