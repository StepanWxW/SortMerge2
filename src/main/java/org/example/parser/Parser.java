package org.example.parser;


import java.util.ArrayList;

public class Parser {
    private TypeSort typeSort = TypeSort.ASCENDING;
    private TypeData typeData = TypeData.NOT_INSTALLED;
    private ArrayList<String> filesList = new ArrayList<>();


    public Settings parsingArgs(String[] args) {
        try {
            if (args.length > 3) {
                for (String arg : args) {
                    switch (arg) {
                        case ("-d"):
                            typeSort = TypeSort.DESCENDING;
                            break;
                        case ("-a"):
                            typeSort = TypeSort.ASCENDING;
                            break;
                        case ("-i"):
                            typeData = TypeData.INTEGER;
                            break;
                        case ("-s"):
                            typeData = TypeData.STRING;
                            break;
                        default:
                            filesList.add(arg);
                            break;
                    }
                }
            } else if (typeData == TypeData.NOT_INSTALLED) {
                Helper.help();
            } else {
                Helper.help();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            Helper.help();
        }
        return new Settings(typeSort, typeData, filesList);
    }
}
