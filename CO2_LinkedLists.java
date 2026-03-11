package DSA_Java;

public class CO2_LinkedLists {

    // --- SINGLY LINKED LIST ---
    public static class SinglyLinkedList {
        class Node {
            int data;
            Node next;
            Node(int data) { this.data = data; this.next = null; }
        }
        Node head;

        public void insert(int data) {
            Node newNode = new Node(data);
            if (head == null) { head = newNode; return; }
            Node last = head;
            while (last.next != null) last = last.next;
            last.next = newNode;
        }

        public void delete(int key) {
            Node temp = head, prev = null;
            if (temp != null && temp.data == key) {
                head = temp.next;
                return;
            }
            while (temp != null && temp.data != key) {
                prev = temp;
                temp = temp.next;
            }
            if (temp == null) return;
            prev.next = temp.next;
        }

        public boolean search(int key) {
            Node current = head;
            while (current != null) {
                if (current.data == key) return true;
                current = current.next;
            }
            return false;
        }

        public void traverse() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }

        public void reverse() {
            Node prev = null, current = head, next = null;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            head = prev;
        }

        public boolean detectCycle() {
            Node slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) return true;
            }
            return false;
        }
    }

    // --- DOUBLY LINKED LIST ---
    public static class DoublyLinkedList {
        class Node {
            int data;
            Node prev, next;
            Node(int data) { this.data = data; }
        }
        Node head;

        public void insert(int data) {
            Node newNode = new Node(data);
            if (head == null) { head = newNode; return; }
            Node last = head;
            while (last.next != null) last = last.next;
            last.next = newNode;
            newNode.prev = last;
        }
        
        public void traverse() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " <-> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    // --- CIRCULAR LINKED LIST ---
    public static class CircularLinkedList {
        class Node {
            int data;
            Node next;
            Node(int data) { this.data = data; }
        }
        Node head = null;
        Node tail = null;

        public void insert(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                tail = newNode;
                newNode.next = head;
            } else {
                tail.next = newNode;
                tail = newNode;
                tail.next = head;
            }
        }
        
        public void traverse() {
            if (head == null) return;
            Node current = head;
            do {
                System.out.print(current.data + " -> ");
                current = current.next;
            } while (current != head);
            System.out.println("(head)");
        }
    }
}
