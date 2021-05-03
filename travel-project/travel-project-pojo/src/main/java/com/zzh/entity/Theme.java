package com.zzh.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 主题旅游映射关系图片
 */
public class Theme extends Model<Theme> {

    private static final long serialVersionUID = 1L;

    /*数据库得自增Id，存储得时候根据Id顺序来进行存储，a-98 A-65 0-48*/
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String themeName;
    private String imageUrl;
    private String detail;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Theme{" +
        ", id=" + id +
        ", themeName=" + themeName +
        ", imageUrl=" + imageUrl +
        ", detail=" + detail +
        "}";
    }
}
