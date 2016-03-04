import java.util.Observer;

public interface Subject {
    public void registerUser(Observer user);

    public void notifyUser();

    public void unregisterUser(Observer user);
}