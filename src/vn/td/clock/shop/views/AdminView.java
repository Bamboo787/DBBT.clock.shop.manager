package vn.td.clock.shop.views;

import vn.td.clock.shop.services.UserService;
import vn.td.clock.shop.utils.AppUtils;

import java.util.Scanner;

public class AdminView {
    UserService userService = new UserService();
    private Scanner scanner = new Scanner(System.in);

    public void loginAdmin() {
        System.out.println("====================================================================");
        System.out.println("║                       |ĐĂNG NHẬP HỆ THỐNG|                       ║");
        System.out.println("====================================================================");
        System.out.print("Username: ");
        String username = AppUtils.retryString("Username: ");
        System.out.print("Password: ");
        String password = AppUtils.retryString("Password: ");
        if (userService.loginAdmin(username, password) == null) {
            System.out.println("Tài khoản đăng nhập không hợp lệ!");
            chons();
        } else {
            System.out.println("ĐĂNG NHẬP THÀNH CÔNG! \uD83C\uDF8A \n");
            System.out.println("✿ ✿ ✿ ✿ ✿ ✿ WELCOME TO WATCH MANAGEMENT STORE ✿ ✿ ✿ ✿ ✿ ✿ \n");
        }
    }

    public void chons() {
        System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ TÙY CHỌN ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println("\t✦                                                                   ✦");
        System.out.println("\t✦                   1. Nhấn 'y' để đăng nhập lại                    ✦");
        System.out.println("\t✦                   2. Nhấn 'n' để thoát chương trình               ✦");
        System.out.println("\t✦                                                                   ✦");
        System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.print("☛ ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "y":
                loginAdmin();
                break;
            case "n":
                Menu.exit();
                System.exit(0);
                break;
            default:
                System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại ");
                chons();
        }
    }

}
