package com.sxm.springboot.ui.controller;

import com.google.common.collect.ImmutableMap;
import com.sxm.springboot.ui.common.Rest;
import com.sxm.springboot.ui.common.RestMessage;
import com.sxm.springboot.ui.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "测试1", tags = "测试1")
@RestController
@RequestMapping("/api/v1")
public class Controller1 {

    @PostMapping("/selectLog")
    @ApiOperation(value = "selectLog")
    public RestMessage selectLog(@RequestBody Map<String, Object> infoMap, @RequestAttribute(name = "CURRENTUSERID") int userId) {
        return new RestMessage(infoMap);
    }

    @PostMapping("/select1")
    @ApiOperation(value = "header参数")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "code", value = "code", dataType = "string" ,paramType = "form"),
                    @ApiImplicitParam(name = "headerparam", value = "headerparam", dataType = "string",paramType = "header"),
                    @ApiImplicitParam(name = "page", value = "page", dataType = "int", paramType = "query"),
                    @ApiImplicitParam(name = "page1", value = "page1", dataType = "Long", paramType = "query")
            })
    public RestMessage reqbody3(@RequestHeader(value = "headerparam") String headerparam, @RequestParam(value = "code") String code, @RequestParam(value = "page") int page, @RequestParam(value = "page1") Long page1) {
        return new RestMessage(ImmutableMap.of("code", code, "header", headerparam, "page", page, "page1", page1));
    }

    @DeleteMapping(value = "/xdf/{code}")
    @ApiOperation(value = "path 参数")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "code", dataType = "Long", paramType = "path")})
    public RestMessage urlPath(@PathVariable(value = "code") Long code) {
        return new RestMessage(code);
    }

    @GetMapping("/rest")
    public Rest<User> rest() {
        User user = new User();
        user.setName("张飞");
        user.setTel("13093939102");
        Rest<User> userRest = new Rest<>();
        userRest.setData(user);
        return userRest;
    }

}
