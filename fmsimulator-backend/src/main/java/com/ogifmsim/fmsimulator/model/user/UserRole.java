package com.ogifmsim.fmsimulator.model.user;

public enum UserRole {
    VIEWER("Viewer", "icon_viewer.png"),
    ADMIN("Admin", "icon_admin.png");

    private String name, url;

    private UserRole(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() { return this.name; }
    public String getUrl() { return this.url; }
}