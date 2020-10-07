package com.hihia.dao;


import com.hihia.domain.Class;
import com.hihia.domain.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDao {

    /**
     * 获取所有班级信息列表
     */
    @Select("select * from class")
    public List<Class> getClassList();

    /**
     * 根据分页获取班级信息列表
     */
    @Select("select * from class limit ${pageSize} offset ${(currentPage-1)*pageSize}")
    public List<Class> getTeacherListByPage(PageInfo pageInfo);

    /**
     * 获取教师信息列表总记录数
     */
    @Select("select count(*) as num from class")
    public Integer getTeacherListNum();

    /**
     * 获取班级编号列表
     */
    @Select("select classno from class order by classno asc")
    public List<String> getClassnoList();

    /**
     * 对指定的班级中班级人数加1
     */
    @Update("update class set studentnumber=studentnumber+1 where classno = #{classno}")
    public void incStudentnumberByClassno(String classno);

    /**
     * 对指定的班级中班级人数减1
     */
    @Update("update class set studentnumber=studentnumber-1 where classno = #{classno}")
    public void decStudentnumberByClassno(String classno);

    /**
     * 创建新的班级信息
     */
    @Insert("insert into class (classno, classname, classmajor, classdept) values ('${classno}', '${classname}', '${classmajor}', '${classdept}')")
    public void createClass(Class newClass);

    /**
     * 检测班级编号是否已存在
     */
    @Select("select classno from class where classno = #{classno}")
    public List<String> checkClassno(String classno);

    /**
     * 删除班级信息
     */
    @Delete("delete from class where classno = #{classno}")
    public void deleteClass(String classno);
}
