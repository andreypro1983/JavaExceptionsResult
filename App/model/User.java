package App.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String surName;
    private String name;
    private String fatherName;
    private Date birthdayDate;
    private long phone;
    private Sex sex;

    public User(String surName, String name, String fatherName, Date birthdayDate, long phone, Sex sex) {
        this.surName = surName;
        this.name = name;
        this.fatherName = fatherName;
        this.birthdayDate = birthdayDate;
        this.phone = phone; 
        this.sex = sex;
    }

    public String getSurName() {
        return surName;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public long getPhone() {
        return phone;
    }

    public Sex getSex() {
        return sex;
    }

    public String getUserInfo(String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String formattedDate = formatter.format(birthdayDate);
        return "<" + surName + "><" + name + "><" + fatherName + "><" + formattedDate + "><" + phone + "><"
                + sex + ">";

    }

}
