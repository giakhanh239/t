package com.example.myapplication;

import java.io.Serializable;

public class Member implements Serializable {
    private String name;
    private String phone;
    private boolean checked;

    public Member() {
    }

    public Member(String name, String phone, boolean checked) {
        this.name = name;
        this.phone = phone;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
