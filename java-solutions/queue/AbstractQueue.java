package queue;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    public Object dequeue() {
        assert size != 0;
        Object result = element();
        dequeueImpl();
        size--;
        return result;
    }

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    public void clear() {
        clearImpl();
        size = 0;
    }

    public int count(Object element) {
        if (isEmpty()) return 0;
        return countImpl(element);
    }

    public int indexOf(Object element) {
        return indexOfImpl(element);
    }

    public int lastIndexOf(Object element) {
        return lastIndexOfImpl(element);
    }

    // P: element != null;
    protected abstract void enqueueImpl(final Object element);
    // Q: q' = immutable q[0:size-1] + [element] && immutable size;

    // P: size() != 0;
    protected abstract void dequeueImpl();
    // Q: q' = q[1..size()-1] && immutable size;

    // P: size > 0;
    protected abstract Object elementImpl();
    // Q: immutable q[0:size-1] && immutable size && return q[0] != null;

    // P: true;
    protected abstract void clearImpl();
    // Q: q' = [];

    // P: element != null;
    protected abstract int countImpl(Object element);
    // Q: return value of the number of occurrences of an element in the queue

    protected abstract int indexOfImpl(Object element);

    protected abstract int lastIndexOfImpl(Object element);

}

