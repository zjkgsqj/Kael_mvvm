package com.ftd.keal.keal_mvvm.model;

import java.io.Serializable;

/**
 * Created by sdhuang on 16/7/4 14:12.
 */
public class ExtraField  implements Serializable {
    private boolean isHeader;
    private String date;

    public ExtraField(boolean isHeader, String date) {
        this.isHeader = isHeader;
        this.date = date;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}