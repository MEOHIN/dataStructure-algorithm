package com.mysite.datastructurealgorithm.chap11;

public class OpenHash<K, V> {
    // 버킷 상태
    enum Status {OCCUPIED, EMPTY, DELETED}; // {데이터 저장, 비어 있음, 삭제 마침}

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
}
