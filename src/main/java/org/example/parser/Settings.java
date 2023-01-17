package org.example.parser;

import java.util.ArrayList;

public record Settings(TypeSort typeSort, TypeData typeData,
        ArrayList<String> filesList) {

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
