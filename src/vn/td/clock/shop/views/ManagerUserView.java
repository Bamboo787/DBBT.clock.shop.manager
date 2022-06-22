package vn.td.clock.shop.views;

import java.util.Scanner;

public class ManagerUserView {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        Menu.menuUser();
        try {
            System.out.println("\nChọn chức năng ");
            System.out.print("☛ ");
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    userView.addUser();
                    break;
                case 2:
                    userView.updateUser();
                    break;
                case 3:
                    userView.showUsers();
                    break;
                case 0:
                    Menu.chon();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    run();
            }
        } catch (Exception e) {
            System.out.println("Nhập sai! vui lòng nhập lại");
        }
    }
}
