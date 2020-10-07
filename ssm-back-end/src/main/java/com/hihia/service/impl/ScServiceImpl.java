package com.hihia.service.impl;

import com.hihia.dao.ScDao;
import com.hihia.domain.Sc;
import com.hihia.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("scServiceImpl")
public class ScServiceImpl implements ScService {

    @Autowired
    private ScDao scDao;

    @Override
    public void deleteRecords(String sno) {
        scDao.deleteRecords(sno);
    }

    @Override
    public void deleteScBycno(String cno) {
        scDao.deleteScBycno(cno);
    }

    @Override
    public List<Sc> getStudentScList() {
        return scDao.getStudentScList();
    }
}
