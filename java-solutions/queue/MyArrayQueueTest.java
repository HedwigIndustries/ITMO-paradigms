package queue;


class MyArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue q1 = new ArrayQueue();
        ArrayQueue q2 = new ArrayQueue();

        for (int i = 111; i < 800; i += 111) {
            q1.enqueue("e_1_" + i);
            q2.enqueue("e_2_" + i);
        }
        checkSize(q2);
        dumpQueue(q1);

    }

    private static void checkSize(ArrayQueue q2) {
        System.out.println(q2.size);
        System.out.println(q2.element());
        System.out.println(q2.size);
        q2.clear();
        System.out.println(q2.size);
        System.out.println(q2.isEmpty());
    }

    private static void dumpQueue(ArrayQueue q) {
        while (!q.isEmpty()) {
            final Object value = q.dequeue();
            System.out.println(q.size() + " " + value);
        }
    }
}