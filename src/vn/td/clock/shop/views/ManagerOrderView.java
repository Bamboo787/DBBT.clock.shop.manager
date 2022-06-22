package vn.td.clock.shop.views;

import java.util.Scanner;

public class ManagerOrderView {
    public static void run(){
        Menu.orderMenu();
        Scanner scanner = new Scanner(System.in);
        OrderView orderView = new OrderView();
        System.out.println("\nChọn chức năng");
        System.out.print("☛ ");
        int choices = scanner.nextInt();
        switch (choices){
            case 1:
                orderView.addOder();
                break;
            case 2:
                orderView.showAllOrder();
                break;
            case 0:
                Menu.chon();
                break;
            default:
                System.out.println("Chọn sai! vui lòng nhập lại");
                run();

        }
    }
}

