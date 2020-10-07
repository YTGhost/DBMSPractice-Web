package com.hihia.service;

import com.hihia.domain.Sc;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface ScService {

    /**
     * 删除与指定学号相关的所有选课记录
     */
    public void deleteRecords(String sno);

    /**
     * 删除与指定课程号相关的所有选课记录
     */
    public void deleteScBycno(String cno);

    /**
     * 获取所有学生选课信息列表
     */
    public List<Sc> getStudentScList();
}
