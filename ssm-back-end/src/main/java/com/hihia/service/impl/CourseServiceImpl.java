package com.hihia.service.impl;

import com.hihia.dao.CourseDao;
import com.hihia.domain.Course;
import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getCourseList() {
        return courseDao.getCourseList();
    }

    @Override
    public List<Course> getCourseListByPage(PageInfo pageInfo) {
        return courseDao.getCourseListByPage(pageInfo);
    }

    @Override
    public Integer getCourseListNum() {
        return courseDao.getCourseListNum();
    }

    @Override
    public void createCourse(Course course) {
        courseDao.createCourse(course);
    }

    @Override
    public List<String> checkCno(String cno) {
        return courseDao.checkCno(cno);
    }

    @Override
    public void deleteCourse(String cno) {
        courseDao.deleteCourse(cno);
    }

    @Override
    public void modifyCourse(Course course) {
        courseDao.modifyCourse(course);
    }
}
