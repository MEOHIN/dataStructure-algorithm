package com.mysite.datastructurealgorithm.chap06;

import java.util.Arrays;
import java.util.Comparator;

public class PhyscExamSort {
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

        public static final Comparator<PhysicData> HEIGHT_ORDER = new PhysicData.HeightOrderComparator();

        private static class HeightOrderComparator implements Comparator<PhysicData> {
            public int compare(PhysicData d1, PhysicData d2) {
                return (d1.height) > d2.height ? 1 : (d1.height < d2.height) ? -1 : 0;
            }
        }
    }

    public static void main(String[] args) {
        PhysicData[] x = {
                new PhysicData("박현규", 162, 0.3),
                new PhysicData("함진아", 172, 0.7),
                new PhysicData("최윤미", 175, 2.0),
                new PhysicData("홍연의", 171, 1.5),
                new PhysicData("이수진", 168, 0.4),
                new PhysicData("김영준", 174, 1.2),
                new PhysicData("박용규", 169, 0.8),
        };

        Arrays.sort(x, PhysicData.HEIGHT_ORDER);

        System.out.println("■  신체검사 리스트  ■");
        System.out.println("이름       키   시력");
        System.out.println("------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
        }
    }
}