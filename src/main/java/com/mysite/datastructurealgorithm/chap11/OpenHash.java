package com.mysite.datastructurealgorithm.chap11;

public class OpenHash<K, V> {
    // 버킷 상태
    enum Status {OCCUPIED, EMPTY, DELETED} // {데이터 저장, 비어 있음, 삭제 마침}

    // 버킷
    static class Bucket<K, V> {
        private K key;
        private V data;
        private Status stat;

        Bucket() {
            stat = Status.EMPTY;
        }

        // 모든 필드에 값을 설정한다.
        void set(K key, V data, Status stat) {
            this.key = key;
            this.data = data;
            this.stat = stat;
        }

        public void setStat(Status stat) {
            this.stat = stat;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return data;
        }

        public int hashCode() {
            return key.hashCode();
        }
    }

    private int size;
    private Bucket<K, V>[] table;

    public OpenHash(int size) {
        try {
            table = new Bucket[size];
            for (int i = 0; i < size; i++) {
                table[i] = new Bucket<K, V>();
            }
            this.size = size;
        } catch (OutOfMemoryError e) {
            this.size = 0;
        }
    }

    public int hashValue(Object key) {
        return key.hashCode() % size;
    }

    public int rehashValue(int hash) {
        return (hash + 1) % size;
    }

    private Bucket<K, V> searchNode(K key) {
        int hash = hashValue(key);      // 검색할 데이터의 해시 값
        Bucket<K, V> p = table[hash];   // 선택 버킷

        for (int i = 0; p.stat != Status.EMPTY && i < size; i++) {
            if (p.stat == Status.OCCUPIED && p.getKey().equals(key)) {
                return p;
            }
            hash = rehashValue(hash);   // 재해시
            p = table[hash];
        }
        return null;
    }

    public V search(K key) {
        Bucket<K, V> p = searchNode(key);
        if (p != null) {
            return p.getValue();
        } else {
            return null;
        }
    }

    public int add(K key, V data) {
        if (search(key) != null) {
            return 1;                   // 이 키 값은 이미 등록됨
        }

        int hash = hashValue(key);      // 추가할 데이터의 해시 값
        Bucket<K, V> p = table[hash];   // 선택 버킷
        for (int i = 0; i < size; i++) {
            if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
                p.set(key, data, Status.OCCUPIED);
                return 0;
            }
            hash = rehashValue(hash);   // 재해시
            p = table[hash];
        }
        return 2;
    }

    public int remove(K key) {
        Bucket<K, V> p = searchNode(key);// 선택 버킷
        if (p == null) {
            return 1;                   // 이 키 값은 등록되지 않음
        }

        p.setStat(Status.DELETED);
        return 0;
    }

    public void dump() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%02d ", i);
            switch (table[i].stat) {
                case OCCUPIED:
                    System.out.printf("%s (%s)\n", table[i].getKey(), table[i].getValue());
                    break;

                case EMPTY:
                    System.out.println("-- 미등록 --");
                    break;

                case DELETED:
                    System.out.println("-- 삭제 마침 --");
                    break;
            }
        }
    }
}
