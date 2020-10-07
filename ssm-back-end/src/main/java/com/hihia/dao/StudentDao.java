package com.hihia.dao;

import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    /**
     * 获取所有学生信息列表
     */
    @Select("select * from student")
    public List<Student> getStudentList();

    /**
     * 根据分页获取学生信息列表
     */
    @Select("select * from student limit ${pageSize} offset ${(currentPage-1)*pageSize}")
    public List<Student> getStudentListByPage(PageInfo pageInfo);

    /**
     * 获取学生信息列表总记录数
     */
    @Select("select count(*) as num from student")
    public Integer getStudentListNum();

    /**
     * 检测学号是否已存在
     */
    @Select("select sno from student where sno = #{sno}")
    public List<String> checkSno(String sno);

    /**
     * 创建新的学生信息
     */
    @Insert("insert into student (sno, sname, ssex, sbirthday, classno) values ('${sno}', '${sname}', '${ssex}', '${sbirthday}', '${classno}')")
    public void createStudent(Student student);

    /**
     * 根据学号获得学生信息
     */
    @Select("select * from student where sno = #{sno}")
    public Student getStudentBySno(String sno);

    /**
     * 修改学生信息
     */
    @Update("update student set sname = '${sname}', ssex = '${ssex}', sbirthday = '${sbirthday}', classno = '${classno}' where sno = '${sno}'")
    public void modifyStudent(Student student);

    /**
     * 删除学生信息
     */
    @Delete("delete from student where sno = #{sno}")
    public void deleteStudent(String sno);

    /**
     * 将指定班级编号的学生删除
     */
    @Delete("delete from student where classno = #{classno}")
    public void deleteClassRelations(String classno);

    /**
     * 模糊搜索学生表
     */
    @Select("select * from student where sno ~ #{query} or sname ~ #{query}")
    public List<Student> queryStudent(String query);
}
