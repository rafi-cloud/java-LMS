import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LibraryDriver {



    public static boolean adminCheck () {

        Scanner sc = new Scanner(System.in);
        System.out.println("Checking Admin Privilege...");
        System.out.println("Admin Privilege = REQUIRED");
        System.out.print("\n Enter Admin ID: ");
        String adminID = sc.next();

        if (adminID.equals("admin")) {
            System.out.print(" Enter Admin Password: ");
            String adminPassword = sc.next();
            if (adminPassword.equals("admin")) {
                System.out.println(" Admin Privilege Granted.");
                System.out.println();
                return true;
            }
            System.out.println("Invalid Admin Password");
            return false;
        }
        System.out.println("Invalid Admin ID");
        return false;
    }


    public static void main(String[] args) {

        boolean hasAdminPrivilege = false;

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
        System.out.println("    • Thi Phuong Thanh bui (K250200)");
        System.out.println("OODP101 Object Oriented Design and Programming - Group Assignment");
        System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println("****************************************");

        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. Add Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Add Member");
            System.out.println("6. Remove Member");
            System.out.println("7. View Transactions");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1: // Borrow
                    System.out.print("Enter your Member ID: ");
                    int borrowMemberID = sc.nextInt();

                    System.out.println("Available books:");
                    for (Book book : library.getBooks()) {
                        System.out.println(book);
                    }
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = sc.next();
                    library.borrowBook(borrowMemberID, borrowIsbn);
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
                case 3:
                    if (!hasAdminPrivilege) {
                        if(adminCheck()) {
                            hasAdminPrivilege = true;
                        }else{
                            System.out.println("Access denied. Please try again.");
                            break;
                        }
                    }else {
                        System.out.println("Checking Admin Privilege");
                        System.out.println("Admin Privilege = OK\n");
                    }
                    System.out.println("=== Enter book information ===");

                    // ISBN input
                    System.out.print("Enter ISBN (3 digits): ");
                    String ISBN = sc.next();

                    // Title input
                    System.out.print("Enter book title: ");
                    String title = sc.next();
                    sc.nextLine(); // Consume remaining newline

                    // Author input
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();

                    // Category input
                    System.out.print("Enter book category: ");
                    String category = sc.nextLine();

                    // Price input
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();

                    // Create Book object
                    Book newBook = new Book(ISBN, title, author, category, price);
                    Library.addBook(newBook);

                    System.out.println("\nNew Book Added:");
                    System.out.println("Available books:");
                    for (Book book : library.getBooks()) {
                        System.out.println(book);
                    }

                    break;
                case 4:
                    if (!hasAdminPrivilege) {
                        if(adminCheck()) {
                            hasAdminPrivilege = true;
                        }else{
                            System.out.println("Access denied. Please try again.");
                            break;
                        }
                    }else {
                        System.out.println("Checking Admin Privilege");
                        System.out.println("Admin Privilege = OK\n");
                    }
                    sc.nextLine();
                    System.out.println("Select Book to Remove:");
                    for (Book book : library.getBooks()) {
                        System.out.println(book);
                    }
                    System.out.print("Enter ISBN (3 digits): ");
                    String isbn = sc.nextLine();
                    library.removeBook(isbn);
                    System.out.println("Book Removed\n");
                    System.out.println("Available books:");
                    for (Book book : library.getBooks()) {
                        System.out.println(book);
                    }
                    break;

                case 5:
                case 6:
                case 7:
                case 8: // Exit
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