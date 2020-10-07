package com.hihia.controller;
import com.hihia.domain.Class;
import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.service.ClassService;
import com.hihia.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "班级信息模块Controller", tags = {"班级信息模块"})
@RestController
@RequestMapping("/class")
@CrossOrigin
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "获取所有班级信息列表")
    @GetMapping(value = "/getClassList")
    @ResponseBody
    public Map<String, Object> getClassList() {
        List<Class> list = classService.getClassList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", list.size());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "根据分页获取班级信息列表")
    @PostMapping(value = "/getClassListByPage")
    @ResponseBody
    public Map<String, Object> getClassListByPage(@RequestBody PageInfo pageInfo) {
        List<Class> list = classService.getClassListByPage(pageInfo);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", classService.getClassListNum());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "获取班级信息列表总记录数")
    @GetMapping(value = "/getClassListNum")
    @ResponseBody
    public Map<String, Object> getClassListNum() {
        Object num = classService.getClassListNum();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", num);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "获取班级编号列表")
    @GetMapping(value = "/getClassnoList")
    @ResponseBody
    public Map<String, Object> getClassnoList() {
        List<String> list = classService.getClassnoList();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", list);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "创建新的班级信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> createClass(@RequestBody Class newClass) {
        classService.createClass(newClass);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "创建成功");
        return map;
    }

    @ApiOperation(value = "检测班级编号是否已存在")
    @GetMapping(value = "/checkClassno/{classno}")
    @ResponseBody
    public Map<String, Object> checkSno(@PathVariable(name = "classno") String classno) {
        List<String> list = classService.checkClassno(classno);
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

//    @ApiOperation(value = "删除班级信息")
//    @GetMapping(value = "/delete/{classno}")
//    @ResponseBody
//    public Map<String, Object> deleteClass(@PathVariable(name = "classno") String classno) {
//        studentService.deleteClassRelations(classno);
//        classService.deleteClass(classno);
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 0);
//        map.put("data", "");
//        map.put("msg", "删除成功");
//        return map;
//    }
}
