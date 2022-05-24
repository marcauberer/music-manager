package com.marc_auberer.musicmanager.application;

import com.marc_auberer.musicmanager.domain.artist.ArtistRepository;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;
import com.marc_auberer.musicmanager.application.authorization.AuthorizationService;
import com.marc_auberer.musicmanager.application.exception.UserAlreadyExistsException;
import com.marc_auberer.musicmanager.application.exception.UserNotFoundException;
import com.marc_auberer.musicmanager.application.service.SongService;
import com.marc_auberer.musicmanager.application.service.UserService;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;
import com.marc_auberer.musicmanager.domain.song.SongRepository;
import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

public class MusicManager {

    private User currentUser;

    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final BarTypeRepository barTypeRepository;

    private AuthorizationService authorizationService;
    private UserService userService;
    private SongService songService;

    public MusicManager(
            UserRepository userRepository,
            SongRepository songRepository,
            ArtistRepository artistRepository,
            GenreRepository genreRepository,
            BarTypeRepository barTypeRepository
    ) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.barTypeRepository = barTypeRepository;
    }

    public User login(String username, String password) throws UserNotFoundException {
        // Search for the user in the database
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        // Check if the user exists
        User user = optionalUser.orElseThrow(() -> new UserNotFoundException("The user '" + username + "' was not found"));

        currentUser = user;
        instantiateServicesForUser();

        return user;
    }

    public void register(String username, String password) throws UserAlreadyExistsException {
        // Check if the user exists already
        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("The user '" + username + "' already exists");
        }

        // Create new user
        User user = new User(0, username, password, new ArrayList<>());
        userRepository.save(user);
        currentUser = user;
        instantiateServicesForUser();
    }

    private void instantiateServicesForUser() {
        this.authorizationService = new AuthorizationService(currentUser);
        this.userService = new UserService(userRepository, currentUser);
        this.songService = new SongService(songRepository, authorizationService, currentUser);
    }
}
