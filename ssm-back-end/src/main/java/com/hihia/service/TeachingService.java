package com.hihia.service;

import com.hihia.domain.Teaching;

import java.util.List;

public interface TeachingService {

    /**
     * 根据教师编号删除所教授课程的记录
     */
    public void deleteTeacheringByTno(String tno);

    /**
     * 获取所有课程安排信息列表
     */
    public List<Teaching> getTeachingList();

    /**
     * 获取所有授课语言列表
     */
    public List<String> getTeachingLanguageList();

    /**
     * 获取所有授课课程列表
     */
    public List<String> getTeachingCourseList();
}
