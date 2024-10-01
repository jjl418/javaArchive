package juliannelee_CSCI201_lab2;

public interface UbaInterface<T>
{
    // Inserts an item at the end of the list
    public void add(T obj);

    // Removes the last item from the list and returns it
    public T remove();

    // Returns a string representation of the list
    public String toString();
    
}
