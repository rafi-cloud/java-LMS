import java.util.ArrayList;

public class Library {
    private static ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public static void addBook(Book book) { books.add(book); }

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

    public void borrowBook(int memberID, String ISBN) {
        Member member = findMember(memberID);
        Book book = findBook(ISBN);
        if (member != null && book != null ) {
            if (member.addBook(book)) {
                System.out.println("Book borrowed successfully.");
                Transaction t = new Transaction(member, book);
                System.out.println(t.toString());
            }else {
                System.out.println("Borrowing limit reached. Cannot borrow more books.");
            }
        }
        System.out.println("Invalid ISBN or member found. Cannot borrow books.");
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
