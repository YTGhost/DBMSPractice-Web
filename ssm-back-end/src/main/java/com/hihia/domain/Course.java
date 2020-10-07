package com.hihia.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "课程信息实体类")
public class Course {

    @ApiModelProperty(name = "cno", value = "课程编号", required = true)
    private String cno;

    @ApiModelProperty(name = "cname", value = "课程名称", required = true)
    private String cname;

    @ApiModelProperty(name = "ccredit", value = "课程学分", required = true)
    private String ccredit;

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

    public String getCcredit() {
        return ccredit;
    }

    public void setCcredit(String ccredit) {
        this.ccredit = ccredit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", ccredit='" + ccredit + '\'' +
                '}';
    }
}
