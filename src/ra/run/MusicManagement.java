package ra.run;

import ra.controller.SingerController;
import ra.controller.SongController;
import ra.controller.SearchController;

import java.util.Scanner;

public class MusicManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SingerController singerController = new SingerController();
        SongController songController = new SongController();
        SearchController searchController = new SearchController();

        while (true) {
            System.out.println("************************MUSIC-MANAGEMENT*************************");
            System.out.println("1. Quản lý ca sĩ ");
            System.out.println("2. Quản lý bài hát ");
            System.out.println("3. Tìm kiếm bài hát ");
            System.out.println("4. Thoát ");
            System.out.print("Nhập lựa chọn của bạn: ");

            char choice;
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine().charAt(0);
            } else {
                choice = '4';
            }

            switch (choice) {
                case '1':
                    singerController.start();
                    break;
                case '2':
                    songController.start();
                    break;
                case '3':
                    searchController.start();
                    break;
                case '4':
                    System.out.println("Ứng dụng kết thúc.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }

            System.out.println();
        }
    }
}
