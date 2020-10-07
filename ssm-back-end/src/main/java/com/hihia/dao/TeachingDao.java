package com.hihia.dao;

import com.hihia.domain.Teaching;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingDao {

    /**
     * 删除学生信息
     */
    @Delete("delete from teaching where tno = #{tno}")
    public void deleteTeacheringByTno(String tno);

    /**
     * 获取所有课程安排信息列表
     */
    @Select("select teaching.cno, course.cname, teaching.tno, teacher.tname, teaching.language from teaching, teacher, course where course.cno = teaching.cno and teacher.tno = teaching.tno")
    public List<Teaching> getTeachingList();

    /**
     * 获取所有授课语言列表
     */
    @Select("select distinct language from teaching")
    public List<String> getTeachingLanguageList();

    /**
     * 获取所有授课课程列表
     */
    @Select("select cname from course where cno in (select distinct cno from teaching)")
    public List<String> getTeachingCourseList();
}
