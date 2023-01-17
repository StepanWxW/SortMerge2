package org.example.sort;

import org.example.parser.Settings;
import org.example.parser.TypeSort;

import java.io.*;
import java.util.ArrayList;

public record MergeSort<T>(SortMerger<T> sortMerger) {

    public void sortAndMerge(Settings settings) throws IOException {
        ArrayList<String> listFile = settings.getFilesList();
        if (listFile.size() == 2) {
            sortTwoFiles(new File(listFile.get(0)), new File(listFile.get(1)), settings.getTypeSort());
        } else {
            File file = sortTwoFiles(new File(listFile.get(1)), new File(listFile.get(2)), settings.getTypeSort(), new File(listFile.get(0)));
            for (int i = 3; i < listFile.size(); i++) {



                sortTwoFiles(file, new File(listFile.get(i)), settings.getTypeSort());



            }
        }
    }


    private void sortTwoFilesTest(File file1, File file2, TypeSort typeSort) throws IOException {
        File tempFile = new File("tempFile.txt");
        if (!file1.exists()) file1.createNewFile();
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            sortMerger.sortAndMerge(br1, br2, writer, typeSort);
        } catch (IOException e) {
            System.out.println(e + "Problem with sort files");
        }
        file1.delete();
        new File(tempFile.getName()).renameTo(new File(file1.getName()));
    }






    private File sortTwoFiles(File file1, File file2, TypeSort typeSort, File file) throws IOException {
        if (!file.exists()) file1.createNewFile();
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            sortMerger.sortAndMerge(br1, br2, writer, typeSort);
        } catch (IOException e) {
            System.out.println(e + "Problem with sort files");
        }
        return file;
    }

    private void sortTwoFiles(File file1, File file2, TypeSort typeSort) throws IOException {
        File tempFile = new File("tempFile.txt");
        if (!file1.exists()) file1.createNewFile();
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            sortMerger.sortAndMerge(br1, br2, writer, typeSort);
        } catch (IOException e) {
            System.out.println(e + "Problem with sort files");
        }
        file1.delete();
        new File(tempFile.getName()).renameTo(new File(file1.getName()));
    }
}
