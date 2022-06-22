package vn.td.clock.shop.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static <T> void write(String path, List<T> items) {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            for (Object item : items) {
                printWriter.println(item.toString());
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(path + "invalid");
        }
    }


//    public static List<String> read(String path) {
//        try {
//            return Files.readAllLines(Paths.get(path));
//        } catch (IOException e) {
//            throw new IllegalArgumentException(path + "invalid");
//        }
//    }

    public static List<String> read(String path) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty())
                lines.add(line);
        } catch (IOException e) {
            throw new IllegalArgumentException(path + " invalid");
        }
        return lines;
    }



//    public static ArrayList<String> read(String path) {
//        ArrayList<String> fileData = new ArrayList<>();
//        String temp;
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
//            while ((temp = bufferedReader.readLine()) != null && !temp.trim().isEmpty()) fileData.add(temp);
//        } catch (FileNotFoundException fileNotFoundException) {
//            System.out.println("Không tìm thấy file!");
//        } catch (IOException ioException) {
//            throw new RuntimeException("Không đọc được file!");
//        }
//        return fileData;
//    }

}

