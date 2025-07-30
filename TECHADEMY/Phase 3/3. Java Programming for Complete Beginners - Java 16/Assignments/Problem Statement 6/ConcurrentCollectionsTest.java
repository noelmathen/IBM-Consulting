import java.util.*;
import java.util.concurrent.*;

public class ConcurrentCollectionsTest {

    // Collections to test
    private static ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
    private static HashMap<Integer, String> hashMap = new HashMap<>();

    private static ConcurrentLinkedQueue<Integer> concurrentQueue = new ConcurrentLinkedQueue<>();
    private static LinkedList<Integer> linkedList = new LinkedList<>();

    private static CopyOnWriteArrayList<String> concurrentList = new CopyOnWriteArrayList<>();
    private static ArrayList<String> arrayList = new ArrayList<>();

    private static final int THREADS = 4;
    private static final int OPERATIONS = 50000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Concurrent Collections Performance Test\n");

        testMaps();
        testQueues();
        testLists();
    }

    private static void testMaps() throws InterruptedException {
        System.out.println("Testing Map:");

        // Non-concurrent HashMap (not thread-safe)
        hashMap.clear();
        Thread[] threads = new Thread[THREADS];
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++) {
            int tid = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    synchronized(hashMap) {
                        hashMap.put(tid * OPERATIONS + j, "Val" + j);
                    }
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        System.out.printf("HashMap with synchronization time: %d ms, size: %d%n", (end - start), hashMap.size());

        // ConcurrentHashMap
        concurrentMap.clear();
        for (int i = 0; i < THREADS; i++) {
            int tid = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    concurrentMap.put(tid * OPERATIONS + j, "Val" + j);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        end = System.currentTimeMillis();
        System.out.printf("ConcurrentHashMap time: %d ms, size: %d%n\n", (end - start), concurrentMap.size());
    }

    private static void testQueues() throws InterruptedException {
        System.out.println("Testing Queue:");

        // LinkedList with synchronization
        linkedList.clear();
        Thread[] threads = new Thread[THREADS];
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++) {
            int tid = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    synchronized(linkedList) {
                        linkedList.add(tid * OPERATIONS + j);
                    }
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        System.out.printf("LinkedList with synchronization time: %d ms, size: %d%n", (end - start), linkedList.size());

        // ConcurrentLinkedQueue
        concurrentQueue.clear();
        for (int i = 0; i < THREADS; i++) {
            int tid = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    concurrentQueue.add(tid * OPERATIONS + j);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        end = System.currentTimeMillis();
        System.out.printf("ConcurrentLinkedQueue time: %d ms, size: %d%n%n", (end - start), concurrentQueue.size());
    }

    private static void testLists() throws InterruptedException {
        System.out.println("Testing List:");

        // ArrayList with synchronization
        arrayList.clear();
        Thread[] threads = new Thread[THREADS];
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++) {
            int tid = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    synchronized(arrayList) {
                        arrayList.add("Val" + (tid * OPERATIONS + j));
                    }
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        System.out.printf("ArrayList with synchronization time: %d ms, size: %d%n", (end - start), arrayList.size());

        // CopyOnWriteArrayList
        concurrentList.clear();
        for (int i = 0; i < THREADS; i++) {
            int tid = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPERATIONS; j++) {
                    concurrentList.add("Val" + (tid * OPERATIONS + j));
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        end = System.currentTimeMillis();
        System.out.printf("CopyOnWriteArrayList time: %d ms, size: %d%n", (end - start), concurrentList.size());
    }
}


