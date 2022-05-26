package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.domain.user.User;

public interface LoginObserver {
    void onLogin(User user);
    void onLogout();
}
