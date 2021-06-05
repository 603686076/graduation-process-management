package com.ruoyi.graduation.domain.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;

// 前端查询参数
public class DetermineQuery extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户账号 */
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 教师id */
    private Long teacherId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "DetermineQuery{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }
}
