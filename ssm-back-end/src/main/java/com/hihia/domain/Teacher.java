package com.hihia.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "老师信息实体类")
public class Teacher implements Serializable {

    @ApiModelProperty(name = "tno", value = "教师编号", required = true)
    private String tno;

    @ApiModelProperty(name = "tname", value = "姓名", required = true)
    private String tname;

    @ApiModelProperty(name = "tsex", value = "性别", required = true)
    private String tsex;

    @ApiModelProperty(name = "tbirthday", value = "出生日期", required = true)
    private String tbirthday;

    @ApiModelProperty(name = "ttitle", value = "职称", required = true)
    private String ttitle;

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public String getTbirthday() {
        return tbirthday;
    }

    public void setTbirthday(String tbirthday) {
        this.tbirthday = tbirthday;
    }

    public String getTtitle() {
        return ttitle;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", tsex='" + tsex + '\'' +
                ", tbirthday='" + tbirthday + '\'' +
                ", ttitle='" + ttitle + '\'' +
                '}';
    }
}
