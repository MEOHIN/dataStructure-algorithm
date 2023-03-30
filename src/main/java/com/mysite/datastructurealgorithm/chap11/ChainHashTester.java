package com.mysite.datastructurealgorithm.chap11;

import java.util.Scanner;

public class ChainHashTester {
    static Scanner stdIn = new Scanner(System.in);

    // 데이터 (회원번호 + 이름)
    static class Data {
        static final int NO = 1;    // 번호를 입력 받습니까?
        static final int NAME = 2;  // 이름을 입력 받습니까?

        private Integer no;         // 회원번호
        private String name;        // 이름

        Integer keyCode() {
            return no;
        }

        public String toString() {
            return name;
        }

        // 데이터를 입력한다.
        //      SW는 1, 2, 3 총 3개의 값을 가질 수 있다.
        //      1은 번호 검색, 2는 이름 검색, 3은 데이터 추가이다.
        void scanData(String guide, int sw) {
            System.out.println(guide + "할 데이터를 입력하세요");

            if ((sw & NO) == NO) {
                System.out.print("번호: ");
                no = stdIn.nextInt();
            }
            if ((sw & NAME) == NAME) {
                System.out.print("이름: ");
                name = stdIn.next();
            }
        }
    }

    // 메뉴 열거형
    enum Menu {
        ADD("추가"),
        REMOVE("삭제"),
        SEARCH("검색"),
        DUMP("표시"),
        TERMINATE("종료");

        private final String message;       // 출력할 문자열

        static Menu MenuAt(int idx) {       // 서수가 idx인 열거를 반환
            for (Menu m : Menu.values()) {
                if (m.ordinal() == idx) {
                    return m;
                }
            }
            return null;
        }

        Menu(String string) {               // 생성자
            message = string;
        }

        String getMessage() {               // 출력할 문자열을 반환
            return message;
        }
    }

    // 메뉴 선택
    static Menu SelectMenu() {
        int key;
        do {
            for (Menu m : Menu.values()) {
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
                if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.TERMINATE.ordinal()) {
                    System.out.println();
                }
            }
            System.out.print(" : ");
            key = stdIn.nextInt();
        } while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());
        return Menu.MenuAt(key);
    }

    public static void main(String[] args) {
        Menu menu;              // 메뉴
        Data data;              // 추가용 데이터 참조
        Data temp = new Data(); // 입력용 데이터

        ChainHash<Integer, Data> hash = new ChainHash<Integer, Data>(13);

        do {
            switch (menu = SelectMenu()) {
                case ADD:
                    data = new Data();
                    data.scanData("삽입", Data.NO | Data.NAME);
                    hash.add(data.keyCode(), data);
                    break;

                case REMOVE:
                    temp.scanData("삭제", Data.NO);
                    hash.remove(temp.keyCode());
                    break;

                case SEARCH:
                    temp.scanData("검색", Data.NO);
                    Data t = hash.search(temp.keyCode());
                    if (t != null) {
                        System.out.println("그 번호의 이름은 " + t + "입니다.");
                    } else {
                        System.out.println("해당 데이터가 없습니다.");
                    }
                    break;

                case DUMP:
                    hash.dump();
                    break;
            }
        } while (menu != Menu.TERMINATE);
    }
}