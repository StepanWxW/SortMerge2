package org.example;

import org.example.parser.Parser;
import org.example.parser.Settings;
import org.example.parser.TypeData;
import org.example.sort.IntegerSortAndMerger;
import org.example.sort.MergeSort;

import org.example.sort.StringSortAndMerger;

public class Main {
    public static void main(String[] args) {
        MergeSort mergeSort = null;
        try {
            Parser parser = new Parser();
            Settings settings = parser.parsingArgs(args);
            if (settings.getTypeData() == TypeData.STRING) {
                mergeSort = new MergeSort(new StringSortAndMerger());
            }
            if (settings.getTypeData() == TypeData.INTEGER) {
                mergeSort = new MergeSort(new IntegerSortAndMerger());
            }
            if(mergeSort != null) mergeSort.sortAndMerge(settings);
        } catch (Exception e) {
            System.out.println(e + "Что-то пошло не так. Общая ошибка");
        }
    }
}