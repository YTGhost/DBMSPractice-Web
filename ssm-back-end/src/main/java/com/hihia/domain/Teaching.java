package com.hihia.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "课程安排信息实体类")
public class Teaching implements Serializable {

    @ApiModelProperty(name = "cno", value = "课程号", required = true)
    private String cno;
    @ApiModelProperty(name = "cname", value = "课程名称", required = true)
    private String cname;
    @ApiModelProperty(name = "tno", value = "授课老师编号", required = true)
    private String tno;
    @ApiModelProperty(name = "tname", value = "授课老师姓名", required = true)
    private String tname;
    @ApiModelProperty(name = "language", value = "课程语言", required = true)
    private String language;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Teaching{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
