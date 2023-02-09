package com.mysite.datastructurealgorithm.chap03;

import java.util.Arrays;
import java.util.Scanner;

public class StringBinarySearch {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        String[] x = {
                "int", "assert", "boolean"
        };

        System.out.print("원하는 키워드를 입력하세요: ");
        String ky = stdIn.next();

        int idx = Arrays.binarySearch(x, ky);

        if (idx < 0) {
            System.out.println("해당 키워드가 없습니다.");
        } else {
            System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");
        }
    }
}
