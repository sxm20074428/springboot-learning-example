package com.sxm.springboot.ui.domain;
import io.swagger.annotations.ApiModelProperty;

public class User {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "电话号码",example = "555")
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
