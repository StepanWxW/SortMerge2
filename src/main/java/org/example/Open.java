package org.example;

import java.io.*;

public class Open {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("out.txt"))){

            while (true){
                String s = br.readLine();
                if(s != null) {
                    System.out.println(s);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка");;
        }
    }
}
