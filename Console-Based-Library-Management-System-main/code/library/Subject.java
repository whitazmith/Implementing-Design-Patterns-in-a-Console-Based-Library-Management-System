package library;

// The Subject interface declares a set of methods for managing observers.
// It allows subjects to register, remove, and notify observers about the state changes.
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}