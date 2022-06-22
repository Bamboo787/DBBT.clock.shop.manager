package vn.td.clock.shop.views;

import vn.td.clock.shop.model.Clock;
import vn.td.clock.shop.model.Order;
import vn.td.clock.shop.model.OrderItem;
import vn.td.clock.shop.services.*;
import vn.td.clock.shop.utils.InstantUtils;
import vn.td.clock.shop.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class OrderView {
    private IClockService clockService;
    private IOrderService orderService;
    private IOderItemService oderItemService;
    private final Scanner scanner = new Scanner(System.in);
    ClockView clockView = new ClockView();
    public OrderView() {
        clockService = new ClockService();
        orderService = new OrderService();
        oderItemService = new OrderItemService();
    }

    public OrderItem addOrderItems(long orderId) {
        oderItemService.getOrderItems();
        ClockView clockView = new ClockView();
        clockView.show();

        long id = System.currentTimeMillis() / 1000;
        System.out.println("Nhập ID: ");
        System.out.print("☛ ");
        int clockId = Integer.parseInt(scanner.nextLine());
        while (!clockService.checkDuplicateId(clockId)) {
            System.out.println("ID không tồn tại");
            System.out.println("Nhập ID: ");
            System.out.print("☛ ");
            clockId = Integer.parseInt(scanner.nextLine());
        }

        Clock clock = clockService.getClockById(clockId);
        double price = clock.getPrice();
        int oldQuantity = clock.getQuantity();
        System.out.println("Nhập số lượng");
        System.out.print("☛ ");
        int quantity = Integer.parseInt(scanner.nextLine());
        while (!checkQualityClock(clock, quantity)) {
            System.out.println("Vượt quá số lượng! Vui lòng nhập lại");
            System.out.println("Nhập số lượng");
            Integer.parseInt(scanner.nextLine());
            quantity = Integer.parseInt(scanner.nextLine());
        }
        String clockName = clock.getName();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        clock.setQuantity(currentQuantity);
        clockService.update();
        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, clockId, clockName, total);
        return orderItem;
    }

    public boolean checkQualityClock(Clock clock, int quantity) {
        if (quantity <= clock.getQuantity())
            return true;
        else
            return false;
    }

    public void addOder() {
        try {
            orderService.getOrders();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ và tên: ");
            System.out.print("☛ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên " + name + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập tên: ");
                System.out.print("☛ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số điên thoại");
            System.out.print("☛ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại");
                System.out.print("☛ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ");
            System.out.print("☛ ");
            String address = scanner.nextLine();
            while (!ValidateUtils.isAddressValid(address)) {
                System.out.println("Địa chỉ: " + address + " chưa hợp lệ. Mời nhập lại (Địa chỉ bắt đầu bằng số)");
                System.out.println("Nhập địa chỉ");
                System.out.print("☛ ");
                address = scanner.nextLine();
            }

            OrderItem orderItem = addOrderItems(orderId);
            Order order = new Order(orderId, name, phone, address);
            oderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Tạo đơn hàng thành công!");
            do {
                System.out.println("--------------------------------------------");
                System.out.println("|                                          |");
                System.out.println("|     1. Nhấn 'y' để tạo tiếp đơn hàng     |");
                System.out.println("|     2. Nhấn 'q' để trở lại               |");
                System.out.println("|     3. nhấp 'p' để in hoá đơn            |");
                System.out.println("|     4. Nhấn 't' để thoát                 |");
                System.out.println("|                                          |");
                System.out.println("--------------------------------------------");
                System.out.print("☛ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addOder();
                        break;
                    case "q":
                        ManagerOrderView.run();
                        break;
                    case "p":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Nhập sai. vui lòng nhập lại!");
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("----------------------------------------------------------HOÁ ĐƠN----------------------------------------------------------------------------------");
            System.out.printf("|%-15s %-20s %-15s %-25s %-15s %-15s %-20s %-20s \n|", "Id", "Tên khách hàng", "Số Điện Thoại", "Địa chỉ", "Tên Clock", "Số lượng", "Giá", "Ngày tạo");
            System.out.printf("%-15s %-20s %-15s %-25s %-15s %-15s %-20s %-20s \n|",
                    order.getId(),
                    order.getName(),
                    order.getPhone(),
                    order.getAddress(),
                    orderItem.getClockName(),
                    orderItem.getQuantity(),
                    clockView.numberFormat.format(orderItem.getPrice()),
                    InstantUtils.instantToString(order.getCreateDate())
                    );
            System.out.println(" ---------------------------------------------------------------------------------------------------- Tổng tiền:" + clockView.numberFormat.format(orderItem.getTotal()));

            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.println("Vui lòng chọn");
                System.out.print("☛ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        ManagerOrderView.run();
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhấn không đúng! Vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.getOrders();
        List<OrderItem> orderItems = oderItemService.getOrderItems();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println("----------------------------------------------------------LIST ORDER-----------------------------------------------------");
            System.out.println("|                                                                                                                        |");
            System.out.printf("|%-15s %-20s %-15s %-23s %-10s %-15s %-20s \n|", "  Id", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên Clock", "Số lượng", "   Giá", "|");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15s %-20s %-15s %-23s %-10s %-15s %-20s \n|",
                        order.getId(),
                        order.getName(),
                        order.getPhone(),
                        order.getAddress(),
                        newOrderItem.getClockName(),
                        newOrderItem.getQuantity(),
                        clockView.numberFormat.format(newOrderItem.getPrice()),
//                        decimalFormat.format(newOrderItem.getTotal()),
                        "|");
            }
            System.out.println("                                                                                                                           |");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print("☛ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        ManagerOrderView.run();
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
