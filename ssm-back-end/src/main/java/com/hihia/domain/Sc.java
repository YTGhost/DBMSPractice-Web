package com.hihia.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "学生选课信息实体类")
public class Sc implements Serializable {

    @ApiModelProperty(name = "sno", value = "学号", required = true)
    private String sno;
    @ApiModelProperty(name = "sname", value = "姓名", required = true)
    private String sname;
    @ApiModelProperty(name = "cno", value = "课程编号", required = true)
    private String cno;
    @ApiModelProperty(name = "cname", value = "课程名称", required = true)
    private String cname;
    @ApiModelProperty(name = "grade", value = "成绩", required = true)
    private String grade;

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

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
