package vn.td.clock.shop.utils;

import java.util.Scanner;

public class AppUtils {

    public static String retryString(String fieldName) {
        Scanner scanner = new Scanner(System.in);
        String result;
        while ((result = scanner.nextLine().replaceAll("\\s","")).isEmpty()) {
            System.out.printf(fieldName);;
        }
        return result;
    }
}
