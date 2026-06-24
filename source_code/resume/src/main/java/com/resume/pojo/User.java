package com.resume.pojo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Heber
 * @version 1.0
 */
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    //用户名
    private String username;

    @Column(nullable = false, length = 100)
    //密码
    private String password;

    @Column(length = 50)
    //姓名
    private String name;
    //年龄
    private int age;

    @Column(length = 50)
    //性别
    private String sex;

    @Column(length = 100)
    //职位
    private String career;

    @Column(length = 100)
    //学校
    private String school;

    @Column(length = 20)
    //电话
    private String phone;

    @Column(length = 20)
    private String qq;

    @Column(length = 100)
    //邮箱
    private String email;

    @Column(length = 200)
    //地址
    private String address;

    @Column(length = 50)
    //学历
    private String education;

    @Column(length = 200)
    //专业
    private String speciality;

    @Column(length = 5000)
    //技能
    private String skill;

    //工作年限
    private int seniority;

    @Column(length = 2000)
    //工作一
    private String workone;

    @Column(length = 2000)
    //工作二
    private String worktwo;
    //项目经验一
    @Column(length = 2000)
    private String workthree;

    @Column(length = 2000)
    //项目经验二
    private String workfour;

    @Column(length = 200)
    //博客网站地址
    private String blog;

    //毕业时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getCareer() {
        return career;
    }

    public String getSchool() {
        return school;
    }

    public String getPhone() {
        return phone;
    }

    public String getQq() {
        return qq;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getEducation() {
        return education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getSkill() {
        return skill;
    }

    public int getSeniority() {
        return seniority;
    }

    public String getWorkone() {
        return workone;
    }

    public String getWorktwo() {
        return worktwo;
    }

    public String getWorkthree() {
        return workthree;
    }

    public String getWorkfour() {
        return workfour;
    }

    public String getBlog() {
        return blog;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public void setWorkone(String workone) {
        this.workone = workone;
    }

    public void setWorktwo(String worktwo) {
        this.worktwo = worktwo;
    }

    public void setWorkthree(String workthree) {
        this.workthree = workthree;
    }

    public void setWorkfour(String workfour) {
        this.workfour = workfour;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", career='" + career + '\'' +
                ", school='" + school + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", education='" + education + '\'' +
                ", speciality='" + speciality + '\'' +
                ", skill='" + skill + '\'' +
                ", seniority=" + seniority +
                ", workone='" + workone + '\'' +
                ", worktwo='" + worktwo + '\'' +
                ", workthree='" + workthree + '\'' +
                ", workfour='" + workfour + '\'' +
                ", blog='" + blog + '\'' +
                ", date=" + date +
                '}';
    }
}

