package com.example.lzq.supperpictagview.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by hrcf on 2016/4/11.
 */
public class AttentionBrand implements Serializable {


    /**
     * id : 5133
     * img : http://www.doolii.cn/static/hgj/img/3th/uploadtemp/mall2w/2015-07/brand_dde84250715bac2e3396a6f61fbc73ae.jpg
     * name : LUXURY REBEL
     * benefitNum : 2条优惠
     * followNum : 1人关注
     * selfFollow : true
     * clist : ["女鞋"]
     */

    private int id;
    private String img;
    private String name;
    private String nameCn;
    private String benefitNum;
    private String followNum;
    private boolean selfFollow;
    private List<String> clist;

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBenefitNum(String benefitNum) {
        this.benefitNum = benefitNum;
    }

    public void setFollowNum(String followNum) {
        this.followNum = followNum;
    }

    public void setSelfFollow(boolean selfFollow) {
        this.selfFollow = selfFollow;
    }

    public void setClist(List<String> clist) {
        this.clist = clist;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getBenefitNum() {
        return benefitNum;
    }

    public String getFollowNum() {
        return followNum;
    }

    public boolean isSelfFollow() {
        return selfFollow;
    }

    public List<String> getClist() {
        return clist;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameCn() {
        return nameCn;
    }
}
