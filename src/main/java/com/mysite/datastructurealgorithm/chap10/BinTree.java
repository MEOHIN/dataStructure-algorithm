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

    private Node<K, V> root;
    private Comparator<? super K> comparator = null;

    /* 노드가 하나도 없는(비어 있는) 이진검색트리를 생성한다.
    이 생성자로 생성한 이진검색트리에서는 자연 순서에 따라 키 값을 비교하고 대소 관계를 판단한다.
     */
    public BinTree() {
        root = null;
        // 비교자를 설정하지 않으므로 comparator의 값은 null이 된다.
    }

    /* 인수로 비교자를 전달받는 생성자다.
    이 생성자로 생성한 이진검색트리에서는 노드의 대소 관계를 판단할 때 전달받은 비교자에 의해 수행한다.
     */
    public BinTree(Comparator<? super K> c) {
        this();         // 인수를 전달받지 않은 생성자 BinTree()를 호출한다. root가 null인 이진검색트리를 생성한다.
        comparator = c; // 필드 comparator에 전달받은 c를 설정한다.
    }

    private int comp(K key1, K key2) {
        return (comparator == null) ? ((Comparable<K>)key1).compareTo(key2) : comparator.compare(key1, key2);
    }

    public V search(K key) {
        Node<K, V> p = root;                    // 루트에 주목
        
        while (true) {
            if (p == null) {                    // 더 이상 진행하지 않으면
                return null;                    // 검색 실패
            }
            int cond = comp(key, p.getKey());   // key와 노드 p의 키를 비교
            if (cond == 0) {                    // 같으면
                return p.getValue();            // 검색 성공
            } else if (con < 0) {               // key 쪽이 작으면
                p = p.left;                     // 왼쪽 서브 트리에서 검색
            } else {                            // key 쪽이 크면
                p = p.right;                    // 오른쪽 서브 트리에서 검색
            }
        }
    }
}
