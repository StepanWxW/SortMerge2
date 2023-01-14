package org.example.parser;

import org.example.parser.TypeData;
import org.example.parser.TypeSort;

import java.util.ArrayList;

public class Settings {
    private TypeSort typeSort;
    private TypeData typeData;
    private ArrayList<String> filesList;

    public Settings(TypeSort typeSort, TypeData typeData, ArrayList<String> filesList) {
        this.typeSort = typeSort;
        this.typeData = typeData;
        this.filesList = filesList;
    }

    public TypeSort getTypeSort() {
        return typeSort;
    }

    public TypeData getTypeData() {
        return typeData;
    }

    public ArrayList<String> getFilesList() {
        return filesList;
    }
}
