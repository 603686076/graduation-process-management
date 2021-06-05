package com.ruoyi.graduation.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

// 确定学生课题前端展示实体类
public class StudentTopic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学生ID */
    private Long id;

    /** 学生账号 */
    private String userName;

    /** 学生昵称 */
    private String nickName;

    /** 课设题目 */
    private String topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "StudentTopic{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
