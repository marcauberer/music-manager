package com.marc_auberer.musicmanager.application.authorization;

import com.marc_auberer.musicmanager.application.exception.UnauthorizedException;
import com.marc_auberer.musicmanager.domain.user.User;

public class AuthorizationService {

    private final User user;

    public AuthorizationService(User user) {
        this.user = user;
    }

    /**
     * Check if the user is permitted to get access to the requested song
     *
     * @param songId the song to be accessed
     * @throws UnauthorizedException exception is thrown if the user is not authorized to modify the recipe
     */
    public void checkAuthorization(long songId) throws UnauthorizedException {
        user.getSongs().stream()
                .filter(song -> song.getId() == songId)
                .findFirst()
                .orElseThrow(() -> new UnauthorizedException("You can't edit items from other users"));
    }
}
