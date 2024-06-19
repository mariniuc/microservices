package com.coursesservice.controller;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coursesservice.entity.Course;

@RestController
@RequestMapping("/course")
public class CourseController {

  private  ElasticsearchOperations elasticsearchOperations;

  public CourseController(ElasticsearchOperations elasticsearchOperations) { 
    this.elasticsearchOperations = elasticsearchOperations;
  }

  @PostMapping
  public String save(@RequestBody Course course) {                         
    Course savedEntity = elasticsearchOperations.save(course);
    return savedEntity.getId();
  }

  @GetMapping("/{id}")
  public Course findById(@PathVariable("id")  String id) {                   
    Course course = elasticsearchOperations.get(id.toString(), Course.class);
    return course;
  }
}