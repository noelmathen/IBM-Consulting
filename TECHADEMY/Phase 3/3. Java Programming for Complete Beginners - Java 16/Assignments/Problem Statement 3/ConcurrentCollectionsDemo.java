import java.util.*;
import java.util.concurrent.*;

public class ConcurrentCollectionsDemo {

    // Shared collections
    private static ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
    private static HashMap<Integer, String> hashMap = new HashMap<>();

    private static ConcurrentLinkedQueue<Integer> concurrentQueue = new ConcurrentLinkedQueue<>();
    private static LinkedList<Integer> linkedList = new LinkedList<>();

    private static CopyOnWriteArrayList<String> concurrentList = new CopyOnWriteArrayList<>();
    private static ArrayList<String> arrayList = new ArrayList<>();

    private static final int NUM_THREADS = 5;
    private static final int OPERATIONS_PER_THREAD = 10000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting concurrent collections demo...\n");

        // Test ConcurrentHashMap vs HashMap with multiple threads
        System.out.println("Testing Map performance:");
        testMapPerformance();

        // Test ConcurrentLinkedQueue vs LinkedList
        System.out.println("\nTesting Queue performance:");
        testQueuePerformance();

        // Test CopyOnWriteArrayList vs ArrayList
        System.out.println("\nTesting List performance:");
        testListPerformance();
    }

    private static void testMapPerformance() throws InterruptedException {
        // Warmup HashMap (not thread-safe)
        hashMap.clear();
        long startHashMap = System.currentTimeMillis();
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    // Not synchronized - may cause errors or exceptions
                    hashMap.put(threadId * OPERATIONS_PER_THREAD + j, "Val" + j);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long endHashMap = System.currentTimeMillis();
        System.out.println("HashMap (non-concurrent) time: " + (endHashMap - startHashMap) + " ms");
        System.out.println("HashMap size: " + hashMap.size());

        // ConcurrentHashMap
        concurrentMap.clear();
        long startConcurrentMap = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    concurrentMap.put(threadId * OPERATIONS_PER_THREAD + j, "Val" + j);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long endConcurrentMap = System.currentTimeMillis();
        System.out.println("ConcurrentHashMap time: " + (endConcurrentMap - startConcurrentMap) + " ms");
        System.out.println("ConcurrentHashMap size: " + concurrentMap.size());
    }

    private static void testQueuePerformance() throws InterruptedException {
        // LinkedList (not thread-safe)
        linkedList.clear();
        Thread[] threads = new Thread[NUM_THREADS];
        long startLinkedList = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    synchronized (linkedList) {
                        linkedList.add(threadId * OPERATIONS_PER_THREAD + j);
                    }
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long endLinkedList = System.currentTimeMillis();
        System.out.println("LinkedList with synchronization time: " + (endLinkedList - startLinkedList) + " ms");
        System.out.println("LinkedList size: " + linkedList.size());

        // ConcurrentLinkedQueue (thread-safe)
        concurrentQueue.clear();
        long startConcurrentQueue = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    concurrentQueue.add(threadId * OPERATIONS_PER_THREAD + j);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long endConcurrentQueue = System.currentTimeMillis();
        System.out.println("ConcurrentLinkedQueue time: " + (endConcurrentQueue - startConcurrentQueue) + " ms");
        System.out.println("ConcurrentLinkedQueue size: " + concurrentQueue.size());
    }

    private static void testListPerformance() throws InterruptedException {
        // ArrayList (not thread-safe)
        arrayList.clear();
        Thread[] threads = new Thread[NUM_THREADS];
        long startArrayList = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    synchronized (arrayList) {
                        arrayList.add("Val" + (threadId * OPERATIONS_PER_THREAD + j));
                    }
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long endArrayList = System.currentTimeMillis();
        System.out.println("ArrayList with synchronization time: " + (endArrayList - startArrayList) + " ms");
        System.out.println("ArrayList size: " + arrayList.size());

        // CopyOnWriteArrayList (thread-safe but costly on writes)
        concurrentList.clear();
        long startCopyOnWrite = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    concurrentList.add("Val" + (threadId * OPERATIONS_PER_THREAD + j));
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long endCopyOnWrite = System.currentTimeMillis();
        System.out.println("CopyOnWriteArrayList time: " + (endCopyOnWrite - startCopyOnWrite) + " ms");
        System.out.println("CopyOnWriteArrayList size: " + concurrentList.size());
    }
}
