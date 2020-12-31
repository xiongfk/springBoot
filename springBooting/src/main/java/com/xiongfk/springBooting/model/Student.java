package com.xiongfk.springBooting.model;

import com.xiongfk.springBooting.customAnnotation.CheckParam;

@CheckParam(msg = "学生信息")
public class Student {
    private Integer sid;

    private String stuName;

    private Integer age;

    private String sex;

    private String phone;

    private String email;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
    @CheckParam(msg = "学生姓名")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }
    @CheckParam(msg = "学生年龄")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @CheckParam(msg = "学生性别")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
    @CheckParam(msg = "手机号")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
    @CheckParam(msg = "学生邮箱")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}