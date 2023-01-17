package org.example.sort;

import org.example.parser.TypeSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface SortMerger<T> {
    void sortAndMerge(BufferedReader br1, BufferedReader br2, BufferedWriter writer, TypeSort typeSort);

    T writeLineAndReadNewLine(BufferedReader br1, BufferedWriter writer, T file);
    T readNewLineAndCheck(BufferedReader br);

    void copyFileToOutFile(BufferedReader br, BufferedWriter writer, T integer);
}