package song;

import java.util.List;

public interface SongRepository {
    Song findSongById(long id);

    List<Song> findAllSongs();

    Song save(Song song);

    void delete(long id);
}