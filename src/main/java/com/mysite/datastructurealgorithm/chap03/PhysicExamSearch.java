package com.mysite.datastructurealgorithm.chap03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PhysicExamSearch {

    static class PhysicData {
        String name;
        int height;
        double vision;

        PhysicData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }

        public String toString() {
            return name + " " + height + " " + vision;
        }

        public static final Comparator<PhysicData> HEIGHT_ORDER = new HeightOrderComparator();

        private static class HeightOrderComparator implements Comparator<PhysicData> {
            public int compare(PhysicData d1, PhysicData d2) {
                return (d1.height) > d2.height ? 1 : (d1.height < d2.height) ? -1 : 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        PhysicData[] x = {
                new PhysicData("박현규", 162, 0.3),
                new PhysicData("함진아", 172, 0.7),
                new PhysicData("최윤미", 175, 2.0),
                new PhysicData("홍연의", 171, 1.5),
                new PhysicData("이수진", 168, 0.4),
                new PhysicData("김영준", 174, 1.2),
                new PhysicData("박용규", 169, 0.8),
        };

        System.out.print("몇 cm인 사람을 찾고 있나요? :");
        int height = stdIn.nextInt();
        int idx = Arrays.binarySearch(
                x,
                new PhysicData("", height, 0.0),
                PhysicData.HEIGHT_ORDER
        );

        if (idx < 0) {
            System.out.println("요소가 없습니다.");
        } else {
            System.out.println("x[" + idx + "]에 있습니다.");
            System.out.println("찾은 데이터: " + x[idx]);
        }
    }
}
