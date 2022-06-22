package vn.td.clock.shop.views;

import vn.td.clock.shop.model.Clock;
import vn.td.clock.shop.services.ClockService;

import java.util.List;
import java.util.Scanner;

public class SearchMenu {
    static Scanner scanner = new Scanner(System.in);
    static ClockView clockView = new ClockView();
    static ClockService clockService = new ClockService();

    public static void searchMenu() {
        clockView.show();
        boolean isChoice = true;
        char choice = ' ';
        do {
            System.out.println("------------------------------------------------------------");
            System.out.println("|                 TÌM KIẾM SẢN PHẨM                        |");
            System.out.println("------------------------------------------------------------");
            System.out.println("| 1. Tìm kiếm theo ID                                      |");
            System.out.println("| 2. Tìm kiếm theo tên                                     |");
            System.out.println("| 3. Tìm kiếm theo số lượng                                |");
            System.out.println("| 4. Tìm kiếm theo giá                                     |");
            System.out.println("|                                                          |");
            System.out.println("| 0. Quay lại                                              |");
            System.out.println(" -----------------------------------------------------------");
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
                    searchById();
                    break;
                case '2':
                    searchByName();
                    break;
                case '3':
                    searchByQuantity();
                    break;
                case '4':
                    searchByPrice();
                    break;
                case '0':
                    ManagerClockView.run();
                    isChoice = false;
                    break;
                default:
                    System.out.println("Chưa hợp lệ! Mời Nhập Lại!!!");
            }
        } while (isChoice);
    }

    public static void searchByQuantity() {
        List<Clock> clocks = clockService.getClocks();
        int count = 0;
        System.out.println("Nhập số lượng của sản phẩm cần tìm kiếm: ");
        try {
            int search = Integer.parseInt(scanner.nextLine());
            System.out.println("Kết quả :  '" + search + "' là : ");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("| %-10s %-30s %-18s %-15s %-15s \n", "Id", "Tên Clock", "Giá", "Số lượng", "Mô tả");
            System.out.println("---------------------------------------------------------------------------------");
            for (Clock clock : clocks) {
                if (clock.getQuantity() == search) {
                    count++;
                    System.out.printf("%-10s %-30s %-18s %-15s %-15s\n",
                            clock.getId(),
                            clock.getName(),
                            clockView.numberFormat.format(clock.getPrice()),
                            clock.getQuantity(),
                            clock.getDescription());
                }
            }
            System.out.println("---------------------------------------------------------------------------------");
            showReturnSearch(count);
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("Chưa hợp lệ... Mời nhập lại");
            searchByQuantity();
        }
    }

    public static void searchById() {
        List<Clock> clockList = clockService.getClocks();
        int count = 0;
        System.out.println("Nhập ID sản phẩm tìm kiếm: ");
        try {
            int search = Integer.parseInt(scanner.nextLine());
            System.out.println("Kết quả :  '" + search + "' là : ");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("| %-10s %-30s %-18s %-15s %-15s \n", "Id", "Tên Clock", "Giá", "Số lượng", "Mô tả");
            System.out.println("---------------------------------------------------------------------------------");
            for (Clock clock : clockList) {
                if (clock.getId() == search) {
                    count++;
                    System.out.printf("%-10s %-30s %-18s %-15s %-15s\n",
                            clock.getId(),
                            clock.getName(),
                            clockView.numberFormat.format(clock.getPrice()),
                            clock.getQuantity(),
                            clock.getDescription());
                }
            }
            System.out.println("---------------------------------------------------------------------------------");
            showReturnSearch(count);

        } catch (Exception e) {
            System.out.println("Chưa hợp lệ... Mời nhập lại");
        }
    }

    public static void searchByName() {
        List<Clock> clocks = clockService.getClocks();
        int count = 0;
        System.out.print("Nhập tên sản phẩm cần tìm kiếm : ");
        String search = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm của từ khóa '" + search + "' là : ");
        search = search.toLowerCase();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("| %-15s %-20s %-15s %-20s %-20s \n", "ID", "Tên Clock", "Số lượng", "Giá", "Mô tả");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Clock clock : clocks) {
            if (clock.getName().toLowerCase().contains(search)) {
                count++;
                System.out.printf("%-15s %-20s %-15s %-20s %-20s \n",
                        clock.getId(),
                        clock.getName(),
                        clock.getQuantity(),
                        clockView.numberFormat.format(clock.getPrice()),
                        clock.getDescription());
            }
        }
        System.out.println("-----------------------------------------------------------------------------------");
        showReturnSearch(count);
    }

    public static void searchByPrice() {
        List<Clock> clocks = clockService.getClocks();
        int count = 0;
        System.out.println();
        System.out.print("Nhập giá sản phẩm cần tìm kiếm : ");
        double search = Double.parseDouble(scanner.nextLine());
        System.out.println("Kết quả tìm kiếm của từ khóa '" + search + "' là : ");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-15s %-20s %-15s %-20s %-20s \n", "ID", "Tên Clock", "Số lượng", "Giá", "Mô tả");
        System.out.println("---------------------------------------------------------------------------------");
        for (Clock clock : clocks) {
            if (clock.getPrice() == search) {
                count++;
                System.out.printf("%-15s %-20s %-15s %-20s %-20s \n",
                        clock.getId(),
                        clock.getName(),
                        clock.getQuantity(),
                        clockView.numberFormat.format(clock.getPrice()),
                        clock.getDescription());
            }
        }
        System.out.println("---------------------------------------------------------------------------------");
        showReturnSearch(count);
    }

    public static void showReturnSearch(int count) {
        System.out.println("Có '" + count + "' sản phẩm được tìm thấy !");
        char press = ' ';
        boolean isChoice;
        System.out.println();
        do {
            System.out.print("Nhấn 'Q' để về menu tìm kiếm ! \n");
            System.out.print("☛ ");
            try {
                press = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                press = ' ';
            }
            switch (press) {
                case 'q':
                case 'Q': {
                    SearchMenu.searchMenu();
                    isChoice = false;
                    break;
                }
                default:
                    isChoice = true;
            }
        } while (isChoice);
    }

}
