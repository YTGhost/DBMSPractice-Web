package com.hihia.service;

import com.hihia.domain.Class;
import com.hihia.domain.PageInfo;

import java.util.List;

public interface ClassService {

    /**
     * 获取所有班级信息列表
     */
    public List<Class> getClassList();

    /**
     * 根据分页获取班级信息列表
     */
    public List<Class> getClassListByPage(PageInfo pageInfo);

    /**
     * 获取班级信息列表总记录数
     */
    public Integer getClassListNum();

    /**
     * 获取班级编号列表
     */
    public List<String> getClassnoList();

    /**
     * 对指定的班级中班级人数加1
     */
    public void incStudentnumberByClassno(String classno);

    /**
     * 对指定的班级中班级人数减一
     */
    public void decStudentnumberByClassno(String classno);

    /**
     * 创建新的班级信息
     */
    public void createClass(Class newClass);

    /**
     * 检测班级编号是否已存在
     */
    public List<String> checkClassno(String classno);

    /**
     * 删除班级信息
     */
    public void deleteClass(String classno);
}
