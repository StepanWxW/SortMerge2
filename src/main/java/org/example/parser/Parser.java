package org.example.parser;


import java.util.ArrayList;

import static org.example.util.Constants.EXPLANATION_MESSAGE;


public class Parser {
    private TypeSort typeSort = TypeSort.ASCENDING;
    private TypeData typeData;
    private final ArrayList<String> filesList = new ArrayList<>();

    public Settings parsingArgs(String[] args) {

        if (args.length >= 3) {
            for (String arg : args) {
                switch (arg) {
                    case ("-a") -> typeSort = TypeSort.ASCENDING;
                    case ("-d") -> typeSort = TypeSort.DESCENDING;
                    case ("-i") -> typeData = TypeData.INTEGER;
                    case ("-s") -> typeData = TypeData.STRING;
                    default -> filesList.add(arg);
                }
            }
        } else {
            System.out.println("Вы не указали мало параметров\n" + "Подсказка: \n" +EXPLANATION_MESSAGE);
        } if(typeData == null) {
            System.out.println("Вы не указали тип данных: -i или -n \n" + "Подсказка: \n" +EXPLANATION_MESSAGE);
        }
        return new Settings(typeSort, typeData, filesList);
    }
}
