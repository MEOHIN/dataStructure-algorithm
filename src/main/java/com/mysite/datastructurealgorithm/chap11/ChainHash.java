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

    private int size;
    private Node<K, V>[] table;

    public ChainHash(int capacity) {
        try {
            table = new Node[capacity];
            this.size = capacity;
        } catch (OutOfMemoryError e) {
            this.size = 0;
        }
    }

    // 해시 값을 구한다.
    public int hashValue(Object key) {
        return key.hashCode() % size;
    }

    public V search(K key) {
        int hash = hashValue(key);          // 검색할 데이터의 해시 값
        Node<K, V> p = table[hash];         // 선택 노드

        while (p != null) {
            if (p.getKey().equals(key)) {
                return p.getValue();        // 검색 성공
            }
            p = p.next;                     // 다음 노드에 주목
        }
        return null;                        // 검색 실패
    }
    
    public int add(K key, V data) {
        int hash = hashValue(key);          // 추가할 데이터의 해시 값
        Node<K, V> p = table[hash];         // 선택 노드
        
        while (p != null) {
            if (p.getKey().equals(key)) {   // 이 키 값은 이미 등록됨
                return 1;
            }
            p = p.next;
        }
        Node<K, V> tmep = new Node<K, V>(key, data, table[hash]);
        table[hash] = tmep;                 // 노드를 삽입
        return 0;
    }

    public int remove(K key, V data) {
        int hash = hashValue(key);          // 삭제할 제이터의 해시 값
        Node<K, V>  p = table[hash];        // 선택 노드
        Node<K, V>  pp = null;              // 바로 앞의 선택 노드

        while (p != null) {
            if (p.getKey().equals(key)) {
                if (pp == null) {
                    table[hash] = p.next;
                } else {
                    pp.next = p.next;
                }
                return 0;
            }
            pp = p;
            p = p.next;
        }
        return 1;                           // 그 키 값은 없다.
    }
}
