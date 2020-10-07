package com.hihia.controller;

import com.hihia.domain.Teaching;
import com.hihia.service.TeachingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "课程安排模块Controller", tags = {"课程安排模块"})
@RestController
@RequestMapping("/teaching")
@CrossOrigin
public class TeachingController {

    @Autowired
    TeachingService teachingService;

    @ApiOperation(value = "获取所有课程安排信息列表")
    @GetMapping(value = "/getTeachingList")
    @ResponseBody
    public Map<String, Object> getTeachingList() {
        List<Teaching> list = teachingService.getTeachingList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", list.size());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "获取所有授课语言列表")
    @GetMapping(value = "/getTeachingLanguageList")
    @ResponseBody
    public Map<String, Object> getTeachingLanguageList() {
        List<String> list = teachingService.getTeachingLanguageList();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", list);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "获取所有授课课程列表")
    @GetMapping(value = "/getTeachingCourseList")
    @ResponseBody
    public Map<String, Object> getTeachingCourseList() {
        List<String> list = teachingService.getTeachingCourseList();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", list);
        map.put("msg", "获取成功");
        return map;
    }
}
