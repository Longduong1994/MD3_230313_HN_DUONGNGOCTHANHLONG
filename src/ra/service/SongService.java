package ra.service;

import ra.model.Song;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SongService {
    private static ArrayList<Song> listSongs = new ArrayList<>();

    public ArrayList<Song> getAllSongs() {
        return listSongs;
    }

    public void updateSong(int songId, Song updatedSong) {
        for (int i = 0; i < listSongs.size(); i++) {
            if (listSongs.get(i).getSongId() == songId) {
                listSongs.set(i, updatedSong);
                System.out.println("Bài hát đã được cập nhật thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy bài hát có mã " + songId);
    }

    public boolean deleteSong(int songId) {
        for (int i = 0; i < listSongs.size(); i++) {
            if (listSongs.get(i).getSongId() == songId) {
                listSongs.remove(i);
                System.out.println("Bài hát đã được xóa thành công.");
                return false;
            }
        }
        System.out.println("Không tìm thấy bài hát có mã " + songId);
        return false;
    }

    public Song findSongById(int songId) {
        for (Song song : listSongs) {
            if (song.getSongId() == songId) {
                return song;
            }
        }
        return null;
    }

    public void addSong(Song song) {
        listSongs.add(song);
        System.out.println("Bài hát đã được thêm thành công.");
    }

    public List<Song> searchSongsByArtistOrGenre(String keyword) {
        List<Song> result = new ArrayList<>();

        for (Song song : listSongs) {
            if (song.getSinger() != null && song.getSinger().getSingerName().equalsIgnoreCase(keyword) || song.getGenre().equalsIgnoreCase(keyword)) {
                result.add(song);
            }
        }

        return result;
    }


    public List<Song> getLatestSongs(int count) {
        List<Song> latestSongs = new ArrayList<>(listSongs);
        latestSongs.sort(Comparator.comparing(Song::getReleaseDate).reversed());
        return latestSongs.subList(0, Math.min(count, latestSongs.size()));
    }
}
