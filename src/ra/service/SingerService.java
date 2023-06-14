package ra.service;

import ra.model.Singer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SingerService {
    private static Singer[] listSingers = new Singer[100];
    private static int size = 0;

    public static void deleteSingerDataById(int id) {
    }

    public static ArrayList<Singer> getSingerList() {
        ArrayList<Singer> singerList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            singerList.add(listSingers[i]);
        }
        return singerList;
    }

    public Singer[] getAllSingers() {
        return listSingers;
    }

    public void addSinger(Singer singer) {
        if (size < listSingers.length) {
            listSingers[size] = singer;
            size++;
        } else {
            System.out.println("Danh sách ca sĩ đã đầy. Không thể thêm ca sĩ mới.");
        }
    }

    public static void updateSingerDataById(int id) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            if (listSingers[i].getSingerId() == id) {
                System.out.println("Nhập thông tin mới cho ca sĩ:");
                Singer updatedSinger = new Singer();
                updatedSinger.setSingerId(id);

                System.out.print("Tên ca sĩ: ");
                updatedSinger.setSingerName(scanner.nextLine());

                System.out.print("Tuổi: ");
                updatedSinger.setAge(Integer.parseInt(scanner.nextLine()));

                System.out.print("Quốc tịch: ");
                updatedSinger.setNationality(scanner.nextLine());

                System.out.print("Giới tính (Nam/Nữ): ");
                String genderInput = scanner.nextLine();
                updatedSinger.setGender(genderInput.equalsIgnoreCase("Nam"));

                System.out.print("Thể loại: ");
                updatedSinger.setGenre(scanner.nextLine());

                listSingers[i] = updatedSinger;
                System.out.println("Cập nhật thông tin ca sĩ thành công.");
                scanner.close();
                return;
            }
        }

        System.out.println("Không tìm thấy ca sĩ có mã ID tương ứng.");
        scanner.close();
    }


    public static void deleteSingerById(int id) {
        for (int i = 0; i < size; i++) {
            if (listSingers[i].getSingerId() == id) {
                // Dịch chuyển các phần tử phía sau về trước để xoá ca sĩ
                for (int j = i; j < size - 1; j++) {
                    listSingers[j] = listSingers[j + 1];
                }
                listSingers[size - 1] = null; // Gán null cho phần tử cuối cùng để xoá
                size--;
                System.out.println("Xoá ca sĩ thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy ca sĩ có mã ID tương ứng.");
    }

    public static void updateSingerById(int singerId, Singer updatedSinger) {
    }

    public Singer findSingerById(int singerId) {
        for (int i = 0; i < size; i++) {
            if (listSingers[i].getSingerId() == singerId) {
                return listSingers[i];
            }
        }
        return null;
    }

    public List<Singer> searchSingersByNameOrGenre(String keyword) {
        List<Singer> searchResults = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Singer singer = listSingers[i];
            if (singer.getSingerName().toLowerCase().contains(keyword.toLowerCase()) ||
                    singer.getGenre().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(singer);
            }
        }
        return searchResults;
    }
}
