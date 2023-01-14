package org.example;

import org.apache.commons.lang3.tuple.Pair;
import org.example.parser.Parser;
import org.example.parser.Settings;
import org.example.sort.MergeSort;


public class Main {
    public static void main(String[] args) {

        String[] args1 = new String[7];
        args1[0] = "-i";
//        args1[1] = "-a";
        args1[1] = "-d";
        args1[2] = "out.txt";
//        args1[3] = "in1.txt";
//        args1[4] = "in2.txt";
//        args1[5] = "in3.txt";

        args1[3] = "ind1.txt";
        args1[4] = "ind2.txt";
        args1[5] = "ind3.txt";
        args1[6] = "ind4.txt";

        try {
            Parser parser = new Parser();
            Settings settings = parser.parseArgs(args1);
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(settings);
        } catch (Exception e) {
            System.out.println(e + "Что-то пошло не так. Общая ошибка");
        }
    }
}
