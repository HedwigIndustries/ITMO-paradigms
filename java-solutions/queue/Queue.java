package queue;


public interface Queue {
    // Model: q[0]..q[size-1] (current queue size) && q[i] != null && size >=0;

    // P: true;
    int size();
    // Q: return size of queue;

    // P: true;
    boolean isEmpty();
    // Q: return true if queue is empty;

    // P: true;
    void enqueue(Object element);
    // Q: size' = size + 1 && q'= q[0..size()-1] + element;

    // P: true;
    Object dequeue();
    // Q: size' = size -1 && q'=q[1..size()-1] && return q[0] != null;

    // P: true;
    Object element();
    // Q: return q[0] != null;

    // P: true;
    void clear();
    // Q: q = [] && (size == 0)

    // P: true;
    int count(Object object);

    // Q: return value of the number of occurrences of an element in the queue
    int indexOf(Object object);

    int lastIndexOf(Object object);


}
