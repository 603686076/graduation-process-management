package com.ruoyi.graduation.domain.vo;

import com.ruoyi.common.core.annotation.Excel;

public class SelectTeacher {
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户昵称 */
    @Excel(name = "用户名称")
    private String nickName;

    /** 指导学生数 */
    @Excel(name = "指导学生数")
    private Long quantity;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SelectTeacher{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
