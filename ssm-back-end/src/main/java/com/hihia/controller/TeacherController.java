package com.hihia.controller;

import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.domain.Teacher;
import com.hihia.service.TeacherService;
import com.hihia.service.TeachingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "教师信息模块Controller", tags = {"教师信息模块"})
@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeachingService teachingService;

    @ApiOperation(value = "获取所有教师信息列表")
    @GetMapping(value = "/getTeacherList")
    @ResponseBody
    public Map<String, Object> getTeacherList() {
        List<Teacher> list = teacherService.getTeacherList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", list.size());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "根据分页获取教师信息列表")
    @PostMapping(value = "/getTeacherListByPage")
    @ResponseBody
    public Map<String, Object> getTeacherListByPage(@RequestBody PageInfo pageInfo) {
        List<Teacher> list = teacherService.getTeacherListByPage(pageInfo);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", teacherService.getTeacherListNum());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "获取教师信息列表总记录数")
    @GetMapping(value = "/getTeacherListNum")
    @ResponseBody
    public Map<String, Object> getTeacherListNum() {
        Object num = teacherService.getTeacherListNum();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", num);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "检测编号是否已存在")
    @GetMapping(value = "/checkTno/{tno}")
    @ResponseBody
    public Map<String, Object> checkSno(@PathVariable(name = "tno") String tno) {
        List<String> list = teacherService.checkTno(tno);
        Map<String, Object> map = new HashMap<>();
        if(list.isEmpty()) {
            map.put("code", 0);
        } else {
            map.put("code", 1);
        }
        map.put("data", "");
        map.put("msg", "检测成功");
        return map;
    }

    @ApiOperation(value = "创建新的教师信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> createTeacher(@RequestBody Teacher teacher) {
        teacherService.createTeacher(teacher);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "创建成功");
        return map;
    }

    @ApiOperation(value = "删除教师信息")
    @GetMapping(value = "/delete/{tno}")
    @ResponseBody
    public Map<String, Object> deleteTeacher(@PathVariable(name = "tno") String tno) {
        teachingService.deleteTeacheringByTno(tno);
        teacherService.deleteTeacher(tno);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "删除成功");
        return map;
    }

    @ApiOperation(value = "模糊搜索教师表")
    @GetMapping(value = "/query/{query}")
    @ResponseBody
    public Map<String, Object> queryTeacher(@PathVariable(name = "query") String query) {
        List<Teacher> list = teacherService.queryTeacher(query);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", list);
        map.put("msg", "查询成功");
        return map;
    }

    @ApiOperation(value = "修改教师信息")
    @PostMapping(value = "/modify")
    @ResponseBody
    public Map<String, Object> modifyTeacher(@RequestBody Teacher teacher) {
        teacherService.modifyTeacher(teacher);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "修改成功");
        return map;
    }
}
