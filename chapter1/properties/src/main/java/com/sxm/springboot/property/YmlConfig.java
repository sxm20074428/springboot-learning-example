package com.sxm.springboot.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "yml")
public class YmlConfig {

    private String string;
    private String[] array;
    private List<Map<String, String>> mapList = new ArrayList<>(); //接收prop1里面的属性值
    private List<String> stringList = new ArrayList<>(); //接收prop2里面的属性值
    private Map<String, String> stringMap = new HashMap<>(); //接收prop1里面的属性值

    public String getString() {
        return string;
    }

    //String类型的一定需要setter来接收属性值；maps, collections, 和 arrays 不需要
    public void setString(String string) {
        this.string = string;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
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
}