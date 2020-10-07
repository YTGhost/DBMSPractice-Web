package com.hihia.dao;

import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.domain.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {

    /**
     * 获取所有教师信息列表
     */
    @Select("select * from teacher")
    public List<Teacher> getTeacherList();

    /**
     * 根据分页获取教师信息列表
     */
    @Select("select * from teacher limit ${pageSize} offset ${(currentPage-1)*pageSize}")
    public List<Teacher> getTeacherListByPage(PageInfo pageInfo);

    /**
     * 获取教师信息列表总记录数
     */
    @Select("select count(*) as num from teacher")
    public Integer getTeacherListNum();

    /**
     * 检测编号是否已存在
     */
    @Select("select tno from teacher where tno = #{tno}")
    public List<String> checkTno(String tno);

    /**
     * 创建新的教师信息
     */
    @Insert("insert into teacher (tno, tname, tsex, tbirthday, ttitle) values ('${tno}', '${tname}', '${tsex}', '${tbirthday}', '${ttitle}')")
    public void createTeacher(Teacher teacher);

    /**
     * 删除教师信息
     */
    @Delete("delete from teacher where tno = #{tno}")
    public void deleteTeacher(String tno);

    /**
     * 模糊搜索教师表
     */
    @Select("select * from teacher where tno ~ #{query} or tname ~ #{query}")
    public List<Teacher> queryTeacher(String query);

    /**
     * 修改教师信息
     */
    @Update("update teacher set tname = '${tname}', tsex = '${tsex}', tbirthday = '${tbirthday}', ttitle = '${ttitle}' where tno = '${tno}'")
    public void modifyTeacher(Teacher teacher);
}
