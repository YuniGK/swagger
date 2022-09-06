package com.example.swagger.controller;

import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/*
@Api - 클래스를 Swagger 리소스 대상으로 표시
@ApiOperation - 요청 URL 에 매핑된 API 에 대한 설명
@ApiParam - 요청 Parameter에 대한 설명 및 필수여부, 예제값 설정
@ApiResponse - 응답에 대한 설명
*/
@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    /*
    @GetMapping("/plus/{x}")
    public int plus(
            @ApiParam(value = "x값", defaultValue = "10")
            @PathVariable int x,
            @ApiParam(value = "y값", defaultValue = "5")
            @RequestParam int y){
        return x+y;
    }
    */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x값", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "y값", required = true, dataType = "int", paramType = "query"),
    })
    @GetMapping("/plus/{x}")
    public int plus( @PathVariable int x, @RequestParam int y){
        return x+y;
    }

    @GetMapping("/user")
    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일때")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메소드")
    public UserRes user(UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }

    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq req){
        return new UserRes(req.getName(), req.getAge());
    }

}
