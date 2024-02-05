package queue;


class MyArrayQueueModuleTest {
    public static void main(String[] args) {
        for (int i = 111; i < 800; i += 111) {
            ArrayQueueModule.enqueue("element" + i);
        }
        while (!ArrayQueueModule.isEmpty()) {
            final Object value = ArrayQueueModule.dequeue();
            System.out.println(ArrayQueueModule.size() + " " + value);
        }
    }
}