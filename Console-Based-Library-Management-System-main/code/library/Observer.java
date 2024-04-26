package library;

// The Observer interface declares the update method that is called by the subject.
// Observers implement this interface to react to changes in the subject's state.
public interface Observer {
    void update(String availability);
}
