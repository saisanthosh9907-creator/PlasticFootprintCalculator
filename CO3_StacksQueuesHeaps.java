package DSA_Java;

import java.util.ArrayList;

public class CO3_StacksQueuesHeaps {

    // --- STACK (Array-based) ---
    public static class ArrayStack {
        private int[] arr;
        private int top;
        private int capacity;

        public ArrayStack(int size) {
            arr = new int[size];
            capacity = size;
            top = -1;
        }

        public void push(int x) {
            if (isFull()) throw new RuntimeException("Stack Overflow");
            arr[++top] = x;
        }

        public int pop() {
            if (isEmpty()) throw new RuntimeException("Stack Underflow");
            return arr[top--];
        }

        public boolean isEmpty() { return top == -1; }
        public boolean isFull() { return top == capacity - 1; }
    }
    
    // --- STACK (Linked List-based) ---
    public static class LinkedStack {
        class Node {
            int data;
            Node next;
            Node(int data) { this.data = data; }
        }
        private Node top;
        
        public void push(int x) {
            Node newNode = new Node(x);
            newNode.next = top;
            top = newNode;
        }
        
        public int pop() {
            if (isEmpty()) throw new RuntimeException("Stack Underflow");
            int val = top.data;
            top = top.next;
            return val;
        }
        
        public boolean isEmpty() { return top == null; }
    }

    // --- CIRCULAR QUEUE (Array-based) ---
    public static class CircularQueue {
        private int[] arr;
        private int front, rear, size, capacity;

        public CircularQueue(int cap) {
            capacity = cap;
            arr = new int[capacity];
            front = size = 0;
            rear = capacity - 1;
        }

        public void enqueue(int item) {
            if (isFull()) return;
            rear = (rear + 1) % capacity;
            arr[rear] = item;
            size++;
        }

        public int dequeue() {
            if (isEmpty()) return Integer.MIN_VALUE;
            int item = arr[front];
            front = (front + 1) % capacity;
            size--;
            return item;
        }

        public boolean isFull() { return size == capacity; }
        public boolean isEmpty() { return size == 0; }
    }

    // --- MAX HEAP / PRIORITY QUEUE ---
    public static class MaxHeap {
        private ArrayList<Integer> heap = new ArrayList<>();

        public void insert(int val) {
            heap.add(val);
            heapifyUp(heap.size() - 1);
        }

        public int extractMax() {
            if (heap.isEmpty()) throw new RuntimeException("Heap is empty");
            int max = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) heapifyDown(0);
            return max;
        }

        private void heapifyUp(int i) {
            int parent = (i - 1) / 2;
            if (i > 0 && heap.get(i) > heap.get(parent)) {
                swap(i, parent);
                heapifyUp(parent);
            }
        }

        private void heapifyDown(int i) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (left < heap.size() && heap.get(left) > heap.get(largest)) largest = left;
            if (right < heap.size() && heap.get(right) > heap.get(largest)) largest = right;

            if (largest != i) {
                swap(i, largest);
                heapifyDown(largest);
            }
        }

        private void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}
