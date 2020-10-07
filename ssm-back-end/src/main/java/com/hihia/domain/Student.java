package com.hihia.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "学生信息实体类")
public class Student implements Serializable {

    @ApiModelProperty(name = "sno", value = "学号", required = true)
    private String sno;
    @ApiModelProperty(name = "sname", value = "姓名", required = true)
    private String sname;
    @ApiModelProperty(name = "ssex", value = "性别", required = true)
    private String ssex;
    @ApiModelProperty(name = "sbirthday", value = "出生日期", required = true)
    private String sbirthday;
    @ApiModelProperty(name = "classno", value = "班级", required = true)
    private String classno;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sbirthday='" + sbirthday + '\'' +
                ", classno='" + classno + '\'' +
                '}';
    }
}
