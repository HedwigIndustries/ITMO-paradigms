package queue;


class MyArrayQueueADTTest {
    public static void main(String[] args) {
        ArrayQueueADT q1 = new ArrayQueueADT();
        ArrayQueueADT q2 = new ArrayQueueADT();

        for (int i = 111; i < 800; i += 111) {
            ArrayQueueADT.enqueue(q1, "e_1_" + i);
            ArrayQueueADT.enqueue(q2, "e_2_" + i);
        }
        dumpStack(q1);
        checkSize(q2);
    }

    private static void dumpStack(ArrayQueueADT q) {
        while (!ArrayQueueADT.isEmpty(q)) {
            final Object value = ArrayQueueADT.dequeue(q);
            System.out.println(ArrayQueueADT.size(q) + " " + value);
        }
    }

    private static void checkSize(ArrayQueueADT q2) {
        System.out.println(ArrayQueueADT.size(q2));
        System.out.println(ArrayQueueADT.element(q2));
        System.out.println(ArrayQueueADT.size(q2));
        ArrayQueueADT.clear(q2);
        System.out.println(ArrayQueueADT.size(q2));
        System.out.println(ArrayQueueADT.isEmpty(q2));
    }
}