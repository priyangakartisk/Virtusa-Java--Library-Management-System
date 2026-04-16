import java.util.*;

// ----------- Book Class -----------
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;
    int issueDay;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issueDay = 0;
    }
}

// ----------- Library Class -----------
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Add Book
    void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("✅ Book added successfully!\n");
    }

    // View Books
    void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.\n");
            return;
        }

        System.out.println("\n--- Book List ---");
        for (Book b : books) {
            System.out.println("ID: " + b.id +
                    " | Title: " + b.title +
                    " | Author: " + b.author +
                    " | Status: " + (b.isIssued ? "Issued" : "Available"));
        }
        System.out.println();
    }

    // Search Book
    void searchBook() {
        sc.nextLine();
        System.out.print("Enter title to search: ");
        String search = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Book b : books) {
            if (b.title.toLowerCase().contains(search)) {
                System.out.println("Found -> ID: " + b.id +
                        " | Title: " + b.title +
                        " | Author: " + b.author);
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ Book not found.\n");
        }
    }

    // Issue Book
    void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                if (b.isIssued) {
                    System.out.println("❌ Book already issued.\n");
                    return;
                }

                System.out.print("Enter issue day (1-30): ");
                int day = sc.nextInt();

                b.isIssued = true;
                b.issueDay = day;

                System.out.println("✅ Book issued successfully!\n");
                return;
            }
        }

        System.out.println("❌ Book not found.\n");
    }

    // Return Book
    void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                if (!b.isIssued) {
                    System.out.println("❌ This book was not issued.\n");
                    return;
                }

                System.out.print("Enter return day (1-30): ");
                int returnDay = sc.nextInt();

                int daysKept = returnDay - b.issueDay;
                int fine = 0;

                if (daysKept > 7) {
                    fine = (daysKept - 7) * 10; // ₹10 per extra day
                }

                b.isIssued = false;
                b.issueDay = 0;

                System.out.println("✅ Book returned successfully!");
                if (fine > 0) {
                    System.out.println("💰 Fine: ₹" + fine);
                }
                System.out.println();
                return;
            }
        }

        System.out.println("❌ Book not found.\n");
    }
}

// ----------- Main Class -----------
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {
            System.out.println("===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    lib.addBook();
                    break;
                case 2:
                    lib.viewBooks();
                    break;
                case 3:
                    lib.searchBook();
                    break;
                case 4:
                    lib.issueBook();
                    break;
                case 5:
                    lib.returnBook();
                    break;
                case 6:
                    System.out.println("Thank you! Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }
}