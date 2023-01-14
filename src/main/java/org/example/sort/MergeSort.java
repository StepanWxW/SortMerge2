package org.example.sort;

import org.example.parser.TypeData;
import org.example.parser.TypeSort;
import org.example.parser.Settings;

import java.io.*;
import java.util.ArrayList;

public class MergeSort {
    public void sorting(Settings settings) throws IOException {
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
        String str1 = br1.readLine();
        String str2 = br2.readLine();
        Integer num1 = str1 != null ? Integer.valueOf(str1) : null;
        Integer num2 = str2 != null ? Integer.valueOf(str2) : null;
        if(typeSort == TypeSort.ASCENDING) {
            while (num1 != null && num2 != null) {
                if (num1 <= num2) num1 = writeLineAndReadNewLine(br1, writer, num1);
                else num2 = writeLineAndReadNewLine(br2, writer, num2);
            }
        } else {
            while (num1 != null && num2 != null) {
                if (num1 >= num2) num1 = writeLineAndReadNewLine(br1, writer, num1);
                else num2 = writeLineAndReadNewLine(br2, writer, num2);
            }
        }
        if (num1 == null) copyFileToOutFile(br2, writer, num2);
        if (num2 == null) copyFileToOutFile(br1, writer, num1);
    }
    private void sortString(BufferedReader br1, BufferedReader br2, BufferedWriter writer, TypeSort typeSort) throws IOException {
        String str1 = br1.readLine();
        String str2 = br2.readLine();
        int compare = str1.compareTo(str2);
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
    private void reNameOutFile(File file1, File fileOut) {
        String name = file1.getName();
        new File(name).delete();
        new File(fileOut.getName()).renameTo(new File(name));
    }
}