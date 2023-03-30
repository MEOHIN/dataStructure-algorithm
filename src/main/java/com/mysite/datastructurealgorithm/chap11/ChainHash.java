package com.mysite.datastructurealgorithm.chap11;

public class ChainHash<K, V> {
    class Node<K, V> {
        private K key;
        private V data;
        private Node<K, V> next;

        Node(K key, V data, Node<K, V> next) {
            this.key = key;
            this.data = data;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return data;
        }

        // 키 값의 해시 값을 반환한다.
        public int hashCode() {
            return key.hashCode();
        }
    }
}
