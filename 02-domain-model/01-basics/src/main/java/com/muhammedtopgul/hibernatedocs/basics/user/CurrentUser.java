package com.muhammedtopgul.hibernatedocs.basics.user;

/**
 * created by Muhammed Topgul on 16/09/2021 at 11:15
 */

public class CurrentUser {

    public static final CurrentUser INSTANCE = new CurrentUser();

    private static final ThreadLocal<String> storage = new ThreadLocal<>();

    public void logIn(String user) {
        storage.set(user);
    }

    public void logOut() {
        storage.remove();
    }

    public String get() {
        return storage.get();
    }
}
