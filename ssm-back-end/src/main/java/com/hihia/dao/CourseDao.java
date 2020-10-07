package com.hihia.dao;

import com.hihia.domain.Class;
import com.hihia.domain.Course;
import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {

    /**
     * 获取所有课程信息列表
     */
    @Select("select * from course")
    public List<Course> getCourseList();

    /**
     * 根据分页获取课程信息列表
     */
    @Select("select * from course limit ${pageSize} offset ${(currentPage-1)*pageSize}")
    public List<Course> getCourseListByPage(PageInfo pageInfo);

    /**
     * 获取课程信息列表总记录数
     */
    @Select("select count(*) as num from course")
    public Integer getCourseListNum();

    /**
     * 创建新的课程信息
     */
    @Insert("insert into course (cno, cname, ccredit) values ('${cno}', '${cname}', '${ccredit}')")
    public void createCourse(Course course);

    /**
     * 检测课程编号是否已存在
     */
    @Select("select cno from course where cno = #{cno}")
    public List<String> checkCno(String cno);

    /**
     * 删除课程信息
     */
    @Delete("delete from course where cno = #{cno}")
    public void deleteCourse(String cno);

    /**
     * 修改课程信息
     */
    @Update("update course set cname = '${cname}', ccredit = '${ccredit}' where cno = '${cno}'")
    public void modifyCourse(Course course);
}
