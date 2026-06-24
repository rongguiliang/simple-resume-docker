package com.resume.mapper;

import com.resume.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Heber
 * @version 1.0
 * 用户登录查询
 */
@Mapper
public interface UserMapper {
    User selectByUsernameAndPassword(String username, String password);

    @Update("UPDATE user SET name=#{name}, age=#{age}, sex=#{sex}, career=#{career}, " +
            "school=#{school}, phone=#{phone}, qq=#{qq}, email=#{email}, address=#{address}, " +
            "education=#{education}, speciality=#{speciality}, skill=#{skill}, " +
            "seniority=#{seniority}, workone=#{workone}, worktwo=#{worktwo}, " +
            "workthree=#{workthree}, workfour=#{workfour}, blog=#{blog}, date=#{date}, " +
            "password=#{password} WHERE id=#{id}")
    void updateUser(User user);
}
