package com.sxm.springboot.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PropertySource注解只可以加载proprties文件,无法加载yml文件
 * abc.properties文件不存在,验证ignoreResourceNotFound属性
 * 加上encoding = "utf-8"属性防止中文乱码,不能为大写的"UTF-8"
 */
@Component
//@PropertySource(value = {"classpath:/config/propConfigs.properties","classpath:/config/abc.properties"},ignoreResourceNotFound = true,encoding = "utf-8")
@PropertySource(value = "classpath:/config/propConfigs.properties")
@ConfigurationProperties(prefix = "prop")
public class PropConfig {

    private String name;

    private String[] array;

    // 集合必须初始化，如果找不到就是空集合，会报错
    private List<String> stringList = new ArrayList<>(); //接收prop里面的属性值

    private Map<String, String> stringMap = new HashMap<>();

    private List<Map<String, String>> mapList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }
}
