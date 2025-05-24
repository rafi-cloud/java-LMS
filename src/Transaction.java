import java.time.LocalDate;
public class Transaction {
    private Member member;
    private Book book;
    private LocalDate borrowDate;


    public Transaction(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.borrowDate = LocalDate.now();
    }

    public LocalDate calculateDueDate() {
        return switch (member.getMembershipType().toLowerCase()) {
            case "regular" -> borrowDate.plusWeeks(2);
            case "premium" -> borrowDate.plusWeeks(4);
            case "guest" -> borrowDate.plusWeeks(1);
            default -> borrowDate;
        };
    }

    @Override
    public String toString() {
        return "\n--- Transaction Receipt ---\n" +
                "Member: " + member.getName() +
                "\nBook: " + book.getTitle() +
                "\nBorrow Date: " + borrowDate +
                "\nDue Date: " + calculateDueDate() +
                "\n----------------------------";
    }
}
