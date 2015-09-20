package smikhlevskiy.myfirstapplication.model;

import java.util.Date;

/**
 * Created by tcont98 on 15-Sep-15.
 */
public class RegPlaceItemData {
    public String getName() {
        return name;
    }

    private String name;
    private String address;
    private Date date;
    private String comment;
    private String fileName;

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
