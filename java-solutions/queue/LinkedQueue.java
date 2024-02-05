package queue;

public class LinkedQueue extends AbstractQueue implements Queue {
    private static class Node {
        private final Object value;
        private Node next;

        public Node(Object element, Node next) {
            this.value = element;
            this.next = next;
        }
    }

    private Node start;
    private Node end;


    @Override
    public void enqueueImpl(Object element) {
        Node node = new Node(element, null);
        if (!isEmpty()) {
            end.next = node;
            end = end.next;
        } else {
            start = end = node;
        }
    }

    @Override
    public void dequeueImpl() {
        start = start.next;
    }

    @Override
    public Object elementImpl() {
        return start.value;
    }

    @Override
    public void clearImpl() {
        start = null;
        end = null;
    }

    @Override
    public int countImpl(Object element) {
        int result = 0;
        for (Node i = start; i != null; i = i.next) {
            if (i.value.equals(element)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public int indexOfImpl(Object element) {
        int result = 0;
        for (Node i = start; i != null; i = i.next) {
            if (i.value.equals(element)) {
                return result;
            }
            result++;
        }
        return -1;
    }

    @Override
    public int lastIndexOfImpl(Object element) {
        int result1 = 0;
        int result2 = -1;
        for (Node i = start; i != null; i = i.next) {
            if (i.value.equals(element)) {
                result2 = result1;
            }
            result1++;
        }
        return result2;
    }


}
