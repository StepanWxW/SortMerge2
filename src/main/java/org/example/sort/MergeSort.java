package org.example.sort;

import org.example.parser.TypeData;
import org.example.parser.TypeSort;
import org.example.parser.Settings;

import java.io.*;
import java.util.ArrayList;

public class MergeSort {
    public void sort(Settings settings) throws IOException {
        ArrayList<String> listFile = settings.getFilesList();
        if(listFile.size() == 2) {
            sortTwoFiles(new File(listFile.get(0)), new File(listFile.get(1)), settings.getTypeSort(), settings.getTypeData());
        } else {
           File file = sortTwoFiles(new File(listFile.get(1)), new File(listFile.get(2)), settings.getTypeSort(), settings.getTypeData(), new File(listFile.get(0)));
            for (int i = 3; i < listFile.size(); i++) {
                sortTwoFiles(file, new File(listFile.get(i)), settings.getTypeSort(), settings.getTypeData());
            }
        }
    }

    private File sortTwoFiles(File file1, File file2, TypeSort typeSort, TypeData typeData, File file) throws IOException {
        if(!file.exists()) file1.createNewFile();
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if(typeData == TypeData.STRING){
                sortString(br1, br2, writer, typeSort);
            } else {
                sortInteger(br1, br2, writer, typeSort);
            }
        } catch (IOException e) {
            System.out.println("Problem with sort files");
        }
        return file;
    }

    private void sortTwoFiles(File file1, File file2, TypeSort typeSort, TypeData typeData) throws IOException {
        File tempFile = new File("tempFile.txt");
        if(!file1.exists()) file1.createNewFile();
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            if(typeData == TypeData.STRING){
                sortString(br1, br2, writer, typeSort);
            } else {
                sortInteger(br1, br2, writer, typeSort);
            }
        } catch (IOException e) {
            System.out.println("Problem with sort files");
        }
        file1.delete();
        new File(tempFile.getName()).renameTo(new File(file1.getName()));
    }
    private void sortInteger(BufferedReader br1, BufferedReader br2, BufferedWriter writer, TypeSort typeSort) throws IOException {
        String lineOne = br1.readLine();
        String lineTwo = br2.readLine();
        Integer num1 = lineOne != null ? Integer.valueOf(lineOne) : null;
        Integer num2 = lineTwo != null ? Integer.valueOf(lineTwo) : null;
        Integer[] array = {num1, num2};
        while (array[0] != null && array[1] != null) {
                compare(array, br1, br2, writer, typeSort == TypeSort.ASCENDING);
            }
        if (array[0] == null) copyFileToOutFile(br2, writer, array[1]);
        if (array[1] == null) copyFileToOutFile(br1, writer, array[0]);
    }
    private void sortString(BufferedReader br1, BufferedReader br2, BufferedWriter writer, TypeSort typeSort) throws IOException {
        String str1 = br1.readLine();
        String str2 = br2.readLine();
        int compare = 0;
        if (str1 != null && str2 != null) {
            compare = str1.compareTo(str2);
        }
        if(typeSort == TypeSort.ASCENDING) {
            while (str1 != null && str2 != null){
                if (compare >= 0) str2 = writeLineAndReadNewLine(br1, writer, str2);
                else str1 = writeLineAndReadNewLine(br1, writer, str1);
            }
        } else {
            while (str1 != null && str2 != null) {
                if (compare <= 0) str2 = writeLineAndReadNewLine(br1, writer, str2);
                else str1 = writeLineAndReadNewLine(br1, writer, str1);
            }
        }
        if (str2 == null) copyFileToOutFile(br1, writer, str1);
        if (str1 == null) copyFileToOutFile(br2, writer, str2);
    }
    private Integer[] compare(Integer[] array, BufferedReader br1, BufferedReader br2, BufferedWriter writer, boolean sign) throws IOException {
        if ((array[0] <= array[1]) == sign) {
                array[0] = writeLineAndReadNewLine(br1, writer, array[0]);
            }
            else {
                array[1] = writeLineAndReadNewLine(br2, writer, array[1]);
            }
        return array;
    }
    private Integer writeLineAndReadNewLine(BufferedReader br1, BufferedWriter writer, Integer file) throws IOException {
        writer.write(file + "\n");
        String str = br1.readLine();
        return str != null ? Integer.valueOf(str) : null;
    }
    private String writeLineAndReadNewLine(BufferedReader br1, BufferedWriter writer, String file) throws IOException {
        writer.write(file + "\n");
        return br1.readLine();
    }
    private void copyFileToOutFile(BufferedReader br, BufferedWriter writer, Integer integer) throws IOException {
        while (!(integer == null)){
            integer = writeLineAndReadNewLine(br, writer, integer);
        }
    }
    private void copyFileToOutFile(BufferedReader br, BufferedWriter writer, String str) throws IOException {
        while (!(str == null)){
            str = writeLineAndReadNewLine(br, writer, str);
        }
    }
}