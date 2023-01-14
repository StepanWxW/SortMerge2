package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Create {
    public static void main(String[] args) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("file1_000_000(2).txt"))){
                for (long i=0; i<1_000_000; i=i+2){
                    bf.write(i +"\n");
                }
        } catch (IOException e) {
            System.out.println("Ошибка");;
        }

    }
}