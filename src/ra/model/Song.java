package ra.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import ra.model.Singer;

import ra.service.SingerService;

public class Song {
    private int songId;
    private String songName;
    private String descriptions;
    private Singer singer;
    private String songWriter;
    private Date createdDate;
    private boolean songStatus;
    private static ArrayList<Song> songList = new ArrayList<>();
    private static int nextSongId = 1;

    public Song() {
    }

    public Song(int songId, String songName, String descriptions, Singer singer, String songWriter, Date createdDate, boolean songStatus) {
        this.songId = songId;
        this.songName = songName;
        this.descriptions = descriptions;
        this.singer = singer;
        this.songWriter = songWriter;
        this.createdDate = createdDate;
        this.songStatus = songStatus;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
    private String genre;

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    public static void inputData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập thông tin bài hát:");
        Song song = new Song();
        song.setSongId(nextSongId++);

        System.out.print("Tên bài hát: ");
        song.setSongName(scanner.nextLine());

        System.out.print("Mô tả: ");
        song.setDescriptions(scanner.nextLine());

        // Chọn ca sĩ
        ArrayList<Singer> singerList = SingerService.getSingerList();
        if (singerList.isEmpty()) {
            System.out.println("Danh sách ca sĩ trống. Vui lòng thêm ca sĩ trước!");
            Singer.inputDataSinger();
        }

        System.out.println("Danh sách ca sĩ:");
        for (Singer singer : singerList) {
            System.out.println("ID: " + singer.getSingerId());
            System.out.println("Tên ca sĩ: " + singer.getSingerName());
            System.out.println("---------------------");
        }

        System.out.print("Nhập ID của ca sĩ: ");
        int singerId = Integer.parseInt(scanner.nextLine());
        Singer selectedSinger = findSingerById(singerId);

        if (selectedSinger == null) {
            System.out.println("Không tìm thấy ca sĩ có ID " + singerId);
        }

        song.setSinger(selectedSinger);

        System.out.print("Tên nhạc sĩ: ");
        song.setSongWriter(scanner.nextLine());

        song.setCreatedDate(new Date());

        System.out.print("Trạng thái bài hát (true/false): ");
        song.setSongStatus(Boolean.parseBoolean(scanner.nextLine()));

        songList.add(song);


        scanner.close();
    }

    public static void displayData() {
        System.out.println("Danh sách bài hát:");
        for (Song song : songList) {
            System.out.println("ID: " + song.getSongId());
            System.out.println("Tên bài hát: " + song.getSongName());
            System.out.println("Mô tả: " + song.getDescriptions());
            System.out.println("Ca sĩ: " + song.getSinger().getSingerName());
            System.out.println("Tên nhạc sĩ: " + song.getSongWriter());
            System.out.println("Ngày tạo: " + song.getCreatedDate());
            System.out.println("Trạng thái: " + (song.isSongStatus() ? "Hoạt động" : "Không hoạt động"));
            System.out.println("---------------------");
        }
    }

    public static Singer findSingerById(int singerId) {
        ArrayList<Singer> singerList = SingerService.getSingerList();
        for (Singer singer : singerList) {
            if (singer.getSingerId() == singerId) {
                return singer;
            }
        }
        return null;
    }

    public static ArrayList<Song> getSongList() {
        return songList;
    }

    public static <U extends Comparable<? super U>, T> U getReleaseDate(T t) {
        if (t instanceof Song) {
            Song song = (Song) t;
            return (U) song.getCreatedDate();
        }
        return null;
    }

    public static <U extends Comparable<? super U>, T> U getTitle(T t) {
        if (t instanceof Song) {
            Song song = (Song) t;
            return (U) song.getSongName();
        }
        return null;
    }

    public String getGenre() {

        return genre;
    }

    public Singer getArtist() {
        return singer;
    }

    public String getId() {

        return String.valueOf(songId);
    }

}
