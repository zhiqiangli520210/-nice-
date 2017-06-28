package com.example.lzq.supperpictagview.bean;


import java.io.Serializable;

/**
 * Created by lzq on 2016/8/24.
 * 历史、推荐标签
 */
public class RecordBean implements Serializable {


    private String type;
    private String name;

    public RecordBean(String type, String name) {
        this.type=type;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
