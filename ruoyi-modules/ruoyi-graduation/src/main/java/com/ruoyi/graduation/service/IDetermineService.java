package com.ruoyi.graduation.service;

import com.ruoyi.graduation.domain.Student;
import com.ruoyi.graduation.domain.dto.DetermineQuery;
import com.ruoyi.graduation.domain.vo.StudentTopic;

import java.util.List;

public interface IDetermineService {
    public List<StudentTopic> selectStudentTopic(DetermineQuery determineQuery);

    public int updateStudentTopic(Student student);

    public StudentTopic selectStudentById(Long id);
}
