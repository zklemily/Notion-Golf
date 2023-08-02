package com.zkl.courseinfoservice.controller;

import com.zkl.courseinfoservice.golfdata.model.GolfCourse;
import com.zkl.courseinfoservice.golfdata.service.GolfDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/golf-course")
public class GolfDataController {
    private final GolfDataService golfDataService;

    public GolfDataController(GolfDataService golfDataService) {
        this.golfDataService = golfDataService;
    }

    @GetMapping("/{name}")
    public List<GolfCourse> getGolfCourses(@PathVariable("name") String name) throws IOException, InterruptedException {
        return golfDataService.getGolfCourses(name);
    }
}
