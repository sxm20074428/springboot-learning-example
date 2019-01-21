package com.sxm.springboot.ui.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sxm.springboot.ui.common.RestMessage;
import com.sxm.springboot.ui.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(value = "测试2", tags = "测试2")
@RestController
@RequestMapping("/api/v2")
public class Controller2 {

    @ApiOperation(value = "puttest测试说明")
    @PutMapping(value = "/puttest")
    @ApiImplicitParam(value = "code", name = "code", dataType = "string", paramType = "query")
    public RestMessage put(String code) {
        return new RestMessage(code);
    }

    @ApiOperation(value = "deletetest测试说明")
    @DeleteMapping(value = "/deletetest")
    @ApiImplicitParam(value = "code", name = "code", dataType = "string", paramType = "query")
    public RestMessage delete(@RequestParam(value = "code") String code) {
        return new RestMessage(code);
    }

    @PostMapping("/reqbody")
    @ApiOperation(value = "RequestBody接口类型", notes = "RequestBody测试接口：实体类型")
    public RestMessage reqbody(@RequestBody User user) {
        return new RestMessage(user);
    }

    @PostMapping("/reqbody1")
    @ApiOperation(value = "ModelAttribute", notes = "ModelAttribute类型参数")
    public RestMessage reqbody1(@ModelAttribute User user) {
        return new RestMessage(user);
    }

    @PostMapping("/reqbody2")
    @ApiOperation(value = "RequestBody接口类型2", notes = "RequestBody测试接口：string类型")
    public RestMessage reqbody2(@RequestBody String user) {
        return new RestMessage(user);
    }

    @PostMapping("/reqbody3")
    @ApiOperation(value = "header参数")
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "code", name = "code", dataType = "string", paramType = "query"),
                    @ApiImplicitParam(value = "headerParam", name = "headerParam", dataType = "string", paramType = "header"),
                    @ApiImplicitParam(value = "page", name = "page", dataType = "int", paramType = "query"),
                    @ApiImplicitParam(value = "page1", name = "page1", dataType = "Long", paramType = "query")
            })
    public RestMessage reqbody3(@RequestHeader(value = "headerParam") String headerParam,
                                @RequestParam(value = "code") String code,
                                @RequestParam(value = "page") int page,
                                @RequestParam(value = "page1") Long page1) {
        return new RestMessage(ImmutableMap.of("code", code, "header", headerParam, "page", page, "page1", page1));
    }


    @ApiOperation(value = "文件素材上传接口")
    @ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true, dataType = "MultipartFile")
    @RequestMapping(value = "/uploadMaterial", method = RequestMethod.POST)
    @ResponseBody
    public RestMessage uploadMaterial(@RequestParam(value = "file") MultipartFile[] files, HttpServletRequest request) throws IOException {
        //int mul=1*1024*1024;
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File realFile = new File(realPath);
        if (!realFile.exists()) {
            realFile.mkdirs();
        }

        List<Map> uploadFiles = Lists.newArrayList();
        System.out.println("进入图片上传接口:" + files.length + "张");
        for (MultipartFile file : files) {
            File targetFile = new File(realFile, file.getOriginalFilename());

            try (FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
                int i;
                byte[] bytes = new byte[1024 * 1024];
                try (InputStream ins = file.getInputStream()) {
                    while ((i = ins.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, i);
                    }
                }
            } catch (IOException e) {

            }

            Map fileInfo = Maps.newHashMap();
            fileInfo.put("id", UUID.randomUUID().toString());
            fileInfo.put("url", targetFile.getPath());
            fileInfo.put("original_name", targetFile.getName());
            uploadFiles.add(fileInfo);
        }
        RestMessage restMessage = new RestMessage();
        restMessage.setData(uploadFiles);
        return restMessage;
    }


}
