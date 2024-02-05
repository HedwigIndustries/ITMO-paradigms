package queue;

public class ArrayQueueADT {
    // Model: q[0]..q[size-1] (current queue size) && size >=0 && q[i] != null &&
    //      && data.length >= size &&
    //      && start in [0..data.length - 1] && end in [0..data.length - 1]

    private Object[] data = new Object[2];
    private int start = 0;
    private int end = 0;

    // P: true;
    public static int size(final ArrayQueueADT q) {

        if (q.start > q.end) {
            return q.data.length - q.start + q.end;
        } else {
            return q.end - q.start;
        }
    }
    // Q: return size of q

    // P: true;
    private static void ensureCapacity(final ArrayQueueADT q, int size) {
        if (size != q.data.length) {
            return;
        }
        Object[] newData = new Object[q.data.length * 2];
        System.arraycopy(q.data, q.start, newData, 0, size - q.start);
        System.arraycopy(q.data, 0, newData, size - q.start, q.end);
        q.start = 0;
        q.end = size - 1;
        q.data = newData;
    }
    // Q: data size increase if (size = data.length) &&
    //      end = size - 1 &&
    //      start = 0;


    // P: element != null;
    public static void enqueue(final ArrayQueueADT q, final Object element) {
        assert element != null;
        ensureCapacity(q, size(q) + 1);
        q.data[q.end] = element;
        q.end = (q.end + 1) % (q.data.length);
    }
    // Q: q[end] == element &&
    //      end increase cyclically &&
    //      data size increase if needed;

    // P: size != 0;
    public static Object dequeue(final ArrayQueueADT q) {
        // assert !isEmpty();
        /*if (isEmpty()) {
            return null;
        }*/
        Object result = element(q);
        q.data[q.start] = null;
        q.start = (q.start + 1) % (q.data.length);
        return result;
    }
    // Q: return data[start] != null &&
    //      data[start] = null &&
    //      start increase cyclically

    // P: true;
    public static void clear(final ArrayQueueADT q) {
        q.data = new Object[2];
        q.start = 0;
        q.end = 0;
    }
    // Q: data = Object[2] && start = 0 && end = 0;

    // P: true
    public static String toStr(final ArrayQueueADT q) {
        StringBuilder result = new StringBuilder("[");
        int count = size(q);
        while (count != 0) {
            if (count == 1) {
                result.append(q.data[(q.start + size(q) - count) % q.data.length].toString());
            } else {
                result.append(q.data[(q.start + size(q) - count) % q.data.length].toString()).append(", ");
            }
            count--;
        }
        result.append("]");
        return result.toString();
    }
    // Q: return new string of data values

    public static Object[] toArray(final ArrayQueueADT q){
        int count = size(q);
        Object[] result = new Object[count];
        while (count != 0) {
            result[size(q)-count] = q.data[(q.start + size(q) - count) % q.data.length];
            count--;
        }
        return result;
    }

    // P: size > 0
    public static Object element(final ArrayQueueADT q) {
        assert size(q) > 0;
        return q.data[q.start];
    }
    // Q: return data[start] != null &&
    //      immutable data

    // P: true;
    public static boolean isEmpty(final ArrayQueueADT q) {
        return size(q) == 0;
    }
    // Q: return boolean of predicate "size==0";


}
