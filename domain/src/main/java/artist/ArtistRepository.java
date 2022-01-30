package artist;

import java.util.List;

public interface ArtistRepository {
    List<Artist> findAllArtistsBySongId(long songId);

    Artist save(Artist artist, long songId);

    void delete(int id);
}