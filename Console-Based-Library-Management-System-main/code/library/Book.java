package library;

import java.util.ArrayList;
import java.util.List;

public class Book implements Subject {
    private List<Observer> observers = new ArrayList<>(); // Holds references to observers.
    private String title;
    private String author; 
    private String publicationDate; 
    private String ISBN;
    private boolean isAvailable;
    private List<String> tags;


    
    public Book(String title, String author, String publicationDate, String ISBN) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.isAvailable ? "Available" : "Not Available");
        }
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        notifyObservers();
    }

    
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publication Date: " + publicationDate);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return ISBN;
    }

    public void setIsbn(String isbn) {
        this.ISBN = isbn;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
    public String getTagsAsString() {
        return String.join(", ", tags);
    }

}
