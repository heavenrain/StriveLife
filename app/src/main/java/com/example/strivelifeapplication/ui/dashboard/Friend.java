package com.example.strivelifeapplication.ui.dashboard;

public class Friend {

    String name = null;
    String avatar = null;

    // === Friend 屬性說明 ===
    // id: 好友id
    // nane: 好友id
    // ====================
    public Friend(String name, String avatar) {
        super();
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
