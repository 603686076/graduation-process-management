package com.ruoyi.graduation.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.graduation.domain.Student;
import com.ruoyi.graduation.domain.dto.DetermineQuery;
import com.ruoyi.graduation.domain.vo.StudentTopic;
import com.ruoyi.graduation.mapper.DetermineMapper;
import com.ruoyi.graduation.service.IDetermineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetermineServiceImpl implements IDetermineService {
    @Autowired
    private DetermineMapper determineMapper;

    @Override
    public List<StudentTopic> selectStudentTopic(DetermineQuery determineQuery) {
        return determineMapper.selectStudentTopic(determineQuery);
    }

    @Override
    public int updateStudentTopic(Student student) {
        student.setUpdateTime(DateUtils.getNowDate());
        return determineMapper.updateStudentTopic(student);
    }

    @Override
    public StudentTopic selectStudentById(Long id) {
        return determineMapper.selectStudentById(id);
    }
}
