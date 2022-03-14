package song;

import java.util.List;

public interface SongRepository {
    Song findSongById(long id);

    List<Song> findAllSongs();

    List<Song> findAllSongsByUserId(long userId);

    Song save(Song song);

    void delete(long id);
}