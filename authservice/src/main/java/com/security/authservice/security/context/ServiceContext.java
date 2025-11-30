package com.security.authservice.security.context;

public class ServiceContext {

    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

    public static void setUser(String username) {
        currentUser.set(username);
    }

    public static String getUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
