package com.hihia.dao;

import com.hihia.domain.Sc;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScDao {

    /**
     * 删除与指定学号相关的所有选课记录
     */
    @Delete("delete from sc where sno = #{sno}")
    public void deleteRecords(String sno);

    /**
     * 删除与指定课程号相关的所有选课记录
     */
    @Delete("delete from sc where cno = #{cno}")
    public void deleteScBycno(String cno);

    /**
     * 获取所有学生选课信息列表
     */
    @Select("select sc.sno, student.sname, sc.cno, course.cname, sc.grade from sc, student, course where student.sno = sc.sno and course.cno = sc.cno")
    public List<Sc> getStudentScList();
}
