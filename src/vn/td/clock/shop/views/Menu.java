package vn.td.clock.shop.views;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void menuClock() {
        System.out.println();
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                 QUẢN LÝ SẢN PHẨM                   ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧               1. Thêm sản phẩm                     ♧");
        System.out.println("\t♧               2. Sửa thông tin sản phẩm            ♧");
        System.out.println("\t♧               3. Xóa sản phẩm                      ♧");
        System.out.println("\t♧               4. Hiển thị và sắp xếp sản phẩm      ♧");
        System.out.println("\t♧               5. Tìm kiếm sản phẩm                 ♧");
        System.out.println("\t♧               6. Quay về màn hình chính            ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧               0. Thoát khỏi chương trình           ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
    }

    public static void exit() {
        System.out.println("\tTạm biệt. Hẹn gặp lại!");
        System.exit(0);
    }

    public static void user() {
        AdminView adminView = new AdminView();
        adminView.loginAdmin();
//        UserView userView = new UserView();
//        userView.loginAdmin();
        chon();
    }

    public static void chon() {
        do {
            mainMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nChọn chức năng ");
                System.out.print("☛ ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        ManagerUserView.run();
                        break;
                    case 2:
                        ManagerClockView.run();
                        break;
                    case 3:
                        ManagerOrderView.run();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        chon();
                }

            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void menuUser() {
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                 QUẢN LÝ NGƯỜI DÙNG                 ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧               1. Thêm người dùng                   ♧");
        System.out.println("\t♧               2. Sửa thông tin người dùng          ♧");
        System.out.println("\t♧               3. Hiện danh sách người dùng         ♧");
        System.out.println("\t♧                                      0. Quay đầu   ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
    }

    public static void mainMenu() {
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                    MAIN MENU                       ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧               1. Quản lý người dùng                ♧");
        System.out.println("\t♧               2. Quản lý sản phẩm                  ♧");
        System.out.println("\t♧               3. Quản lý đơn hàng                  ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
    }

    public static void orderMenu() {
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║                   QUẢN LÝ ĐƠN HÀNG                  ║");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        System.out.println("║                                                     ║");
        System.out.println("║                  1. Tạo danh sách                   ║");
        System.out.println("║                  2. Xem danh sách                   ║");
        System.out.println("║                                      0. Quay đầu    ║");
        System.out.println("║                                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
    }
}