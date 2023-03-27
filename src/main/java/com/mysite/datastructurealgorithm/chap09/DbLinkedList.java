package com.mysite.datastructurealgorithm.chap09;

import java.util.Comparator;

/* 원형 이중 연결 리스트 클래스
필드 2개를 갖는다.
필드: head, crnt
 */
public class DbLinkedList<E> {
    /* 노드 클래스
    필드 3개와 생성자 2개를 갖는다.
    필드: data, prev, next
    생성자: (Node(), Node(E obj, Node<E> prev, Node<E> next))
     */
    class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        /* Node 생성
        data가 null이고 앞쪽 포인터와 뒤쪽 포인터가 모두 this인 노드를 생성
         */
        Node() {
            data = null;
            prev = next = this; // 자기 자신의 노드가 앞쪽 노드이면서 동시에 다음 노드가 된다.
        }

        /* Node 생성
        데이터가 obj이고, 앞쪽 포인터가 prev, 뒤쪽 포인터가 next인 노드를 생성
         */
        Node(E obj, Node<E> prev, Node<E> next) {
            data = obj;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;               // 머리 노드(더미 노드)
    private Node<E> crnt;               // 선택 노드

    /* 생성자 메서드
    비어 있는 원형 이중 연결 리스트를 생성한다.
    데이터를 갖지 않는 노드 1개만 만든다.
    이 노드는 노드의 삽입과 삭제 처리를 원활하게 하도록 리스트의 머리에 계속 존재하는 더미 노드다.
    생성자를 1번 호출하므로 더미 노드의 prev와 next는 자기 자신의 노드를 가리키도록 초기화 한다.
    head와 crnt가 가리키는 곳은 이때 생성한 더미노드다.
     */
    public DbLinkedList() {
        head = crnt = new Node<E>();
    }

    /* 리스트가 비어 있는 지(더미 노드만 있는지) 확인하는 메서드
    더미 노드의 뒤쪽 포인터 head.next가 더미 노드인 head를 가리키면 리스트는 비어 있는 것이다.
     */
    public boolean isEmpty() {
        return head.next == head;
    }

    /* 노드를 연결 검색하는 메서드
    머리 노드부터 뒤쪽 포인터를 차례로 스캔한다.
    실질적인 머리 노드가 더미 노드의 다음 노드이므로 검색의 시작은 head가 아니라 head.next다.
     */
    public E search(E obj, Comparator<? super E> c) {
        Node<E> ptr = head.next;        // 현재 스캔 중인 노드

        // ptr이 주목하고 있는 것이 꼬리 노드일 때 ptr이 가리키는 곳이 더미 노드가 된다.
        // 더미 노드는 head다.
        while (ptr != head) {
            if (c.compare(obj, ptr.data) == 0) {
                crnt = ptr;
                return ptr.data;        // 검색 성공
            }
            ptr = ptr.next;             // 다음 노드로 선택
        }
        return null;                    // 검색 실패
    }
}
