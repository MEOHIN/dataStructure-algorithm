package com.mysite.datastructurealgorithm.chap10;

import java.util.Comparator;

public class BinTree<K ,V> {
    class Node<K, V> {
        K key;
        V data;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V data, Node<K, V> left, Node<K, V>right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return data;
        }

        void print() {
            System.out.println(data);
        }
    }
}
