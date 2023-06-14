package ra.model;

import ra.service.SingerService;
import java.util.Scanner;

public class Singer {
    private static int nextId = 1; // Khai báo biến nextId để tự tăng ID ca sĩ
    private static final SingerService singerService = new SingerService(); // Khởi tạo đối tượng SingerService

    private int singerId;
    private String singerName;
    private int age;
    private String nationality;
    private boolean gender;
    private String genre;

    public Singer() {
    }

    public Singer(int singerId, String singerName, int age, String nationality, boolean gender, String genre) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.genre = genre;
    }

    public static void inputDataSinger() {
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public static void inputData() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Nhập thông tin ca sĩ:");
            Singer singer = new Singer();
            singer.setSingerId(nextId++);

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

            singerService.addSinger(singer); // Thêm ca sĩ vào danh sách

    }

    public static void displayData() {
        System.out.println("Danh sách ca sĩ:");
        Singer[] singers = singerService.getAllSingers(); // Lấy danh sách ca sĩ từ SingerService

        for (Singer singer : singers) {
            if (singer != null) {
                System.out.println("-------------Danh Sách Ca Sĩ--------------------");
                System.out.println("ID: " + singer.getSingerId());
                System.out.println("Tên ca sĩ: " + singer.getSingerName());
                System.out.println("Tuổi: " + singer.getAge());
                System.out.println("Quốc tịch: " + singer.getNationality());
                System.out.println("Giới tính: " + (singer.isGender() ? "Nam" : "Nữ"));
                System.out.println("Thể loại: " + singer.getGenre());
                System.out.println("---------------------");
            }
        }
    }

    public String getId() {
        return String.valueOf(singerId);
    }

    public boolean equalsIgnoreCase(String keyword) {
        return singerName.equalsIgnoreCase(keyword);
    }
}
