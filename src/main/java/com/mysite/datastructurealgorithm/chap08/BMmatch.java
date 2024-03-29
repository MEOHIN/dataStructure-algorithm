package com.mysite.datastructurealgorithm.chap08;

import java.util.Scanner;

public class BMmatch {
    static int bmMatch(String txt, String pat) {
        int pt;
        int pp;
        int txtLen = txt.length();
        int patLen = pat.length();
//        패턴에 존재할 수 있는 모든 문자의 옮길 크기를 계산하고 저장해야 하므로 건너뛰기 표의 요소 개수는 Character.MAX_VALUE + 1이다.
        int[] skip = new int[Character.MAX_VALUE + 1];

        // 건너뛰기 표 만들기
        for (pt = 0; pt <= Character.MAX_VALUE; pt++) {
            skip[pt] = patLen;
        }
        for (pt = 0; pt < patLen - 1; pt++) {
            skip[pat.charAt(pt)] = patLen - pt - 1; // pt == patLen - 1
        }

        // 검색
        while (pt < txtLen) {
            pp = patLen - 1;    // pat의 끝 문자에 주목

            while (txt.charAt(pt) == pat.charAt(pp)) {
                if (pp == 0) {
                    return pt;
                }
                pp--;
                pt--;
            }
            pt += (skip[txt.charAt(pt)] > patLen - pp) ? skip[txt.charAt(pt)] : patLen - pp;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("텍스트: ");
        String s1 = stdIn.next();   // 문자열 원본

        System.out.print("패턴: ");
        String s2 = stdIn.next();   // 검색할 문자열

        int idx = bmMatch(s1, s2); // 문자열 s1에서 문자열 s2를 검색

        if (idx == -1) {
            System.out.println("텍스트에패턴이 없습니다.");
        } else {
            // 일치하는 문자 바로 앞까지의 길이를 구한다.
            int len = 0;
            for (int i = 0; i < idx; i++) {
                len += s1.substring(i, i + 1).getBytes().length;
            }
            len += s2.length();

            System.out.println((idx + 1) + "번째 문자부터 일치합니다.");
            System.out.println("텍스트: " + s1);
            System.out.printf(String.format("패턴: %%%ds\n", len), s2);
        }
    }
}
