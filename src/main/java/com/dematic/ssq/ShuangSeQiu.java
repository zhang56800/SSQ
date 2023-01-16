package com.dematic.ssq;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class ShuangSeQiu {

    public static void main(String[] args) {
        HashMap<String, Integer> numberList = new HashMap<>();




        for (int k=0; k<79999999;k++) {

            ArrayList<Integer> redBallList = new ArrayList<>();
            ArrayList<Integer> blueBallList = new ArrayList<>();
            int redBall = 34;
            int blueBall = 17;
            for (int i = 1; i < redBall; i++) {
                redBallList.add(i);
            }
            for (int i = 1; i < blueBall; i++) {
                blueBallList.add(i);
            }
            Random random = new Random();

            StringBuilder number = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                int redRandom = random.nextInt(redBallList.size() - 1);
                Integer red = redBallList.remove(redRandom);
                number.append(" ").append(red);
            }

            int blue = random.nextInt(15);
            Integer integer = blueBallList.get(blue);
            number.append(" ").append(integer);

            String s = number.toString();

            if (numberList.containsKey(s)) {
                numberList.put(s, numberList.get(s) + 1);
            } else {
                numberList.put(s, 0);
            }
        }

        Stream<Map.Entry<String, Integer>> sorted = numberList.entrySet()
              .stream().filter(e->(e.getValue()!=0))
              .sorted(Map.Entry.comparingByValue());

        sorted.forEach(System.out::println);

    }



}
