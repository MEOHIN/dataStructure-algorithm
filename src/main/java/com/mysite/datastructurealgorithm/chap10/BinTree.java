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
            } else if (cond < 0) {               // key 쪽이 작으면
                p = p.left;                     // 왼쪽 서브 트리에서 검색
            } else {                            // key 쪽이 크면
                p = p.right;                    // 오른쪽 서브 트리에서 검색
            }
        }
    }

    private void addNode(Node<K, V> node, K key, V data) {
        int cond = comp(key, node.getKey());
        if (cond == 0) {
            return;                              // key가 이진검색트리에 이미 있음
        } else if (cond < 0) {
            if (node.left == null) {
                node.left = new Node<K, V>(key, data, null, null);
            } else {
                addNode(node.left, key, data);  // 왼쪽 서브 트리에 주목
            }
        } else {
            if (node.right == null) {
                node.right = new Node<K, V>(key, data, null, null);
            } else {
                addNode(node.right, key, data); // 오른쪽 서브 트리에 주목
            }
        }
    }

    public void add(K key, V data) {
        if (root == null) {
            root = new Node<K, V>(key, data, null, null);
        } else {
            addNode(root, key, data);
        }
    }

    public boolean remove(K key) {
        Node<K, V> p = root;                    // 스캔 중인 노드
        Node<K, V> parent = null;               // 스캔 중인 노드의 부모 노드
        boolean isleftChild = true;             // p는 부모의 왼쪽 자식 노드인가?

        while (true) {
            if (p == null) {                    // 더 이상 진행하지 않으면
                return false;                   // 그 키 값은 없다.
            }
            int cond = comp(key, p.getKey());   // key와 노드 p의 키 값을 비교
            if (cond == 0) {                    // 같으면
                break;                          // 검색 성공
            } else {
                parent = p;                     // 가지로 내려가기 전에 부모를 설정
                if (cond < 0) {                 // key 쪽이 작으면
                    isleftChild = true;         // 왼쪽 자식으로 내려감
                    p = p.left;                 // 왼쪽 서브 트리에서 검색
                } else {                        // key 쪽이 크면
                    isleftChild = false;        // 오른쪽 자식으로 내려감
                    p = p.right;                // 오른쪽 서브 트리에서 검색
                }
            }
        }

        if (p.left == null) {               // p에는 왼쪽 자식이 없음
            if (p == root) {
                root = p.right;
            } else if (isleftChild) {
                parent.left = p.right;      // 부모의 왼쪽 포인터가 오른쪽 자식을 가리킴
            } else {
                parent.right = p.right;     // 부모의 오른쪽 포인터가 오른쪽 자식을 가리킴
            }
        } else if (p.right == null) {       // p에는 오른쪽 자식이 없음
            if (p == root) {
                root = p.left;
            } else if (isleftChild) {
                parent.left = p.left;       // 부모의 왼쪽 포인터가 왼쪽 자식을 가리킴
            } else {
                parent.right = p.left;      // 부모의 오른쪽 포인터가 왼쪽 자식을 가리킴
            }
        } else {
            parent = p;
            Node<K, V> left = p.left;       // 서브 트리 가운데 가장 큰 노드
            isleftChild = true;
            while (left.right != null) {    // 가장 큰 노드 left 검색
                parent = left;
                left = left.right;
                isleftChild = false;
            }
            p.key = left.key;               // left의 키 값을 p로 옮김
            p.data = left.data;             // left의 데이터를 p로 옮김
            if (isleftChild) {
                parent.left = left.left;    // left 삭제
            } else {
                parent.right = left.left;   // left 삭제
            }
        }
        return true;
    }

    // node를 루트로 하는 서브 트리의 노드를 키 값의 오름 차순으로 출력
    private void printSubTree(Node node) {
        if (node != null) {
            printSubTree(node.left);    // 왼쪽 서브 트리를 키 값의 오름차순으로 출력
            System.out.println(node.key + " " + node.data); // node를 출력
            printSubTree(node.right);   // 오른쪽 서브 트리를 키 값의 오름차순으로 출력
        }
    }

    // 모든 노드를 키 값의 오름차순으로 출력
    public void print() {
        printSubTree(root);
    }
}
