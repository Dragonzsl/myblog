package xyz.myzsl.myblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.myzsl.myblog.bean.enums.impl.ErrorInfoEnum;
import xyz.myzsl.myblog.bean.vo.BlogInfoVO;
import xyz.myzsl.myblog.bean.vo.Results;
import xyz.myzsl.myblog.exception.BlogException;
import xyz.myzsl.myblog.service.CommService;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-04 22:29:38
 */

@Api(tags = "通用接口")
@RestController
public class CommController {

    @Autowired
    private CommService commService;

    @ApiOperation(value = "检查服务端是否正常")
    @GetMapping("/ping")
    public Results ping() {
//        return Results.ok("欢迎访问QBlog API", null);
        throw new BlogException(ErrorInfoEnum.MISSING_PARAMETERS);
    }

    @ApiOperation("获取博客信息")
    @GetMapping("/getInfo")
    public Results<BlogInfoVO> info() {
        BlogInfoVO blogInfo = commService.getBlogInfo();
        return Results.ok(blogInfo);
    }

}
