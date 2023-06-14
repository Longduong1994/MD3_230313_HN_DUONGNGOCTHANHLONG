package ra.controller;

import java.util.Scanner;
import ra.service.SingerService;
import ra.model.Singer;

public class SingerController {
    private SingerService singerService;
    private Scanner scanner;

    public SingerController() {
        singerService = new SingerService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        char choice;
        boolean returnToMainMenu = false;

        do {
            System.out.println("----- Quản lý Ca sĩ -----");
            System.out.println("1. Nhập thông tin ca sĩ");
            System.out.println("2. Hiển thị danh sách ca sĩ");
            System.out.println("3. Cập nhật thông tin ca sĩ");
            System.out.println("4. Xóa ca sĩ");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case '1':
                    inputData();
                    break;
                case '2':
                    displayData();
                    break;
                case '3':
                    updateSinger();
                    break;
                case '4':
                    deleteSinger();
                    break;
                case '0':
                    System.out.println("Ứng dụng kết thúc.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }

            System.out.println();
            System.out.print("Nhấn Enter để quay lại Menu chính...");
            scanner.nextLine();

        } while (!returnToMainMenu);

        // Đóng Scanner sau khi hoàn thành các thao tác
        scanner.close();
    }

    private void inputData() {
        Singer.inputData();
    }

    void displayData() {
        Singer.displayData();
    }

    private void updateSinger() {
        System.out.print("Nhập mã ID của ca sĩ cần cập nhật: ");
        int singerId = Integer.parseInt(scanner.nextLine());
        Singer updatedSinger = createSingerFromUserInput();
        SingerService.updateSingerById(singerId, updatedSinger);
    }

    private Singer createSingerFromUserInput() {
        Singer singer = new Singer();

        System.out.print("Tên ca sĩ: ");
        singer.setSingerName(scanner.nextLine());

        System.out.print("Tuổi: ");
        singer.setAge(Integer.parseInt(scanner.nextLine()));

        System.out.print("Quốc tịch: ");
        singer.setNationality(scanner.nextLine());

        System.out.print("Giới tính (Nam/Nữ): ");
        String genderInput = scanner.nextLine();
        singer.setGender(genderInput.equalsIgnoreCase("Nam"));

        System.out.print("Thể loại: ");
        singer.setGenre(scanner.nextLine());

        return singer;
    }

    private void deleteSinger() {
        System.out.print("Nhập mã ID của ca sĩ cần xóa: ");
        int singerId = Integer.parseInt(scanner.nextLine());
        SingerService.deleteSingerById(singerId);
    }

    public Singer findSingerById(int singerId) {
        return singerService.findSingerById(singerId);
    }
}
