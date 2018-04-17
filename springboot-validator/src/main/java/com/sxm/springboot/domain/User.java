package com.sxm.springboot.domain;

import com.sxm.springboot.validation.custom.basic.UniqueLogin;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    @Min(value = 1, message = "{user.id.error}")
    private int id;

    @Email(message = "用户名必须是邮箱")
    private String userName;

    @UniqueLogin
    private String name;

    @NotBlank
    @Length(min = 6, max = 36)
    private String password;

    private Integer age;

    //By default Jackson uses reflection to set values of fields and requires no-argument constructor to be declared for a class.
    // You can make the constructor private to maintain the interface of your class unpolluted for public use and to keep Jackson working correctly
    private User() {

    }

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User(int id, String userName, String name, String password) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userName);
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
