package com.hihia.service;

import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;

import java.util.List;

public interface StudentService {
    /**
     * 获取所有学生信息列表
     */
    public List<Student> getStudentList();

    /**
     * 根据分页获取学生信息列表
     */
    public List<Student> getStudentListByPage(PageInfo pageInfo);

    /**
     * 获取学生信息列表总记录数
     */
    public Integer getStudentListNum();

    /**
     * 检测学号是否已存在
     */
    public List<String> checkSno(String sno);

    /**
     * 创建新的学生信息
     */
    public void createStudent(Student student);

    /**
     * 根据学号获得学生信息
     */
    public Student getStudentBySno(String sno);

    /**
     * 修改学生信息
     */
    public void modifyStudent(Student student);

    /**
     * 删除学生信息
     */
    public void deleteStudent(String sno);

    /**
     * 将指定班级编号的学生的班级编号清空
     */
    public void deleteClassRelations(String classno);

    /**
     * 模糊搜索学生表
     */
    public List<Student> queryStudent(String query);
}
