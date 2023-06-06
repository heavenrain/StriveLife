package com.example.strivelifeapplication.ui.dashboard;

public class Friend {

    String name = null;
    int id = 0;

    // === Friend 屬性說明 ===
    // id: 好友id
    // nane: 好友id
    // ====================
    public Friend(int id, String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
