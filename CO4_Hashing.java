package DSA_Java;

import java.util.LinkedList;

public class CO4_Hashing {

    // --- HASH TABLE WITH CHAINING ---
    public static class HashTableChaining {
        private int numBuckets;
        private LinkedList<HashNode>[] buckets;

        class HashNode {
            Integer key;
            String value;
            HashNode(Integer key, String value) {
                this.key = key;
                this.value = value;
            }
        }

        @SuppressWarnings("unchecked")
        public HashTableChaining(int capacity) {
            this.numBuckets = capacity;
            buckets = new LinkedList[capacity];
            for (int i = 0; i < capacity; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        private int getBucketIndex(Integer key) {
            return Math.abs(key.hashCode()) % numBuckets;
        }

        public void insert(Integer key, String value) {
            int bucketIndex = getBucketIndex(key);
            LinkedList<HashNode> chain = buckets[bucketIndex];
            
            for (HashNode node : chain) {
                if (node.key.equals(key)) {
                    node.value = value; // update
                    return;
                }
            }
            chain.add(new HashNode(key, value));
        }

        public String search(Integer key) {
            int bucketIndex = getBucketIndex(key);
            LinkedList<HashNode> chain = buckets[bucketIndex];
            for (HashNode node : chain) {
                if (node.key.equals(key)) return node.value;
            }
            return null;
        }
    }

    // --- HASH TABLE WITH OPEN ADDRESSING (Linear Probing) ---
    public static class HashTableOpenAddressing {
        private Integer[] keys;
        private String[] values;
        private int capacity;
        private int size;

        public HashTableOpenAddressing(int capacity) {
            this.capacity = capacity;
            keys = new Integer[capacity];
            values = new String[capacity];
        }

        private int hash(Integer key) {
            return Math.abs(key.hashCode()) % capacity;
        }

        public void insert(Integer key, String value) {
            if (size >= capacity) throw new RuntimeException("Hash Table Full");
            int i = hash(key);
            while (keys[i] != null && !keys[i].equals(key)) {
                i = (i + 1) % capacity;
            }
            if (keys[i] == null) size++; // new element inserted
            keys[i] = key;
            values[i] = value;
        }

        public String search(Integer key) {
            int i = hash(key);
            while (keys[i] != null) {
                if (keys[i].equals(key)) return values[i];
                i = (i + 1) % capacity;
            }
            return null;
        }
    }
}
