package com.hihia.service;

import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.domain.Teacher;

import java.util.List;

public interface TeacherService {

    /**
     * 获取所有教师信息列表
     */
    public List<Teacher> getTeacherList();

    /**
     * 根据分页获取教师信息列表
     */
    public List<Teacher> getTeacherListByPage(PageInfo pageInfo);

    /**
     * 获取教师信息列表总记录数
     */
    public Integer getTeacherListNum();

    /**
     * 检测编号是否已存在
     */
    public List<String> checkTno(String tno);

    /**
     * 创建新的教师信息
     */
    public void createTeacher(Teacher teacher);

    /**
     * 删除教师信息
     */
    public void deleteTeacher(String tno);

    /**
     * 模糊搜索教师表
     */
    public List<Teacher> queryTeacher(String query);

    /**
     * 修改教师信息
     */
    public void modifyTeacher(Teacher teacher);
}
