package com.hihia.service.impl;

import com.hihia.dao.StudentDao;
import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getStudentList() {
        return studentDao.getStudentList();
    }

    @Override
    public List<Student> getStudentListByPage(PageInfo pageInfo) {
        return studentDao.getStudentListByPage(pageInfo);
    }

    @Override
    public Integer getStudentListNum() {
        return studentDao.getStudentListNum();
    }

    @Override
    public List<String> checkSno(String sno) {
        return studentDao.checkSno(sno);
    }

    @Override
    public void createStudent(Student student) {
        studentDao.createStudent(student);
    }

    @Override
    public Student getStudentBySno(String sno) {
        return studentDao.getStudentBySno(sno);
    }

    @Override
    public void modifyStudent(Student student) {
        studentDao.modifyStudent(student);
    }

    @Override
    public void deleteStudent(String sno) {
        studentDao.deleteStudent(sno);
    }

    @Override
    public void deleteClassRelations(String classno) {
        studentDao.deleteClassRelations(classno);
    }

    @Override
    public List<Student> queryStudent(String query) {
        return studentDao.queryStudent(query);
    }
}
