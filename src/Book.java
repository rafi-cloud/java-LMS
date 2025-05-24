public class Book {
    private String ISBN;
    private String title;
    private String author;
    private String category;
    private double price;

    public Book() {
        this.ISBN = "";
        this.title = "";
        this.author = "";
        this.category = "";
        this.price = 0.0;
    }

    public Book(String ISBN, String title, String author, String category, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Category: %s | Price: $%.2f",
                ISBN, title, author, category, price);
    }
}
