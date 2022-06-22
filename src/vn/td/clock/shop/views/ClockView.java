package vn.td.clock.shop.views;

import vn.td.clock.shop.model.Clock;
import vn.td.clock.shop.services.ClockService;
import vn.td.clock.shop.utils.InstantUtils;
import vn.td.clock.shop.utils.ValidateUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ClockView {

    private final ClockService clockService = new ClockService();
    private final Scanner scanner = new Scanner(System.in);
    Locale locale = new Locale("vi","VN");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

//    public ClockView() {
//        clockService = new ClockService();
//        scanner = new Scanner(System.in);
//    }

    public void add() {
        clockService.getClocks();
        System.out.println("Nhập ID sản phẩm: ");
        System.out.print("☛ ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (clockService.checkDuplicateId(id)) {
            System.out.println("ID đã tồn tại! Vui lòng nhập lại");
            add();
        } else {
            System.out.println("Nhập tên sản phẩm: ");
            System.out.print("☛ ");
            String nameProduct = scanner.nextLine();
            while (!ValidateUtils.isNameProductValid(nameProduct)) {
                System.out.println("Tên sản phẩm phải bắt đầu bằng Dong ho (vd: Dong ho CASIO)");
                nameProduct = scanner.nextLine();
            }
            System.out.println("Nhập giá sản phẩm");
            System.out.print("☛ ");
            double price;
            do {
                price = Double.parseDouble(scanner.nextLine());
                if (!(price > 1000000 && price < 1000000000)) {
                    System.out.print("Lỗi Nhập Giá!!! Mời nhập lại \n Giá tiền phải nằm trong khoảng từ 1tr đến 1 tỷ\n => \t");
                    price = Double.parseDouble(scanner.nextLine());
                }
            } while (!(price > 1000000 && price < 1000000000));

            scanner.nextLine();
            System.out.println("Nhập số lượng: ");
            System.out.print("☛ ");
            int quantity;
            do {
                quantity = scanner.nextInt();
                if (!(quantity > 0 && quantity <= 100)) {
                    System.out.println("Số lượng không được quá 100");
                    System.out.print("☛ ");
                }
            } while (!(quantity > 0 && quantity <= 100));
            System.out.println("Mô tả sản phẩm: ");
            System.out.print("☛ ");
            scanner.nextLine();
            String description = scanner.nextLine();
            Clock clock = new Clock(id, nameProduct, price, quantity, description);
            clockService.add(clock);
            System.out.println("Thêm thành công\n");
        }
        boolean is = true;
        do {
            System.out.println("Nhấn 'y' để thêm mới \t|\t 'q' để quay lại \t|\t 't' để thoát");
            System.out.print("☛ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    add();
                    break;
                case "q":
                    ManagerClockView.run();
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
    }

    public void update() {
        show();
        clockService.getClocks();
        System.out.println("Nhập Id sản phẩm cần sửa: ");
        System.out.print("☛ ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Clock clock = clockService.getClockById(id);
        if (clockService.checkDuplicateId(id)) {
            System.out.println("=====================================");
            System.out.println("|       Nhấn '1' để sửa số lượng    |");
            System.out.println("|       Nhấn '2' để sửa giá         |");
            System.out.println("=====================================");
            System.out.println("Chọn chức năng: ");
            System.out.print("☛ ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Nhập số lượng bạn muốn sửa: ");
                    System.out.print("☛ ");
                    int quantity = scanner.nextInt();
                    clock.setQuantity(quantity);
                    clockService.update();
                    System.out.println("Cập nhật thành công!!!");
                    break;
                case 2:
                    System.out.println("Nhập giá bạn muốn sửa: ");
                    System.out.print("☛ ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    clock.setPrice(price);
                    clockService.update();
                    System.out.println("Cập nhật thành công!!!");
                    break;
                case 3:
                    ManagerClockView.run();
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    update();
            }

            boolean is = true;
            do {
                System.out.println("Nhấn 'y' để sửa tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
                System.out.print("☛ ");
                String choi = scanner.nextLine();
                switch (choi) {
                    case "y":
                        update();
                        break;
                    case "q":
                        ManagerClockView.run();
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } else {
            System.out.println("Không tìm thấy ID! Vui lòng nhập lại");
            update();
        }
    }

    public void show() {
        List<Clock> clocks = clockService.getClocks();
        System.out.println("-------------------------------------------------------DANH SÁCH SẢN PHẨM----------------------------------------------------------");
        System.out.printf("%-15s %-20s %-15s %-10s %-20s %-20s %-20s", "ID", "Tên sản phẩm", "Giá", "Số lượng", "Mô tả", "Ngày tạo", "Ngày cập nhật");
        System.out.println(" ");
        for (Clock clock : clocks) {
            System.out.printf("%-15s %-20s %-15s %-10s %-20s %-20s %-20s \n",
                    clock.getId(),
                    clock.getName(),
                    numberFormat.format(clock.getPrice()),
                    clock.getQuantity(),
                    clock.getDescription(),
                    InstantUtils.instantToString(clock.getCreateDate()),
                    clock.getCreateUpdate() == null ? "" : InstantUtils.instantToString(clock.getCreateUpdate())
                    );
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void showClock() {
        show();
        boolean is = true;
        do {
            System.out.println("── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ──");
            System.out.println("|  Nhấn 'y' để trở lại \t|\t 'n' để thoát chương trình |");
            System.out.println("── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ──");
            System.out.print("☛ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    ManagerClockView.run();
                    break;
                case "n":
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhấn không đúng! vui lòng chọn lại");
                    is = false;
            }
        } while (!is);
    }

    public void remove() {
        show();
        clockService.getClocks();
        System.out.println("Nhập ID bạn cần xoá: ");
        System.out.print("☛ ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Clock clock = clockService.getClockById(id);
        if (clock == null) {
            System.out.println("Không tìm thấy ID bạn cần xóa!");
        } else {
            boolean check = true;
            System.out.println("*******************************************************");
            System.out.println("*               BẠN CÓ CHẮC CHẮN MUỐN XÓA             *");
            System.out.println("*******************************************************");
            System.out.println("*                                                     *");
            System.out.println("*              1. Nhấn '1' để xác nhận xóa            *");
            System.out.println("*              2. Nhấn '2' để quay lại                *");
            System.out.println("*                                                     *");
            System.out.println("*******************************************************");
            System.out.print("☛ ");
            int choi = scanner.nextInt();
            scanner.nextLine();
            switch (choi) {
                case 1:
                    clockService.remove(clock);
                    System.out.println("Đã xoá thành công! \uD83C\uDF8A");
                    do {
                        System.out.println("── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ──");
                        System.out.println("|  Nhấn 'y' để trở lại \t|\t 'n' để thoát chương trình |");
                        System.out.println("── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ──");
                        System.out.print("☛ ");
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "y":
                                ManagerClockView.run();
                                break;
                            case "n":
                                Menu.exit();
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Nhấn không đúng! vui lòng chọn lại");
                                check = false;
                        }
                    } while (!check);
                    break;
                case 2:
                    ManagerClockView.run();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
            }
        }
    }
}
