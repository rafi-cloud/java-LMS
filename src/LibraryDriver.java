import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LibraryDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Initialize sample data
        Book book1 = new Book("111", "Java Basics", "Smith", "Education", 55);
        Book book2 = new Book("222", "AI Future", "John", "Science", 70);
        Book book3 = new Book("333", "History 101", "Alice", "History", 40);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        Member member1 = new Member(1, "Alice", "Regular");
        Member member2 = new Member(2, "Bob", "Premium");
        Member member3 = new Member(3, "Carol", "Guest");

        library.registerMember(member1);
        library.registerMember(member2);
        library.registerMember(member3);

        member1.addBook(book1);
        member2.addBook(book2);
        member3.addBook(book3);

        // Display header once
        System.out.println("****************************************");
        System.out.println("WELCOME TO LIBRARY MANAGEMENT SYSTEM\n");
        System.out.println("    Developed by: GROUP 3");
        System.out.println("    ---------------------------------------");
        System.out.println("    • Rafi Miazi (K250249) - Team Leader");
        System.out.println("    • Utsav Paudel (K250066)");
        System.out.println("    • Thi Phuong Thanh (K123456)");
        System.out.println("OODP101 Object Oriented Design and Programming - Group Assignment");
        System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println("****************************************");

        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1: // Borrow
                    System.out.print("Enter your Member ID: ");
                    int borrowMemberID = sc.nextInt();
                    Member borrowMember = library.findMember(borrowMemberID);

                    if (borrowMember == null) {
                        System.out.println("Invalid Member ID.");
                        break;
                    }

                    System.out.println("Available books:");
                    for (Book book : library.getBooks()) {
                        System.out.println(book);
                    }
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = sc.next();
                    Book selectedBook = library.findBook(borrowIsbn);

                    if (selectedBook != null) {
                        boolean success = borrowMember.addBook(selectedBook);
                        if (success) {
                            Transaction t = new Transaction(borrowMember, selectedBook);
                            System.out.println("Book borrowed successfully.");
                            System.out.println(t);
                        } else {
                            System.out.println("Borrowing limit reached. Cannot borrow more books.");
                        }
                    } else {
                        System.out.println("Book not found with ISBN: " + borrowIsbn);
                    }
                    break;

                case 2: // Return
                    System.out.print("Enter your Member ID: ");
                    int returnMemberID = sc.nextInt();
                    Member returnMember = library.findMember(returnMemberID);

                    if (returnMember == null) {
                        System.out.println("Invalid Member ID.");
                        break;
                    }

                    System.out.println("Your borrowed books:");
                    for (Book book : returnMember.getBorrowedBooks()) {
                        System.out.println(book);
                    }
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = sc.next();
                    System.out.print("Enter number of days overdue: ");
                    int daysOverdue = sc.nextInt();
                    double fee = library.returnBook(returnMemberID, returnIsbn, daysOverdue);
                    if (fee >= 0) {
                        System.out.printf("Book returned. Late fee: $%.2f\n", fee);
                    } else {
                        System.out.println("Return failed. Book not found in your borrowed list.");
                    }
                    break;

                case 3: // Exit
                    running = false;
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}