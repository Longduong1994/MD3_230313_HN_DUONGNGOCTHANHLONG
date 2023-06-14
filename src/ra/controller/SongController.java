package ra.controller;

import ra.service.SongService;
import ra.model.Song;
import ra.model.Singer;

import java.util.Date;
import java.util.Scanner;

public class SongController {
    private SongService songService;
    private SingerController singerController;
    private Scanner scanner;
    private boolean returnToMainMenu;
    public SongController() {
        songService = new SongService();
        singerController = new SingerController();
        scanner = new Scanner(System.in);
        returnToMainMenu = false;
    }

    public void start() {
        char choice;

        do {
            returnToMainMenu = false;

            System.out.println("----- Quản lý Bài hát -----");
            System.out.println("1. Nhập thông tin bài hát");
            System.out.println("2. Hiển thị danh sách bài hát");
            System.out.println("3. Cập nhật thông tin bài hát");
            System.out.println("4. Xóa bài hát");
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
                    updateSong();
                    break;
                case '4':
                    deleteSong();
                    break;
                case '0':
                    System.out.println("Ứng dụng kết thúc.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }

            System.out.println();
        } while (!returnToMainMenu);

        scanner.close();
    }

    private void inputData() {
        System.out.println("----- Nhập thông tin bài hát -----");

        Song song = new Song();
        song.setSongId(generateSongId());

        System.out.print("Tên bài hát: ");
        song.setSongName(scanner.nextLine());

        System.out.print("Mô tả: ");
        song.setDescriptions(scanner.nextLine());

        System.out.println("Danh sách ca sĩ:");
        singerController.displayData();
        System.out.print("Nhập ID của ca sĩ: ");
        int singerId = Integer.parseInt(scanner.nextLine());
        Singer selectedSinger = singerController.findSingerById(singerId);

        if (selectedSinger == null) {
            System.out.println("Không tìm thấy ca sĩ có ID " + singerId);
            return;
        }

        song.setSinger(selectedSinger);

        System.out.print("Tên nhạc sĩ: ");
        song.setSongWriter(scanner.nextLine());

        song.setCreatedDate(new Date());

        System.out.print("Trạng thái bài hát (true/false): ");
        song.setSongStatus(Boolean.parseBoolean(scanner.nextLine()));

        songService.addSong(song);

        System.out.println("Thêm bài hát thành công.");
    }

    private void displayData() {
        System.out.println("----- Danh sách bài hát -----");
        for (Song song : songService.getAllSongs()) {
            if (song != null) {
                System.out.println("ID: " + song.getSongId());
                System.out.println("Tên bài hát: " + song.getSongName());
                System.out.println("Mô tả: " + song.getDescriptions());
                System.out.println("Ca sĩ: " + song.getSinger().getSingerName());
                System.out.println("Tên nhạc sĩ: " + song.getSongWriter());
                System.out.println("Trạng thái bài hát: " + song.isSongStatus());
                System.out.println("Ngày tạo: " + song.getCreatedDate());
                System.out.println("--------------------------");
            }
        }
    }

    private void updateSong() {
        System.out.print("Nhập ID của bài hát cần cập nhật: ");
        int songId = Integer.parseInt(scanner.nextLine());
        Song songToUpdate = songService.findSongById(songId);

        if (songToUpdate == null) {
            System.out.println("Không tìm thấy bài hát có ID " + songId);
            return;
        }

        System.out.println("----- Cập nhật thông tin bài hát -----");

        System.out.print("Tên bài hát: ");
        songToUpdate.setSongName(scanner.nextLine());

        System.out.print("Mô tả: ");
        songToUpdate.setDescriptions(scanner.nextLine());

        System.out.println("Danh sách ca sĩ:");
        singerController.displayData();
        System.out.print("Nhập ID của ca sĩ: ");
        int singerId = Integer.parseInt(scanner.nextLine());
        Singer selectedSinger = singerController.findSingerById(singerId);

        if (selectedSinger == null) {
            System.out.println("Không tìm thấy ca sĩ có ID " + singerId);
            return;
        }

        songToUpdate.setSinger(selectedSinger);

        System.out.print("Tên nhạc sĩ: ");
        songToUpdate.setSongWriter(scanner.nextLine());

        System.out.print("Trạng thái bài hát (true/false): ");
        songToUpdate.setSongStatus(Boolean.parseBoolean(scanner.nextLine()));

        System.out.println("Cập nhật bài hát thành công.");
    }

    private void deleteSong() {
        System.out.print("Nhập ID của bài hát cần xóa: ");
        int songId = Integer.parseInt(scanner.nextLine());

        if (songService.deleteSong(songId)) {
            System.out.println("Xóa bài hát thành công.");
        } else {
            System.out.println("Không tìm thấy bài hát có ID " + songId);
        }
    }

    private int generateSongId() {
        Song[] songs = songService.getAllSongs().toArray(new Song[0]);
        int maxId = 0;

        for (Song song : songs) {
            if (song != null && song.getSongId() > maxId) {
                maxId = song.getSongId();
            }
        }

        return maxId + 1;
    }
}
