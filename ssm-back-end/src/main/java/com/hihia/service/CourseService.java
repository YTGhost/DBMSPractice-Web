package com.hihia.service;

import com.hihia.domain.Course;
import com.hihia.domain.PageInfo;

import java.util.List;

public interface CourseService {

    /**
     * 获取所有课程信息列表
     */
    public List<Course> getCourseList();

    /**
     * 根据分页获取课程信息列表
     */
    public List<Course> getCourseListByPage(PageInfo pageInfo);

    /**
     * 获取课程信息列表总记录数
     */
    public Integer getCourseListNum();

    /**
     * 创建新的课程信息
     */
    public void createCourse(Course course);

    /**
     * 检测课程编号是否已存在
     */
    public List<String> checkCno(String cno);

    /**
     * 删除课程信息
     */
    public void deleteCourse(String cno);

    /**
     * 修改课程信息
     */
    public void modifyCourse(Course course);
}
