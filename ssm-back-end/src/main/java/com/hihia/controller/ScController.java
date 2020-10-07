package com.hihia.controller;

import com.hihia.domain.Sc;
import com.hihia.service.ScService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "选课模块Controller", tags = {"选课模块"})
@RestController
@RequestMapping("/sc")
@CrossOrigin
public class ScController {

    @Autowired
    ScService scService;

    @ApiOperation(value = "获取所有学生选课信息列表")
    @GetMapping(value = "/getStudentScList")
    @ResponseBody
    public Map<String, Object> getStudentScList() {
        List<Sc> list = scService.getStudentScList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", list.size());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }
}
