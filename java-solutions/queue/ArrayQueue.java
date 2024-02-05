package queue;

public class ArrayQueue extends AbstractQueue implements Queue {
    private final int DEFAULT_SIZE = 10;
    private Object[] data = new Object[DEFAULT_SIZE];
    private int start = 0;
    private int end = 0;

    @Override
    public void enqueueImpl(Object element) {
        ensureCapacity(size() + 1);
        data[end] = element;
        end = (end + 1) % (data.length);
    }

    @Override
    public void dequeueImpl() {
        data[start] = null;
        start = (start + 1) % (data.length);
    }

    @Override
    public Object elementImpl() {
        return data[start];
    }

    @Override
    public void clearImpl() {
        data = new Object[2];
        start = 0;
        end = 0;
    }

    @Override
    public int countImpl(Object element) {
        Object[] newQueue = queueToArray(new Object[size]);
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (newQueue[i].equals(element)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public int indexOfImpl(Object element) {
        Object[] newQueue = queueToArray(new Object[size]);
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (newQueue[i].equals(element)) {
                return result;
            }
            result++;
        }
        return -1;
    }

    @Override
    public int lastIndexOfImpl(Object element) {
        Object[] newQueue = queueToArray(new Object[size]);
        int result1 = 0;
        int result2 = -1;
        for (int i = 0; i < size; i++) {
            if (newQueue[i].equals(element)) {
                result2 = result1;
            }
            result1++;
        }
        return result2;
    }

    private Object[] queueToArray(Object[] newData) {
        if (start <= end) {
            System.arraycopy(data, start, newData, 0, end - start);
        } else {
            System.arraycopy(data, start, newData, 0, data.length - start);
            System.arraycopy(data, 0, newData, data.length - start, end);
        }
        return newData;
    }

    public String toStr() {
        StringBuilder result = new StringBuilder("[");
        int count = size();
        while (count != 0) {
            if (count == 1) {
                result.append(data[(start + size() - count) % data.length]);
            } else {
                result.append(data[(start + size() - count) % data.length]).append(", ");
            }
            count--;
        }
        result.append("]");
        return result.toString();
    }

    public Object[] toArray() {
        int count = size();
        Object[] result = new Object[count];
        while (count != 0) {
            result[size() - count] = data[(start + size() - count) % data.length];
            count--;
        }
        return result;
    }

    private void ensureCapacity(int size) {
        if (size != data.length) {
            return;
        }
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, start, newData, 0, size - start);
        System.arraycopy(data, 0, newData, size - start, end);
        start = 0;
        end = size - 1;
        data = newData;
    }

}
