package com.hihia.controller;

import com.hihia.domain.PageInfo;
import com.hihia.domain.Student;
import com.hihia.service.ClassService;
import com.hihia.service.ScService;
import com.hihia.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "学生信息模块Controller", tags = {"学生信息模块"})
@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private ScService scService;

    @ApiOperation(value = "获取所有学生信息列表")
    @GetMapping(value = "/getStudentList")
    @ResponseBody
    public Map<String, Object> getStudentList() {
        List<Student> list = studentService.getStudentList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", list.size());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "根据分页获取学生信息列表")
    @PostMapping(value = "/getStudentListByPage")
    @ResponseBody
    public Map<String, Object> getStudentListByPage(@RequestBody PageInfo pageInfo) {
        List<Student> list = studentService.getStudentListByPage(pageInfo);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", studentService.getStudentListNum());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "获取学生信息列表总记录数")
    @GetMapping(value = "/getStudentListNum")
    @ResponseBody
    public Map<String, Object> getStudentListNum() {
        Object num = studentService.getStudentListNum();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", num);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "检测学号是否已存在")
    @GetMapping(value = "/checkSno/{sno}")
    @ResponseBody
    public Map<String, Object> checkSno(@PathVariable(name = "sno") String sno) {
        List<String> list = studentService.checkSno(sno);
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

    @ApiOperation(value = "创建新的学生信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        classService.incStudentnumberByClassno(student.getClassno());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "创建成功");
        return map;
    }

    @ApiOperation(value = "修改学生信息")
    @PostMapping(value = "/modify")
    @ResponseBody
    public Map<String, Object> modifyStudent(@RequestBody Student student) {
        String sno = student.getSno();
        String newClassno = student.getClassno();
        Student old = studentService.getStudentBySno(sno);
        String oldClassno = old.getClassno();
        if(!newClassno.equals(oldClassno)) {
            classService.incStudentnumberByClassno(newClassno);
            classService.decStudentnumberByClassno(oldClassno);
        }
        studentService.modifyStudent(student);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "修改成功");
        return map;
    }

    @ApiOperation(value = "删除学生信息")
    @GetMapping(value = "/delete/{sno}")
    @ResponseBody
    public Map<String, Object> deleteStudent(@PathVariable(name = "sno") String sno) {
        Student student = studentService.getStudentBySno(sno);
        String classno = student.getClassno();
        classService.decStudentnumberByClassno(classno);
        scService.deleteRecords(sno);
        studentService.deleteStudent(sno);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "删除成功");
        return map;
    }

    @ApiOperation(value = "模糊搜索学生表")
    @GetMapping(value = "/query/{query}")
    @ResponseBody
    public Map<String, Object> queryStudent(@PathVariable(name = "query") String query) {
        List<Student> list = studentService.queryStudent(query);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", list);
        map.put("msg", "查询成功");
        return map;
    }
}
