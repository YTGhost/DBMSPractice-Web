package com.hihia.service.impl;

import com.hihia.dao.ClassDao;
import com.hihia.domain.Class;
import com.hihia.domain.PageInfo;
import com.hihia.domain.Teacher;
import com.hihia.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classServiceImpl")
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Override
    public List<Class> getClassList() {
        return classDao.getClassList();
    }

    @Override
    public List<Class> getClassListByPage(PageInfo pageInfo) {
        return classDao.getTeacherListByPage(pageInfo);
    }

    @Override
    public Integer getClassListNum() {
        return classDao.getTeacherListNum();
    }

    @Override
    public List<String> getClassnoList() {
        return classDao.getClassnoList();
    }

    @Override
    public void incStudentnumberByClassno(String classno) {
        classDao.incStudentnumberByClassno(classno);
    }

    @Override
    public void decStudentnumberByClassno(String classno) {
        classDao.decStudentnumberByClassno(classno);
    }

    @Override
    public void createClass(Class newClass) {
        classDao.createClass(newClass);
    }

    @Override
    public List<String> checkClassno(String classno) {
        return classDao.checkClassno(classno);
    }

    @Override
    public void deleteClass(String classno) {
        classDao.deleteClass(classno);
    }
}
