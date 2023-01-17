package org.example.sort;

import org.example.parser.TypeSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IntegerSortAndMerger implements SortMerger<Integer> {
    @Override
    public void sortAndMerge(BufferedReader br1, BufferedReader br2, BufferedWriter writer, TypeSort typeSort) {
        Integer num1 = readNewLineAndCheck(br1);
        Integer num2 = readNewLineAndCheck(br2);
        if (typeSort == TypeSort.ASCENDING) {
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

    public Integer readNewLineAndCheck(BufferedReader br) {
        Integer number = null;
        boolean flag = true;
        while (flag) {
            try {
                String line = br.readLine();
                if (line != null) {
                    number = Integer.valueOf(line);
                }
                flag = false;
            } catch (NumberFormatException e) {
                flag = true;
                System.out.println("Строка содержит не число, возможно есть пробел (данные этой строки будут утеряны), программа заверит работу корректно. Изучите данные строки: " + e);
            } catch (IOException e) {
                System.out.println("При чтение из файла произошла ошибка" + e);
            }
        }
        return number;
    }

    @Override
    public Integer writeLineAndReadNewLine(BufferedReader br, BufferedWriter writer, Integer numberWrite) {
        Integer numberRead = null;
        try {
            writer.write(numberWrite + "\n");
            numberRead = readNewLineAndCheck(br);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
        }
        return numberRead;
    }

    @Override
    public void copyFileToOutFile(BufferedReader br, BufferedWriter writer, Integer integer) {
        while (!(integer == null)) {
            integer = writeLineAndReadNewLine(br, writer, integer);
        }
    }
}