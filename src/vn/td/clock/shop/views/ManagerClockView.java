package vn.td.clock.shop.views;

import vn.td.clock.shop.views.Sort.MenuSort;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerClockView {
    public static void run() {
        int number;
        do {
            Scanner scanner = new Scanner(System.in);
            ClockView clockView = new ClockView();
            Menu.menuClock();
            try {
                System.out.println("\nChọn chức năng: ");
                System.out.print("☛ ");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        clockView.add();
                        break;
                    case 2:
                        clockView.update();
                        break;
                    case 3:
                        clockView.remove();
                        break;
                    case 4:
                        clockView.show();
                        MenuSort.option();
                        break;
                    case 5:
                        SearchMenu.searchMenu();
                        break;
                    case 6:
                        Menu.chon();
                        break;
                    case 0:
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        run();
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
