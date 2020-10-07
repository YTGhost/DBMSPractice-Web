package com.hihia.controller;

import com.hihia.domain.Class;
import com.hihia.domain.Course;
import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.service.CourseService;
import com.hihia.service.ScService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "课程信息模块Controller", tags = {"课程信息模块"})
@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScService scService;

    @ApiOperation(value = "获取所有课程信息列表")
    @GetMapping(value = "/getCourseList")
    @ResponseBody
    public Map<String, Object> getTeacherList() {
        List<Course> list = courseService.getCourseList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", list.size());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "根据分页获取课程信息列表")
    @PostMapping(value = "/getCourseListByPage")
    @ResponseBody
    public Map<String, Object> getCourseListByPage(@RequestBody PageInfo pageInfo) {
        List<Course> list = courseService.getCourseListByPage(pageInfo);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", courseService.getCourseListNum());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "获取课程信息列表总记录数")
    @GetMapping(value = "/getCourseListNum")
    @ResponseBody
    public Map<String, Object> getTeacherListNum() {
        Object num = courseService.getCourseListNum();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", num);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "创建新的课程信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "创建成功");
        return map;
    }

    @ApiOperation(value = "检测课程编号是否已存在")
    @GetMapping(value = "/checkCno/{cno}")
    @ResponseBody
    public Map<String, Object> checkCno(@PathVariable(name = "cno") String cno) {
        List<String> list = courseService.checkCno(cno);
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

    @ApiOperation(value = "删除课程信息")
    @GetMapping(value = "/delete/{cno}")
    @ResponseBody
    public Map<String, Object> deleteCourse(@PathVariable(name = "cno") String cno) {
        scService.deleteScBycno(cno);
        courseService.deleteCourse(cno);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "删除成功");
        return map;
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping(value = "/modify")
    @ResponseBody
    public Map<String, Object> modifyCourse(@RequestBody Course course) {
        courseService.modifyCourse(course);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "修改成功");
        return map;
    }
}
