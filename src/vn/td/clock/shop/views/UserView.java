package vn.td.clock.shop.views;

import vn.td.clock.shop.model.Role;
import vn.td.clock.shop.model.User;
import vn.td.clock.shop.services.IUserService;
import vn.td.clock.shop.services.UserService;
import vn.td.clock.shop.utils.InstantUtils;
import vn.td.clock.shop.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserView() {
        userService = new UserService();
    }

    public void addUser() {
        System.out.println("Nhập ID");
        System.out.print("☛ ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (userService.exists(id)) {
            System.out.println("ID này đã tồn tại. Vui lòng nhập ID khác!");
            addUser();
        } else {
            System.out.println("Nhập Username");
            System.out.print("☛ ");
            String userName = scanner.nextLine();
            while (userService.checkDuplicateUserName(userName)) {
                System.out.println("Username này đã tồn tại. Vui lòng nhập lại");
                System.out.print("☛ ");
                userName = scanner.nextLine();
            }
            System.out.println("Nhập mật khẩu (Mật khẩu phải > 8 kí tự" +
                    ")");
            System.out.print("☛ ");
            String password = scanner.nextLine();
            while (!ValidateUtils.isPasswordValid(password)) {
                System.out.println("Mật khẩu yếu! Vui lòng nhập lại ");
                System.out.print("☛ ");
                password = scanner.nextLine();
            }
            System.out.println("Nhập họ và tên");
            System.out.print("☛ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên: " + name + " không đúng định dạng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập họ và tên");
                System.out.print("☛ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số điện thoại");
            System.out.print("☛ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số: " + phone + " của bạn không đúng định dạng. Vui lòng nhập lại!"); //  Số điện thoại bao gồm 10 số và bắt đầu là số 0
                System.out.println("Nhập số điện thoại");
                System.out.print("☛ ");
                phone = scanner.nextLine();
            }
            while (userService.checkDuplicatePhone(phone)) {
                System.out.println("Số điện thoại này đã tồn tại! vui lòng kiểm tra lại!");
                System.out.println("Nhập số điện thoại");
                System.out.print("☛ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập Email");
            System.out.print("☛ ");
            String email = scanner.nextLine();
            while (!ValidateUtils.isEmailValid(email)) {
                System.out.println("Email: " + email + " của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
                System.out.println("Nhập Email");
                System.out.print("☛ ");
                email = scanner.nextLine();
            }
            while (userService.checkDuplicateEmail(email)) {
                System.out.println("Email: " + email + " của bạn đã tồn tại! vui lòng kiểm tra lại");
                System.out.println("Nhập Email");
                System.out.print("☛ ");
                email = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ");
            System.out.print("☛ ");
            String address = scanner.nextLine();
            while (!ValidateUtils.isAddressValid(address)) {
                System.out.println("Địa chỉ: " + address + " chưa hợp lệ. Vui lòng nhập lại!");
                System.out.println("Nhập địa chỉ");
                System.out.print("☛ ");
                address = scanner.nextLine();
            }

            User user = new User(id, userName, password, name, phone, email, address, Role.USER);
            setRole(user);
            userService.add(user);
            System.out.println("Đã thêm thành công!\uD83C\uDF8A");
        }
        boolean check = true;
        do {
            System.out.println("==========================================================================");
            System.out.println("Nhấn 'y' để thêm tiếp người dùng \t|\t 'q' để quay lại \t|\t 't' để thoát");
            System.out.println("==========================================================================");
            System.out.print("☛ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    addUser();
                    break;
                case "q":
                    ManagerUserView.run();
                    break;
                case "t":
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhấn không đúng! Vui lòng chọn lại");
                    check = false;
            }
        } while (!check);
    }

    public void setRole(User user) {
        System.out.println("============= SET ROLE =============");
        System.out.println("|       Nhấn '1' USER              |");
        System.out.println("|       Nhấn '2' ADMIN             |");
        System.out.println("====================================");
        System.out.println("Chọn Role: ");
        System.out.print("☛ ");
        int chido = scanner.nextInt();
        scanner.nextLine();
        switch (chido) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("Nhập không đúng! Vui lòng nhập lại");
                setRole(user);
        }
    }

    public void show() {
        System.out.println("-------------------------------------------------------DANH SÁCH NGƯỜI DÙNG---------------------------------------------------------------------");
        System.out.printf("%-8s %-20s %-15s %-22s %-30s %-12s %-20s %-20s \n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng", "Ngày tạo", "Ngày cập nhật");
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.printf("%-8s %-20s %-15s %-22s %-30s %-12s %-20s %-20s \n",
                    user.getId(),
                    user.getName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getCreateDate()),
                    user.getCreateUpdate() == null ? "" : InstantUtils.instantToString(user.getCreateUpdate()));
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" ");
    }

    public void showUsers() {
        try {
            show();
            boolean is = true;
            do {
                System.out.println("======================================================");
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.println("======================================================");
                System.out.print("☛ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        ManagerUserView.run();
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

    public void updateUser() {
        try {
            show();
            userService.getUsers();
            System.out.println("Nhập ID bạn muốn sửa");
            System.out.print("☛ ");
            int id = scanner.nextInt();
            scanner.nextLine();
            // User user = userService.getUserById(id);
            if (userService.exists(id)) {
                System.out.println("┌--------------------SỬA--------------------┐");
                System.out.println("|        1. Đổi tên                         |");
                System.out.println("|        2. Sửa số điện thoại               |");
                System.out.println("|        3. Sửa địa chỉ                     |");
                System.out.println("|        4. Quay lại                        |");
                System.out.println("└-------------------------------------------┘");
                System.out.println("Chọn chức năng");
                System.out.print("☛ ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                User user = new User();
                user.setId(id);
                switch (choice) {
                    case 1:
                        System.out.println("Nhập tên mà bạn muốn sửa đổi");
                        System.out.print("☛ ");
                        String name = scanner.nextLine();
                        user.setName(name);
                        userService.update(user);
                        System.out.println("Bạn đã đổi tên thành công!\uD83C\uDF89");
                        break;
                    case 2:
                        System.out.println("Nhập số điện thoại mà bạn muốn đổi");
                        System.out.print("☛ ");
                        String phone1 = scanner.nextLine();
                        while (!ValidateUtils.isPhoneValid(phone1)) {
                            System.out.println("Số " + phone1 + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                            System.out.println("Nhập số điện thoại");
                            System.out.print("☛ ");
                            phone1 = scanner.nextLine();
                        }
                        while (userService.checkDuplicatePhone(phone1)) {
                            System.out.println("Số này đã tồn tại! Mời bạn nhập lại");
                            System.out.print("☛ ");
                            phone1 = scanner.nextLine();
                        }
                        user.setPhone(phone1);
                        userService.update(user);
                        System.out.println("Bạn đã đổi số điện thoại thành công\uD83C\uDF89");
                        break;
                    case 3:
                        System.out.println("Nhập địa chỉ mà bạn muốn đổi");
                        System.out.print("☛ ");
                        String address = scanner.nextLine();
                        user.setAddress(address);
                        userService.update(user);
                        System.out.println("Bạn đã đổi thành công\uD83C\uDF89");
                        break;
                    case 4:
                        ManagerUserView.run();
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        updateUser();
                }

                boolean is = true;
                do {
                    System.out.println("==========================================================================");
                    System.out.println("Nhấn 'y' để sửa tiếp \t|\t 'q' để quay lại\t|\t 't' để thoát chương trình");
                    System.out.println("==========================================================================");
                    System.out.print("☛ ");
                    String choi = scanner.nextLine();
                    switch (choi) {
                        case "y":
                            updateUser();
                            break;
                        case "q":
                            ManagerUserView.run();
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
                updateUser();
            }
        } catch (Exception e) {
            System.out.println("Nhập sai! vui lòng nhập lại");
        }
    }
}


