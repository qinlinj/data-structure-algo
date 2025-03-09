package org.qinlinj.linear.queue;

import java.util.Queue;
import java.util.*;
import java.util.concurrent.*;


// @formatter:off
/**
 * A comprehensive test class that demonstrates the usage, inheritance relationships,
 * methods, advantages, and disadvantages of Queue and Stack implementations in Java.
 *
 * Queue Hierarchy:
 * - Queue (interface) extends Collection
 *   - Deque (interface) extends Queue
 *     - ArrayDeque (class) implements Deque
 *     - LinkedList (class) implements Deque
 *   - PriorityQueue (class) implements Queue
 *   - BlockingQueue (interface) extends Queue
 *     - ArrayBlockingQueue (class) implements BlockingQueue
 *     - LinkedBlockingQueue (class) implements BlockingQueue
 *     - PriorityBlockingQueue (class) implements BlockingQueue
 *     - DelayQueue (class) implements BlockingQueue
 *     - SynchronousQueue (class) implements BlockingQueue
 *   - TransferQueue (interface) extends BlockingQueue
 *     - LinkedTransferQueue (class) implements TransferQueue
 *
 * Stack Hierarchy:
 * - Stack (class) extends Vector
 * - Deque (interface) can be used as a stack
 *   - ArrayDeque (class) implements Deque (recommended instead of Stack)
 *   - LinkedList (class) implements Deque (can be used as stack)
 *
 * COMPARISON TABLE OF QUEUE IMPLEMENTATIONS:
 * ┌─────────────────────┬─────────────┬──────────────┬────────────────┬─────────────────┬───────────────────────────────────┐
 * │ Implementation      │ Thread-safe │ Allows null  │ Bounded        │ Special Features│ Best Use Case                     │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ ArrayDeque          │ No          │ No           │ No             │ Fast operations │ Single-threaded general purpose   │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ LinkedList          │ No          │ Yes          │ No             │ Double-ended    │ When nulls needed or frequent adds│
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ PriorityQueue       │ No          │ No           │ No             │ Priority order  │ When elements need ordering       │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ ArrayBlockingQueue  │ Yes         │ No           │ Yes (required) │ Optionally fair │ Bounded producer-consumer         │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ LinkedBlockingQueue │ Yes         │ No           │ Yes (optional) │ High throughput │ Unbounded producer-consumer       │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ PriorityBlockingQ   │ Yes         │ No           │ No             │ Priority order  │ Concurrent priority tasks         │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ SynchronousQueue    │ Yes         │ No           │ Zero capacity  │ Direct handoff  │ Thread-to-thread transfers        │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ LinkedTransferQueue │ Yes         │ No           │ No             │ Transfer modes  │ Complex producer-consumer         │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼─────────────────┼───────────────────────────────────┤
 * │ DelayQueue          │ Yes         │ No           │ No             │ Delayed access  │ Scheduled/time-based tasks        │
 * └─────────────────────┴─────────────┴──────────────┴────────────────┴─────────────────┴───────────────────────────────────┘
 *
 * COMPARISON TABLE OF STACK IMPLEMENTATIONS:
 * ┌─────────────────────┬─────────────┬──────────────┬────────────────┬────────────────┬───────────────────────────────────┐
 * │ Implementation      │ Thread-safe │ Allows null  │ Performance    │ Special Notes  │ Best Use Case                     │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼────────────────┼───────────────────────────────────┤
 * │ Stack (legacy)      │ Yes         │ Yes          │ Poor           │ Legacy class   │ Legacy code only                  │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼────────────────┼───────────────────────────────────┤
 * │ ArrayDeque as Stack │ No          │ No           │ Excellent      │ Modern API     │ Most stack applications           │
 * ├─────────────────────┼─────────────┼──────────────┼────────────────┼────────────────┼───────────────────────────────────┤
 * │ LinkedList as Stack │ No          │ Yes          │ Good           │ More overhead  │ When nulls needed                 │
 * └─────────────────────┴─────────────┴──────────────┴────────────────┴────────────────┴───────────────────────────────────┘
 *
 * CONCLUSION:
 * - For non-concurrent queues: ArrayDeque is generally best for performance
 * - For concurrent queues: Choose based on specific needs (bounded/unbounded, fairness, etc.)
 * - For stacks: ArrayDeque is almost always the best choice over legacy Stack class
 * - For special requirements:
 *   - Need nulls? Use LinkedList
 *   - Need ordering? Use PriorityQueue
 *   - Need blocking? Choose appropriate BlockingQueue implementation
 */
public class JavaQueueStackImplementTest {

    public static void main(String[] args) {
        // Testing different Queue implementations
        System.out.println("===== TESTING QUEUE IMPLEMENTATIONS =====");
        testArrayDequeAsQueue();
        testLinkedListAsQueue();
        testPriorityQueue();
        testArrayBlockingQueue();
        testLinkedBlockingQueue();
        testPriorityBlockingQueue();
        testSynchronousQueue();
        testLinkedTransferQueue();
        testDelayQueue();

        // Testing different Stack implementations
        System.out.println("\n===== TESTING STACK IMPLEMENTATIONS =====");
        testLegacyStack();
        testArrayDequeAsStack();
        testLinkedListAsStack();
    }

    /**
     * Tests ArrayDeque as a Queue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: Deque interface (which extends Queue)
     * - Internal structure: Resizable array with circular design
     * - Memory efficiency: Good - minimal overhead, array-based
     * - Thread safety: Not thread-safe, use Collections.synchronizedDeque() if needed
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(1) for offer(e), poll(), peek(), size(), isEmpty()
     * - Excellent memory locality (better cache performance)
     * - Faster than LinkedList for most operations due to array-based structure
     *
     * KEY METHODS:
     * - offer(e): Adds element at tail, returns false if full (never in ArrayDeque)
     * - add(e): Same as offer but throws exception if full
     * - poll(): Removes and returns head, returns null if empty
     * - remove(): Same as poll but throws exception if empty
     * - peek(): Returns but doesn't remove head, returns null if empty
     * - element(): Same as peek but throws exception if empty
     *
     * ADVANTAGES:
     * - Excellent general-purpose implementation
     * - No capacity restrictions (grows as needed)
     * - Better performance than LinkedList for most operations
     * - Lower memory overhead than LinkedList
     * - Can be used as both Queue and Stack
     *
     * DISADVANTAGES:
     * - Null elements are prohibited
     * - Not thread-safe
     * - Array resizing operations can be expensive (but infrequent)
     *
     * BEST USED FOR:
     * - General-purpose queue in single-threaded applications
     * - When performance is a concern
     * - When you need both stack and queue operations
     */
    private static void testArrayDequeAsQueue() {
        System.out.println("\n--- ArrayDeque as Queue ---");
        Queue<String> queue = new ArrayDeque<>();

        // Adding elements
        System.out.println("Adding elements...");
        queue.offer("First");   // Preferred method, returns false if full
        queue.add("Second");    // Throws exception if full
        queue.offer("Third");
        System.out.println("Queue after adding: " + queue);

        // Examining elements
        System.out.println("Peek (non-destructive): " + queue.peek());    // Returns null if empty
        System.out.println("Element (throws if empty): " + queue.element());
        System.out.println("Queue after peeking: " + queue);

        // Removing elements
        System.out.println("Poll (returns null if empty): " + queue.poll());   // Returns null if empty
        System.out.println("Remove (throws if empty): " + queue.remove());
        System.out.println("Queue after removing: " + queue);
    }

    /**
     * Tests LinkedList as a Queue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: Deque interface (which extends Queue) and List interface
     * - Internal structure: Doubly-linked list of nodes
     * - Memory efficiency: Fair - each node has overhead for prev/next references
     * - Thread safety: Not thread-safe, use Collections.synchronizedList() if needed
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(1) for offer(e), poll(), peek(), size(), isEmpty()
     * - O(n) for contains() and remove(Object)
     * - Worse cache locality than ArrayDeque
     *
     * KEY METHODS:
     * - offer(e): Adds element at tail, returns true (always in LinkedList)
     * - add(e): Same as offer (always returns true)
     * - poll(): Removes and returns head, returns null if empty
     * - remove(): Same as poll but throws exception if empty
     * - peek(): Returns but doesn't remove head, returns null if empty
     * - element(): Same as peek but throws exception if empty
     *
     * ADVANTAGES:
     * - Allows null elements (unlike most Queue implementations)
     * - Good for frequent add/remove operations
     * - No need to resize like array-based implementations
     * - Can be used as List, Queue, and Stack simultaneously
     * - Implements more interfaces (List, Deque) than ArrayDeque
     *
     * DISADVANTAGES:
     * - Higher memory overhead per element
     * - Generally slower than ArrayDeque for queue operations
     * - Worse cache locality (elements spread in memory)
     * - Not thread-safe
     *
     * BEST USED FOR:
     * - When you need to store null elements
     * - When you need List interface methods along with Queue operations
     * - When elements are frequently added/removed
     * - When you're already using a LinkedList for other purposes
     */
    private static void testLinkedListAsQueue() {
        System.out.println("\n--- LinkedList as Queue ---");
        Queue<String> queue = new LinkedList<>();

        // Adding elements
        queue.offer("First");
        queue.add("Second");
        queue.offer("Third");
        System.out.println("Queue after adding: " + queue);

        // Adding null (allowed in LinkedList)
        queue.offer(null);
        System.out.println("Queue after adding null: " + queue);

        // Examining elements
        System.out.println("Peek: " + queue.peek());

        // Removing elements
        System.out.println("Poll: " + queue.poll());
        System.out.println("Remove: " + queue.remove());
        System.out.println("Queue after removing: " + queue);
    }

    /**
     * Tests PriorityQueue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: Queue interface
     * - Internal structure: Binary heap (complete binary tree)
     * - Memory efficiency: Good - array-based heap with minimal overhead
     * - Thread safety: Not thread-safe, use PriorityBlockingQueue for thread safety
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(log n) for offer(e), poll(), remove()
     * - O(1) for peek(), element(), size(), isEmpty()
     * - O(n) for contains(), remove(Object)
     *
     * KEY METHODS:
     * - offer(e): Adds element at priority position, returns true (always in PriorityQueue)
     * - add(e): Same as offer (always returns true)
     * - poll(): Removes and returns highest priority element, returns null if empty
     * - remove(): Same as poll but throws exception if empty
     * - peek(): Returns but doesn't remove highest priority element, returns null if empty
     * - element(): Same as peek but throws exception if empty
     *
     * ADVANTAGES:
     * - Elements are ordered by priority (natural ordering or provided Comparator)
     * - Always returns the highest priority element first
     * - User can define custom priority logic with a Comparator
     * - Good performance for priority queue operations
     *
     * DISADVANTAGES:
     * - Does not allow null elements
     * - No guaranteed order for elements with equal priority
     * - Not thread-safe
     * - Iteration is not in priority order (use poll() repeatedly for that)
     * - More expensive operations than standard queue (due to maintaining heap)
     *
     * BEST USED FOR:
     * - When elements need to be processed in priority order
     * - Task schedulers and priority systems
     * - Dijkstra's algorithm and other priority-based algorithms
     * - Implementing heapsort
     */
    private static void testPriorityQueue() {
        System.out.println("\n--- PriorityQueue ---");

        // Default constructor - natural ordering
        Queue<Integer> naturalOrderQueue = new PriorityQueue<>();
        naturalOrderQueue.addAll(Arrays.asList(5, 2, 8, 1, 7));
        System.out.println("Priority queue (natural order): " + naturalOrderQueue);

        System.out.println("Elements come out in priority order:");
        while (!naturalOrderQueue.isEmpty()) {
            System.out.print(naturalOrderQueue.poll() + " ");
        }
        System.out.println();

        // With custom comparator (reverse order)
        Queue<Integer> reverseOrderQueue = new PriorityQueue<>(Comparator.reverseOrder());
        reverseOrderQueue.addAll(Arrays.asList(5, 2, 8, 1, 7));

        System.out.println("Elements come out in reverse priority order:");
        while (!reverseOrderQueue.isEmpty()) {
            System.out.print(reverseOrderQueue.poll() + " ");
        }
        System.out.println();
    }

    /**
     * Tests ArrayBlockingQueue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: BlockingQueue interface (extends Queue)
     * - Internal structure: Array-based circular buffer with fixed capacity
     * - Memory efficiency: Excellent - compact array representation
     * - Thread safety: Fully thread-safe with internal locks
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(1) for offer(e), poll(), peek(), size(), isEmpty()
     * - Blocking operations may wait indefinitely
     * - Optional fairness policy (FIFO ordering of waiting threads)
     *
     * KEY METHODS:
     * - offer(e): Adds element if space available, returns false if full
     * - offer(e, time, unit): Adds element, waits for specified time if full
     * - add(e): Same as offer but throws exception if full
     * - put(e): Adds element, blocks indefinitely if full
     * - poll(): Removes and returns head, returns null if empty
     * - poll(time, unit): Removes and returns head, waits for specified time if empty
     * - take(): Removes and returns head, blocks indefinitely if empty
     * - peek(): Returns but doesn't remove head, returns null if empty
     * - drainTo(collection): Removes all elements and adds to the given collection
     *
     * ADVANTAGES:
     * - Fixed capacity (prevents unbounded memory usage)
     * - Thread-safe without external synchronization
     * - Optional fairness policy
     * - Suitable for bounded producer-consumer patterns
     * - Good memory locality (array-based)
     *
     * DISADVANTAGES:
     * - Fixed capacity must be defined at creation
     * - Cannot be resized after creation
     * - Does not allow null elements
     * - Potentially less throughput than LinkedBlockingQueue due to single lock
     *
     * BEST USED FOR:
     * - Producer-consumer scenarios with known bounded buffer requirements
     * - When memory constraints are important
     * - When fairness between threads is required
     * - High performance concurrent applications with bounded buffer needs
     */
    private static void testArrayBlockingQueue() {
        System.out.println("\n--- ArrayBlockingQueue ---");

        // Create bounded queue with capacity 3
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        // Adding elements
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");

        // This will return false because queue is full
        boolean added = queue.offer("Fourth");
        System.out.println("Fourth element added: " + added);

        System.out.println("Queue after offering: " + queue);

        // Demonstrate blocking behavior (commented out to avoid blocking the test)
        // try {
        //     queue.put("Will block until space available");
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // Removing elements
        System.out.println("Poll: " + queue.poll());
        System.out.println("Queue after polling: " + queue);

        // Now we can add another element
        queue.offer("Fourth");
        System.out.println("Queue after adding fourth: " + queue);
    }

    /**
     * Tests LinkedBlockingQueue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: BlockingQueue interface (extends Queue)
     * - Internal structure: Linked nodes with optional maximum capacity
     * - Memory efficiency: Moderate - node overhead but dynamic sizing
     * - Thread safety: Fully thread-safe with separate locks for head and tail
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(1) for offer(e), poll(), peek(), size(), isEmpty()
     * - Blocking operations may wait indefinitely
     * - Higher throughput than ArrayBlockingQueue due to separate head/tail locks
     *
     * KEY METHODS:
     * - offer(e): Adds element if space available, returns false if full
     * - offer(e, time, unit): Adds element, waits for specified time if full
     * - add(e): Same as offer but throws exception if full
     * - put(e): Adds element, blocks indefinitely if full
     * - poll(): Removes and returns head, returns null if empty
     * - poll(time, unit): Removes and returns head, waits for specified time if empty
     * - take(): Removes and returns head, blocks indefinitely if empty
     * - peek(): Returns but doesn't remove head, returns null if empty
     * - drainTo(collection): Removes all elements and adds to the given collection
     *
     * ADVANTAGES:
     * - Optionally bounded capacity
     * - Thread-safe without external synchronization
     * - Higher throughput than ArrayBlockingQueue due to separate locks
     * - Suitable for producer-consumer patterns
     * - Dynamically allocated nodes (no need for upfront capacity)
     *
     * DISADVANTAGES:
     * - Higher memory overhead than ArrayBlockingQueue
     * - Does not allow null elements
     * - Unbounded by default (can lead to OutOfMemoryError if not controlled)
     * - Nodes are allocated dynamically (potential GC pressure)
     *
     * BEST USED FOR:
     * - High throughput producer-consumer scenarios
     * - When multiple threads need to add/remove concurrently
     * - When capacity requirements are not strictly bounded
     * - When performance is more important than memory efficiency
     */
    private static void testLinkedBlockingQueue() {
        System.out.println("\n--- LinkedBlockingQueue ---");

        // Create bounded queue with capacity 3
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

        // Adding elements
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");

        // This will return false because queue is full
        boolean added = queue.offer("Fourth");
        System.out.println("Fourth element added: " + added);

        System.out.println("Queue after offering: " + queue);

        // Removing elements
        System.out.println("Poll: " + queue.poll());

        // Now we can add another element
        queue.offer("Fourth");
        System.out.println("Queue after adding fourth: " + queue);
    }

    /**
     * Tests PriorityBlockingQueue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: BlockingQueue interface (extends Queue)
     * - Internal structure: Binary heap (complete binary tree) with thread safety
     * - Memory efficiency: Good - array-based heap with minimal overhead
     * - Thread safety: Fully thread-safe with internal locks
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(log n) for offer(e), poll(), remove()
     * - O(1) for peek(), size(), isEmpty()
     * - Blocking only on retrieval operations (not on insertions)
     *
     * KEY METHODS:
     * - offer(e): Always adds element and returns true (unbounded)
     * - offer(e, time, unit): Same as offer(e), time parameter is ignored
     * - add(e): Same as offer (always returns true)
     * - put(e): Adds element without blocking (unbounded)
     * - poll(): Removes and returns highest priority element, returns null if empty
     * - poll(time, unit): Waits up to specified time for an element
     * - take(): Removes and returns highest priority element, blocks if empty
     * - peek(): Returns but doesn't remove highest priority element, returns null if empty
     *
     * ADVANTAGES:
     * - Thread-safe priority queue
     * - Elements are ordered by priority even in concurrent environment
     * - Unbounded capacity (grows as needed)
     * - User can define custom priority logic with a Comparator
     * - Blocking retrieval operations for producer-consumer patterns
     *
     * DISADVANTAGES:
     * - Does not allow null elements
     * - No guaranteed order for elements with equal priority
     * - Unbounded (can lead to OutOfMemoryError if not controlled)
     * - More expensive operations than standard BlockingQueue (due to maintaining heap)
     * - Insertion operations never block (may cause memory issues)
     *
     * BEST USED FOR:
     * - Thread-safe priority-based processing
     * - Task schedulers and priority systems in concurrent applications
     * - Priority-based message handling
     * - When elements must be processed in priority order by multiple threads
     */
    private static void testPriorityBlockingQueue() {
        System.out.println("\n--- PriorityBlockingQueue ---");

        // Create priority blocking queue with custom comparator
        BlockingQueue<String> queue = new PriorityBlockingQueue<>(10,
                Comparator.comparing(String::length));

        // Adding elements of different lengths
        queue.offer("Long string");
        queue.offer("Medium");
        queue.offer("Short");

        System.out.println("Queue contents: " + queue);

        // Elements will be dequeued based on priority (string length)
        System.out.println("Polling elements in priority order:");
        System.out.println("Poll 1: " + queue.poll());  // Should be "Short"
        System.out.println("Poll 2: " + queue.poll());  // Should be "Medium"
        System.out.println("Poll 3: " + queue.poll());  // Should be "Long string"
    }

    /**
     * Tests SynchronousQueue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: BlockingQueue interface (extends Queue)
     * - Internal structure: No internal capacity, direct handoffs
     * - Memory efficiency: Excellent - no element storage overhead
     * - Thread safety: Fully thread-safe with internal synchronization
     *
     * PERFORMANCE CHARACTERISTICS:
     * - Direct handoff from producer to consumer
     * - Zero capacity (no internal buffer)
     * - Can be configured with fair or non-fair waiting policy
     *
     * KEY METHODS:
     * - offer(e): Adds element only if another thread is waiting to take it, otherwise returns false
     * - offer(e, time, unit): Tries to add element, waiting up to specified time for a consumer
     * - add(e): Throws exception if no consumer waiting
     * - put(e): Adds element, blocks until another thread takes it
     * - poll(): Removes and returns element if one available, otherwise returns null
     * - poll(time, unit): Waits up to specified time for an element
     * - take(): Removes and returns an element, blocks until one is available
     * - peek(): Always returns null (as there's no storage)
     *
     * ADVANTAGES:
     * - Thread-safe direct handoffs between threads
     * - No capacity limits or buffer management
     * - Minimal memory overhead (no element storage)
     * - Can use fair mode for predictable thread ordering
     * - Excellent for producer-consumer with 1:1 relationship
     *
     * DISADVANTAGES:
     * - Zero capacity - insertion blocks until another thread receives
     * - Cannot peek() at elements (always returns null)
     * - Does not allow null elements
     * - Operations may block indefinitely
     * - Not suitable for storing elements
     *
     * BEST USED FOR:
     * - Event handling systems where handlers are always waiting
     * - Direct thread-to-thread transfers
     * - Scenarios where buffering is not desired
     * - Rendezvous design pattern implementation
     */
    private static void testSynchronousQueue() {
        System.out.println("\n--- SynchronousQueue ---");

        BlockingQueue<String> queue = new SynchronousQueue<>();

        // Demonstrate behavior with a producer and consumer thread
        Thread producer = new Thread(() -> {
            try {
                System.out.println("Producer trying to put element");
                // This will block until another thread takes the element
                boolean success = queue.offer("Element", 2, TimeUnit.SECONDS);
                System.out.println("Producer put element: " + success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                // Give producer time to start
                Thread.sleep(1000);
                System.out.println("Consumer taking element");
                String element = queue.poll(2, TimeUnit.SECONDS);
                System.out.println("Consumer got: " + element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests LinkedTransferQueue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: TransferQueue interface (extends BlockingQueue)
     * - Internal structure: Linked nodes with optimistic non-blocking algorithms
     * - Memory efficiency: Moderate - node overhead but efficient locking
     * - Thread safety: Fully thread-safe using advanced non-blocking algorithms
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(1) for offer(e), poll(), peek(), size(), isEmpty()
     * - Highly scalable with many concurrent threads
     * - Combines features of SynchronousQueue and LinkedBlockingQueue
     *
     * KEY METHODS:
     * - offer(e): Adds element without blocking
     * - add(e): Same as offer (returns true)
     * - put(e): Adds element without blocking
     * - transfer(e): Transfers element to waiting consumer, blocking until consumed
     * - tryTransfer(e): Transfers if consumer waiting, otherwise returns false
     * - tryTransfer(e, time, unit): Transfers if consumer takes within time limit
     * - poll(): Removes and returns head, returns null if empty
     * - take(): Removes and returns head, blocks if empty
     * - getWaitingConsumerCount(): Returns estimate of waiting consumers
     * - hasWaitingConsumer(): Returns whether any consumers are waiting
     *
     * ADVANTAGES:
     * - Combines features of several queue types (unbounded, transfer capabilities)
     * - High performance with non-blocking algorithms
     * - Excellent scalability with many threads
     * - Flexible modes of operation (immediate, timed, blocking)
     * - Can operate in synchronous or asynchronous modes
     *
     * DISADVANTAGES:
     * - More complex API to understand
     * - Does not allow null elements
     * - Unbounded by default (can lead to OutOfMemoryError if not controlled)
     * - Dynamically allocated nodes (potential GC pressure)
     *
     * BEST USED FOR:
     * - Complex producer-consumer scenarios requiring flexibility
     * - When high scalability with many threads is needed
     * - Applications requiring both queuing and direct handoff
     * - High-performance message passing between threads
     */
    private static void testLinkedTransferQueue() {
        System.out.println("\n--- LinkedTransferQueue ---");

        TransferQueue<String> queue = new LinkedTransferQueue<>();

        // Add some elements
        queue.offer("First");
        queue.offer("Second");

        System.out.println("Queue after offering: " + queue);

        // Take elements
        try {
            System.out.println("Poll: " + queue.poll());
            System.out.println("Take: " + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Demonstrate transfer (would block if no consumer ready)
        Thread transferThread = new Thread(() -> {
            try {
                System.out.println("Attempting to transfer element");
                boolean transferred = queue.tryTransfer("Transferred", 1, TimeUnit.SECONDS);
                System.out.println("Element transferred: " + transferred);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        transferThread.start();
        try {
            transferThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests DelayQueue.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: BlockingQueue interface (extends Queue)
     * - Internal structure: Priority queue of Delayed elements
     * - Memory efficiency: Good - similar to PriorityQueue
     * - Thread safety: Fully thread-safe with internal locks
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(log n) for offer(e), poll(), remove()
     * - O(1) for peek(), size(), isEmpty()
     * - Elements become available only after their delay expires
     *
     * KEY METHODS:
     * - offer(e): Adds element, returns true (unbounded)
     * - add(e): Same as offer (returns true)
     * - put(e): Adds element without blocking (unbounded)
     * - poll(): Removes and returns expired head element, null if no expired elements
     * - poll(time, unit): Waits up to specified time for an expired element
     * - take(): Removes and returns head when expired, blocks until one is available
     * - peek(): Returns but doesn't remove head (even if not expired), null if empty
     *
     * ADVANTAGES:
     * - Built-in delay mechanism for time-based processing
     * - Thread-safe without external synchronization
     * - Natural for implementing scheduling and timeouts
     * - Expired elements come out in order of expiration
     * - Unexpired elements cannot be removed via poll()/take()
     *
     * DISADVANTAGES:
     * - Elements must implement Delayed interface
     * - Does not allow null elements
     * - poll() only returns expired elements, can return null even when not empty
     * - Unbounded (can lead to OutOfMemoryError if not controlled)
     * - More complex to use due to need for Delayed implementation
     *
     * BEST USED FOR:
     * - Scheduled task execution
     * - Timeout management
     * - Rate limiting implementations
     * - Caching with expiration
     * - Any time-based priority processing
     */
    private static void testDelayQueue() {
        System.out.println("\n--- DelayQueue ---");

        DelayQueue<DelayedElement> queue = new DelayQueue<>();

        // Add elements with different delays
        queue.offer(new DelayedElement("First", 2000));
        queue.offer(new DelayedElement("Second", 1000));
        queue.offer(new DelayedElement("Third", 3000));

        System.out.println("Queue size after offering: " + queue.size());

        // Poll will return null if no elements are ready
        System.out.println("Immediate poll: " + queue.poll());

        // Wait for some delays to expire
        try {
            System.out.println("Waiting for delays to expire...");
            Thread.sleep(1500);

            // Should get "Second" as its delay is 1000ms
            System.out.println("Poll after 1.5s: " + queue.poll());

            Thread.sleep(1000);
            // Should get "First" as its delay is 2000ms
            System.out.println("Poll after 2.5s: " + queue.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests the legacy Stack class.
     *
     * IMPLEMENTATION DETAILS:
     * - Extends: Vector class
     * - Internal structure: Growable array (from Vector)
     * - Memory efficiency: Fair - array-based but with synchronization overhead
     * - Thread safety: Fully thread-safe through Vector's synchronization
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(1) for push(e), pop(), peek(), empty(), search(o)
     * - All operations are synchronized (potential contention)
     * - Performance suffers under concurrent access due to full synchronization
     *
     * KEY METHODS:
     * - push(e): Adds element to the top of the stack
     * - pop(): Removes and returns the top element, throws EmptyStackException if empty
     * - peek(): Returns but doesn't remove the top element, throws if empty
     * - empty(): Returns true if stack contains no elements
     * - search(o): Returns 1-based position from top, or -1 if not found
     *
     * ADVANTAGES:
     * - Simple and familiar API (push/pop/peek)
     * - Allows null elements
     * - Thread-safe without external synchronization
     * - Legacy code compatibility
     *
     * DISADVANTAGES:
     * - Poor performance due to synchronized methods (even in single-thread use)
     * - Extends Vector (carries obsolete functionality)
     * - Officially discouraged in favor of Deque implementations
     * - Less memory efficient than modern alternatives
     * - Stack behavior is mixed with Vector's List behavior (conceptually unclear)
     *
     * BEST USED FOR:
     * - Legacy code maintenance only
     * - When thread safety is needed but performance is not critical
     * - When null elements must be stored in a thread-safe stack
     * - NOT recommended for new development (use Deque implementations instead)
     */
    private static void testLegacyStack() {
        System.out.println("\n--- Legacy Stack ---");

        Stack<String> stack = new Stack<>();

        // Push elements
        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        System.out.println("Stack after pushing: " + stack);

        // Peek at top element
        System.out.println("Peek: " + stack.peek());

        // Check if element exists (returns 1-based position from top)
        System.out.println("Position of 'First' from top: " + stack.search("First"));

        // Pop elements
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());

        System.out.println("Stack after popping: " + stack);

        // Check if empty
        System.out.println("Is stack empty? " + stack.empty());
    }

    /**
     * Tests ArrayDeque as a Stack.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: Deque interface
     * - Internal structure: Resizable array with circular design
     * - Memory efficiency: Excellent - minimal overhead, array-based
     * - Thread safety: Not thread-safe, use Collections.synchronizedDeque() if needed
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(1) for push(e), pop(), peek(), isEmpty()
     * - Better memory locality than LinkedList (array-based)
     * - No synchronization overhead (unlike Stack class)
     *
     * KEY METHODS:
     * - push(e): Adds element to the front of the deque
     * - pop(): Removes and returns the first element, throws NoSuchElementException if empty
     * - peek(): Returns but doesn't remove the first element, returns null if empty
     * - isEmpty(): Returns true if deque contains no elements
     *
     * ADVANTAGES:
     * - Excellent performance (fastest stack implementation in Java)
     * - No synchronization overhead
     * - Modern and recommended replacement for Stack class
     * - Can be used as both Stack and Queue
     * - Better memory locality (array-based)
     *
     * DISADVANTAGES:
     * - Does not allow null elements
     * - Not thread-safe by default
     * - No direct equivalent of Stack's search() method
     * - No isEmpty() method (must use isEmpty() inherited from Collection)
     *
     * BEST USED FOR:
     * - Single-threaded stack operations
     * - When performance is important
     * - Modern Java applications
     * - General-purpose stack implementation (recommended by Java documentation)
     */
    private static void testArrayDequeAsStack() {
        System.out.println("\n--- ArrayDeque as Stack ---");

        Deque<String> stack = new ArrayDeque<>();

        // Push elements
        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        System.out.println("Stack after pushing: " + stack);

        // Peek at top element
        System.out.println("Peek: " + stack.peek());

        // Pop elements
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());

        System.out.println("Stack after popping: " + stack);

        // Check if empty
        System.out.println("Is stack empty? " + stack.isEmpty());
    }

    /**
     * Tests LinkedList as a Stack.
     *
     * IMPLEMENTATION DETAILS:
     * - Implements: Deque interface and List interface
     * - Internal structure: Doubly-linked list of nodes
     * - Memory efficiency: Fair - each node has overhead for prev/next references
     * - Thread safety: Not thread-safe, use Collections.synchronizedList() if needed
     *
     * PERFORMANCE CHARACTERISTICS:
     * - O(1) for push(e), pop(), peek(), isEmpty()
     * - Worse cache locality than ArrayDeque
     *
     * KEY METHODS:
     * - push(e): Adds element to the front of the deque
     * - pop(): Removes and returns the first element, throws NoSuchElementException if empty
     * - peek(): Returns but doesn't remove the first element, returns null if empty
     * - isEmpty(): Returns true if list contains no elements
     *
     * ADVANTAGES:
     * - Allows null elements (unlike ArrayDeque)
     * - Can be used as List, Queue, and Stack simultaneously
     * - No need to resize unlike array-based implementations
     * - More versatile (more interfaces implemented)
     *
     * DISADVANTAGES:
     * - Slightly worse performance than ArrayDeque for stack operations
     * - Higher memory overhead per element
     * - Not thread-safe by default
     * - Worse cache locality (elements spread in memory)
     *
     * BEST USED FOR:
     * - When you need to store null elements in a stack
     * - When you need List interface methods along with Stack operations
     * - When you're already using a LinkedList for other purposes
     * - When frequent additions/removals from both ends are needed
     */
    private static void testLinkedListAsStack() {
        System.out.println("\n--- LinkedList as Stack ---");

        Deque<String> stack = new LinkedList<>();

        // Push elements
        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        System.out.println("Stack after pushing: " + stack);

        // Push null (allowed in LinkedList)
        stack.push(null);
        System.out.println("Stack after pushing null: " + stack);

        // Peek at top element
        System.out.println("Peek: " + stack.peek());

        // Pop elements
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());

        System.out.println("Stack after popping: " + stack);
    }

    /**
     * A simple class that implements Delayed for use with DelayQueue.
     */
    private static class DelayedElement implements Delayed {
        private final String name;
        private final long endTime;

        public DelayedElement(String name, long delayInMillis) {
            this.name = name;
            this.endTime = System.currentTimeMillis() + delayInMillis;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(endTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed other) {
            return Long.compare(getDelay(TimeUnit.MILLISECONDS),
                    other.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
