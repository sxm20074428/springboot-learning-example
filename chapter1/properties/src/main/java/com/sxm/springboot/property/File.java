package com.sxm.springboot.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件属性
 */
@Component
public class File {

    @Value("${filePathLocation:d:/data/myFiles}")
    private String filePathLocation;

    @Override
    public String toString() {
        return "File{" +
                "filePathLocation='" + filePathLocation + '\'' +
                '}';
    }
}
