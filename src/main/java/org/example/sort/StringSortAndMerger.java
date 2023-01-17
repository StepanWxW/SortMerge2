package org.example.sort;

import org.example.parser.TypeSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class StringSortAndMerger implements SortMerger<String> {
    @Override
    public void sortAndMerge(BufferedReader br1, BufferedReader br2, BufferedWriter writer, TypeSort typeSort) {
        String str1 = readNewLineAndCheck(br1);
        String str2 = readNewLineAndCheck(br2);
        if (typeSort == TypeSort.ASCENDING) {
            while (str1 != null && str2 != null) {
                if (str1.compareTo(str2) >= 0) str2 = writeLineAndReadNewLine(br2, writer, str2);
                else str1 = writeLineAndReadNewLine(br1, writer, str1);
            }
        } else {
            while (str1 != null && str2 != null) {
                if (str1.compareTo(str2) <= 0) str2 = writeLineAndReadNewLine(br2, writer, str2);
                else str1 = writeLineAndReadNewLine(br1, writer, str1);
            }
        }
        if (str2 == null) copyFileToOutFile(br1, writer, str1);
        if (str1 == null) copyFileToOutFile(br2, writer, str2);
    }

    @Override
    public String writeLineAndReadNewLine(BufferedReader br, BufferedWriter writer, String file) {
        String str = null;
        try {
            writer.write(file + "\n");
            str = readNewLineAndCheck(br);
        } catch (IOException e) {
            System.out.println("Ошибка записи строки в файл");
        }
        return str;
    }

    public String readNewLineAndCheck(BufferedReader br) {
        String line = null;
        boolean flag = true;
        while (flag) {
            try {
                line = br.readLine();
                if (line == null) {
                    flag = false;
                } else {
                    if (line.contains(" ")) {
                        System.out.println("Данная строка содержит пробел: " + line
                                + " она будет утеряна. Программа завершит работу корректно");
                    } else {
                        flag = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка чтения строки");
            }
        }
        return line;
    }

    @Override
    public void copyFileToOutFile(BufferedReader br, BufferedWriter writer, String str) {
        while (!(str == null)) {
            str = writeLineAndReadNewLine(br, writer, str);
        }
    }
}
