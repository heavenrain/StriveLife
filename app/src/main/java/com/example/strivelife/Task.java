package com.example.strivelife;

public class Task {
    String name = null;
    String group = null;
    int type = 0;
    boolean selected = false;
    int amount = 0;
    int expected_amount = 0;
    String time = null;

    String deadline = null;


    // === Task 屬性說明 ===
    // name: 任務名稱, e.g. 睡覺
    // group: 發起此任務的比賽名稱, e.g. 再睡 5 分鐘
    // type: 任務類別 {0: 完成類型, 1: 量達標類型, 2: 時間類型}
    // selected: 選擇完成與否
    // amount: 任務規定完成的量
    // deadline: 任務規定完成的時間, e.g. "2023/05/25 15:05:01";
    // ====================
    public Task(String name, String group, int type, boolean selected, int expected_amount, String deadline) {
        super();
        this.name = name;
        this.group = group;
        this.type = type;
        this.selected = selected;
        this.amount = 0;
        this.expected_amount = expected_amount;
        this.time = null;
        this.deadline = deadline;
    }

    // get & set for all parameter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {this.group = group;}
    public int getType() {
        return type;
    }
    public void setType(int type) {this.type = type;}

    public boolean isSelected() {return selected;}
    public void setSelected(boolean selected) {this.selected = selected;}
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {this.amount = amount;}
    public int getExpectedAmount() { return expected_amount;}
    public void setExpectedAmount(int expected_amount) {this.expected_amount = expected_amount;}
    public String getTime() {
        return time;
    }
    public void setTime(String time) {this.time = time;}
    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {this.deadline = deadline;}

}
