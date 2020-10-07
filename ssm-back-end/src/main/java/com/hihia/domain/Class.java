package com.hihia.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "班级信息实体类")
public class Class implements Serializable {

    @ApiModelProperty(name = "classno", value = "班级编号", required = true)
    private String classno;

    @ApiModelProperty(name = "classname", value = "班级名称", required = true)
    private String classname;

    @ApiModelProperty(name = "classmajor", value = "所属专业", required = true)
    private String classmajor;

    @ApiModelProperty(name = "classdept", value = "所属院系", required = true)
    private String classdept;

    @ApiModelProperty(name = "studentnumber", value = "学生人数", required = true)
    private String studentnumber;

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassmajor() {
        return classmajor;
    }

    public void setClassmajor(String classmajor) {
        this.classmajor = classmajor;
    }

    public String getClassdept() {
        return classdept;
    }

    public void setClassdept(String classdept) {
        this.classdept = classdept;
    }

    public String getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber) {
        this.studentnumber = studentnumber;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classno='" + classno + '\'' +
                ", classname='" + classname + '\'' +
                ", classmajor='" + classmajor + '\'' +
                ", classdept='" + classdept + '\'' +
                ", studentnumber='" + studentnumber + '\'' +
                '}';
    }
}
