package it.polimi.db2.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileManager {

    public static String readFileAsString(String fileName)throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void leggiFile() throws Exception {
        String data = readFileAsString("D:\\Vivado\\progetto_pulito.txt");

        System.out.println(data);
    }





}


