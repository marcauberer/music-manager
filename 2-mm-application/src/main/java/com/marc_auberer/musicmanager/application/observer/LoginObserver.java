package com.marc_auberer.musicmanager.application.observer;

import com.marc_auberer.musicmanager.domain.user.User;

public interface LoginObserver {
    void onLogin(User user);
    void onLogout();
}
