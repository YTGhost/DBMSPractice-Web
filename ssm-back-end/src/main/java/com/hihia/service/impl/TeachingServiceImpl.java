package com.hihia.service.impl;

import com.hihia.dao.TeachingDao;
import com.hihia.domain.Teaching;
import com.hihia.service.TeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teachingServiceImpl")
public class TeachingServiceImpl implements TeachingService {

    @Autowired
    private TeachingDao teachingDao;

    @Override
    public void deleteTeacheringByTno(String tno) {
        teachingDao.deleteTeacheringByTno(tno);
    }

    @Override
    public List<Teaching> getTeachingList() {
        return teachingDao.getTeachingList();
    }

    @Override
    public List<String> getTeachingLanguageList() {
        return teachingDao.getTeachingLanguageList();
    }

    @Override
    public List<String> getTeachingCourseList() {
        return teachingDao.getTeachingCourseList();
    }
}
