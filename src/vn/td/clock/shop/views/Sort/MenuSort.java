package vn.td.clock.shop.views.Sort;

import vn.td.clock.shop.model.Clock;
import vn.td.clock.shop.services.ClockService;
import vn.td.clock.shop.services.sort.*;
import vn.td.clock.shop.utils.CSVUtils;
import vn.td.clock.shop.views.ClockView;
import vn.td.clock.shop.views.ManagerClockView;

import java.util.List;
import java.util.Scanner;

public class MenuSort {
    private static Scanner scanner = new Scanner(System.in);
    static ClockView clockView = new ClockView();
    static String path = "data/products.csv";
    static ClockService clockService = new ClockService();
    static List<Clock> clockList;

    public MenuSort() {
        clockList = clockService.getClocks();
    }

    public static void sortMenu() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("|                     LỰA CHỌN HIỂN THỊ                        |");
        System.out.println("---------------------------------------------------------------");
        System.out.println("|  1. Hiển thị sắp xếp theo ID                                 |");
        System.out.println("|  2. Hiển thị sắp xếp theo tên                                |");
        System.out.println("|  3. Hiển thị sắp xếp theo số lượng                           |");
        System.out.println("|  4. Hiển thị sắp xếp theo giá                                |");
        System.out.println("|                                                  0. Quay lại |");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Vui lòng chọn chức năng: ");
        System.out.print("☛ ");
    }

    public static void option() {
        boolean flag = true;
        int choice;
        do {
            sortMenu();
            choice = Byte.parseByte(scanner.nextLine());
            switch (choice) {
                case 1:
                    showSortById();
                    break;
                case 2:
                    showSortByName();
                    break;
                case 3:
                    showSortByQuantity();
                    break;
                case 4:
                    showSortByPrice();
                    break;
                case 0:
                    ManagerClockView.run();
                    break;
                default:
                    System.out.println("Không hợp lệ, mời nhập lại!!!");
                    flag = false;
            }
        } while (!flag);
    }

    public static void showSortByQuantity() {
        boolean flag = true;
        char choice = ' ';
        do {
            System.out.println("--------------------------------------------------------");
            System.out.println("|           SẮP XẾP THEO SỐ LƯỢNG SẢN PHẨM             |");
            System.out.println("--------------------------------------------------------");
            System.out.println("| 1. Theo thứ tự từ TĂNG DẦN                           |");
            System.out.println("| 2. Theo thứ tự từ GIẢM DẦN                           |");
            System.out.println("|                                         0. Quay lại  |");
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.println("Vui lòng chọn chức năng: ");
            System.out.print("☛ ");
            try {
                choice = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                choice = ' ';
            }
            switch (choice) {
                case '1':
                    List<Clock> clockList1 = clockService.getClocks();
                    System.out.println("Sắp xếp theo SỐ LƯỢNG TĂNG DẦN");
                    SortByQuantityASC sortByQuantityASC = new SortByQuantityASC();
                    clockList1.sort(sortByQuantityASC);
                    CSVUtils.write(path, clockList1);
                    clockView.show();
                    option();
                    break;
                case '2':
                    List<Clock> clockList2 = clockService.getClocks();
                    System.out.println("Sắp xếp theo SỐ LƯỢNG GIẢM DẦN");
                    SortByQuantityDESC sortByQuantityDESC = new SortByQuantityDESC();
                    clockList2.sort(sortByQuantityDESC);
                    CSVUtils.write(path, clockList2);
                    clockView.show();
                    option();
                    break;
                case '0':
                    ManagerClockView.run();
                    break;
                default:
                    System.out.println("Vui lòng chọn lại!!!");
                    flag = false;
            }
        } while (!flag);
    }

    public static void showSortByPrice() {
        boolean flag = true;
        char choice = ' ';
        do {
            System.out.println("--------------------------------------------------------");
            System.out.println("|           SẮP XẾP THEO GIÁ SẢN PHẨM                  |");
            System.out.println("--------------------------------------------------------");
            System.out.println("| 1. Theo thứ tự từ TĂNG DẦN                           |");
            System.out.println("| 2. Theo thứ tự từ GIẢM DẦN                           |");
            System.out.println("|                                         0. Quay lại  |");
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.println("Vui lòng chọn chức năng: ");
            System.out.print("☛ ");
            try {
                choice = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                choice = ' ';
            }
            switch (choice) {
                case '1':
                    List<Clock> clockList1 = clockService.getClocks();
                    System.out.println("Sắp xếp theo GIÁ TĂNG DẦN");
                    SortByPriceASC sortByPriceASC = new SortByPriceASC();
                    clockList1.sort(sortByPriceASC);
                    CSVUtils.write(path, clockList1);
                    clockView.show();
                    option();
                    break;
                case '2':
                    List<Clock> clockList2 = clockService.getClocks();
                    System.out.println("Sắp xếp theo GIÁ GIẢM DẦN");
                    SortByPriceDESC sortByPriceDESC = new SortByPriceDESC();
                    clockList2.sort(sortByPriceDESC);
                    CSVUtils.write(path, clockList2);
                    clockView.show();
                    option();
                    break;
                case '0':
                    ManagerClockView.run();
                    break;
                default:
                    System.out.println("Vui lòng chọn lại!!!");
                    flag = false;
            }
        } while (!flag);
    }

    public static void showSortByName() {
        boolean flag = true;
        char choice = ' ';
        do {
            System.out.println("--------------------------------------------------------");
            System.out.println("|           SẮP XẾP THEO TÊN SẢN PHẨM                  |");
            System.out.println("--------------------------------------------------------");
            System.out.println("| 1. Theo thứ tự tên TĂNG DẦN                          |");
            System.out.println("| 2. Theo thứ tự tên GIẢM DẦN                          |");
            System.out.println("|                                         0. Quay lại  |");
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.println("Vui lòng chọn chức năng: ");
            System.out.print("☛ ");
            try {
                choice = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                choice = ' ';
            }
            switch (choice) {
                case '1':
                    List<Clock> clockList1 = clockService.getClocks();
                    System.out.println("Sắp xếp theo tên TĂNG DẦN");
                    SortByNameASC sortByNameASC = new SortByNameASC();
                    clockList1.sort(sortByNameASC);
                    CSVUtils.write(path, clockList1);
                    clockView.show();
                    option();
                    break;
                case '2':
                    List<Clock> clockList2 = clockService.getClocks();
                    System.out.println("Sắp xếp theo tên GIẢM DẦN");
                    SortByNameDESC sortByNameDESC = new SortByNameDESC();
                    clockList2.sort(sortByNameDESC);
                    CSVUtils.write(path, clockList2);
                    clockView.show();
                    option();
                    break;
                case '0':
                    ManagerClockView.run();
                    break;
                default:
                    System.out.println("Vui lòng chọn lại!!!");
                    flag = false;
            }
        } while (!flag);
    }

    public static void showSortById() {
        boolean flag = true;
        char choice = ' ';
        do {
            System.out.println("--------------------------------------------------------");
            System.out.println("|           SẮP XẾP THEO ID SẢN PHẨM                    |");
            System.out.println("--------------------------------------------------------");
            System.out.println("| 1. Theo thứ tự từ TĂNG DẦN                           |");
            System.out.println("| 2. Theo thứ tự từ GIẢM DẦN                           |");
            System.out.println("|                                         0. Quay lại  |");
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.println("Vui lòng chọn chức năng: ");
            System.out.print("☛ ");
            try {
                choice = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                choice = ' ';
            }
            switch (choice) {
                case '1':
                    List<Clock> clockList1 = clockService.getClocks();
                    System.out.println("Sắp xếp theo ID TĂNG DẦN");
                    SortByIDASC sortByIDASC = new SortByIDASC();
                    clockList1.sort(sortByIDASC);
                    CSVUtils.write(path, clockList1);
                    clockView.show();
                    option();
                    break;
                case '2':
                    List<Clock> clockList2 = clockService.getClocks();
                    System.out.println("Sắp xếp theo ID TĂNG DẦN");
                    SortByIDDESC sortByIDDESC = new SortByIDDESC();
                    clockList2.sort(sortByIDDESC);
                    CSVUtils.write(path, clockList2);
                    clockView.show();
                    option();
                    break;
                case '0':
                    ManagerClockView.run();
                    break;
                default:
                    System.out.println("Vui lòng chọn lại!!!");
                    flag = false;
            }
        } while (!flag);
    }


}
