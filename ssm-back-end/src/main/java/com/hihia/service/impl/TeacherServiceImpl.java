package com.hihia.service.impl;

import com.hihia.dao.TeacherDao;
import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.domain.Teacher;
import com.hihia.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> getTeacherList() {
        return teacherDao.getTeacherList();
    }

    @Override
    public List<Teacher> getTeacherListByPage(PageInfo pageInfo) {
        return teacherDao.getTeacherListByPage(pageInfo);
    }

    @Override
    public Integer getTeacherListNum() {
        return teacherDao.getTeacherListNum();
    }

    @Override
    public List<String> checkTno(String tno) {
        return teacherDao.checkTno(tno);
    }

    @Override
    public void createTeacher(Teacher teacher) {
        teacherDao.createTeacher(teacher);
    }

    @Override
    public void deleteTeacher(String tno) {
        teacherDao.deleteTeacher(tno);
    }

    @Override
    public List<Teacher> queryTeacher(String query) {
        return teacherDao.queryTeacher(query);
    }

    @Override
    public void modifyTeacher(Teacher teacher) {
        teacherDao.modifyTeacher(teacher);
    }


}
