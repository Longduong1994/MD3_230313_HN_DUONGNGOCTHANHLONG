package ra.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import ra.model.Singer;
import ra.model.Song;
import ra.service.SingerService;
import ra.service.SongService;

public class SearchController {
    private SingerService singerService;
    private SongService songService;
    private Scanner scanner;

    public SearchController() {
        singerService = new SingerService();
        songService = new SongService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        char choice;

        do {
            System.out.println("----- Tìm kiếm -----");
            System.out.println("1. Tìm kiếm bài hát theo tên ca sĩ hoặc thể loại");
            System.out.println("2. Tìm kiếm ca sĩ theo tên hoặc thể loại");
            System.out.println("3. Hiển thị danh sách bài hát theo thứ tự tên tăng dần");
            System.out.println("4. Hiển thị 10 bài hát mới nhất");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case '1':
                    searchSongsByArtistOrGenre();
                    break;
                case '2':
                    searchSingersByNameOrGenre();
                    break;
                case '3':
                    displaySongsByAscendingName();
                    break;
                case '4':
                    displayLatestSongs();
                    break;
                case '0':
                    System.out.println("Ứng dụng kết thúc.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }

            System.out.println();
            System.out.print("Nhấn Enter để quay lại Menu tìm kiếm...");
            scanner.nextLine();

        } while (true);
    }

    private void searchSongsByArtistOrGenre() {
        System.out.print("Nhập tên ca sĩ hoặc thể loại: ");
        String keyword = scanner.nextLine();

        List<Song> songs = songService.searchSongsByArtistOrGenre(keyword);
        if (songs.isEmpty()) {
            System.out.println("Không tìm thấy bài hát phù hợp.");
        } else {
            System.out.println("Danh sách bài hát:");
            displaySongList(songs);
        }
    }

    private void searchSingersByNameOrGenre() {
        System.out.print("Nhập tên ca sĩ hoặc thể loại: ");
        String keyword = scanner.nextLine();

        List<Singer> singers = singerService.searchSingersByNameOrGenre(keyword);
        if (singers.isEmpty()) {
            System.out.println("Không tìm thấy ca sĩ phù hợp.");
        } else {
            System.out.println("Danh sách ca sĩ:");
            displaySingerList(singers);
        }
    }

    private void displaySongsByAscendingName() {
        List<Song> songs = songService.getAllSongs();
        if (songs.isEmpty()) {
            System.out.println("Danh sách bài hát trống.");
        } else {
            Collections.sort(songs, Comparator.comparing(Song::getTitle));
            System.out.println("Danh sách bài hát theo thứ tự tên tăng dần:");
            displaySongList(songs);
        }
    }

    private void displayLatestSongs() {
        List<Song> songs = songService.getLatestSongs(10);
        if (songs.isEmpty()) {
            System.out.println("Danh sách bài hát trống.");
        } else {
            System.out.println("10 bài hát mới nhất:");
            displaySongList(songs);
        }
    }

    private void displaySongList(List<Song> songs) {
        for (Song song : songs) {
            System.out.println("ID: " + song.getId());
            System.out.println("Tên bài hát: " + song.getSongName());
            System.out.println("Ca sĩ: " + song.getArtist().getSingerName());
            System.out.println("Thể loại: " + song.getGenre());
            System.out.println("--------------------------");
        }
    }

    private void displaySingerList(List<Singer> singers) {
        if (singers.isEmpty()) {
            System.out.println("Không tìm thấy ca sĩ phù hợp.");
        } else {
            System.out.println("Danh sách ca sĩ:");
            for (Singer singer : singers) {
                System.out.println("ID: " + singer.getId());
                System.out.println("Tên ca sĩ: " + singer.getSingerName());
                System.out.println("Tuổi: " + singer.getAge());
                System.out.println("Quốc tịch: " + singer.getNationality());
                System.out.println("Giới tính: " + (singer.isGender() ? "Nam" : "Nữ"));
                System.out.println("Thể loại: " + singer.getGenre());
                System.out.println("--------------------------");
            }
        }
    }

}
