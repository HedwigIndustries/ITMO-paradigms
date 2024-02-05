package queue;

public class ArrayQueueModule {
    // Model: q[0]..q[size-1] (current queue size) && size >=0 && q[i]! = null &&
    //      && data.length >= size &&
    //      && start in [0..data.length - 1] && end in [0..data.length - 1]

    private static Object[] data = new Object[2];
    private static int start = 0;
    private static int end = 0;

    // P: true;
    public static int size() {
        if (start > end) {
            return data.length - start + end;
        } else {
            return end - start;
        }
    }
    // Q: return size of q

    // P: true;
    private static void ensureCapacity(int size) {
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
    // Q: data size increase if (size = data.length) &&
    //      end = size - 1 &&
    //      start = 0;

    // P: element != null;
    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size() + 1);
        data[end] = element;
        end = (end + 1) % (data.length);
    }
    // Q: q[end] == element &&
    //      end increase cyclically &&
    //      data size increase if needed;

    // P: size != 0;
    public static Object dequeue() {
        assert !isEmpty();
        Object result = element();
        data[start] = null;
        start = (start + 1) % (data.length);
        return result;
    }
    // Q: return data[start] != null &&
    //      data[start] = null &&
    //      start increase cyclically

    // P: true;
    public static void clear() {
        data = new Object[2];
        start = 0;
        end = 0;
    }
    // Q: data = Object[2] && start = 0 && end = 0;

    // P: true
    public static String toStr() {
        StringBuilder result = new StringBuilder("[");
        int count = size();
        while (count != 0) {
            if (count == 1) {
                result.append(data[(start + size() - count) % data.length].toString());
            } else {
                result.append(data[(start + size() - count) % data.length].toString()).append(", ");
            }
            count--;
        }
        result.append("]");
        return result.toString();
    }
    // Q: return new string of data values

    public static Object[] toArray() {
        int count = size();
        Object[] result = new Object[count];
        while (count != 0) {
            result[size()-count] = data[(start + size() - count) % data.length];
            count--;
        }
        return result;
    }

    // P: size > 0
    public static Object element() {
        assert size() > 0;
        return data[start];
    }
    // Q: return data[start] != null &&
    //      immutable data

    // P: true;
    public static boolean isEmpty() {
        return size() == 0;
    }
    // Q: return boolean of predicate "size==0";


}
