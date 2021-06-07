package xyz.myzsl.myblog.controller;

import cn.hutool.core.map.MapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.myzsl.myblog.bean.dto.ArticleDTO;
import xyz.myzsl.myblog.bean.vo.*;
import xyz.myzsl.myblog.service.ArticlesService;
import xyz.myzsl.myblog.service.impl.ArticlesServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 09:44:58
 */
@Api("与文章相关的api接口")
@RestController
public class ArticleController {

    @Autowired
    private ArticlesService articlesService;

    @ApiOperation("批量获取文章")
    @GetMapping("/articles")
    public Results<PageVO> getArticles(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "5") Integer limit) {
        return Results.ok(articlesService.getArticles(page, limit));
    }

    @PostMapping("/articles")
    @ApiOperation("新增文章")
    public Results postArticles(@ApiParam(name = "文章信息", value = "传入json格式", required = true)
                                @RequestBody @Valid ArticleDTO articleDTO) {
        String id = articlesService.insArticle(articleDTO);
        return Results.ok(MapUtil.of("id", id));
    }

    @GetMapping("/article/{id}")
    @ApiOperation("根据id查询文章信息")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String", paramType = "path")
    public Results<ArticleVO> getArticleByTag(@PathVariable String id) {
        ArticleVO articleVO = articlesService.findById(id);
        return Results.ok(articleVO);
    }

    @DeleteMapping("/article/{id}")
    @ApiOperation("根据id删除文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String", paramType = "path")
    public Results deleteArticle(@PathVariable String id) {
        articlesService.deleteArticle(id);
        return Results.ok("删除成功", null);
    }

    @PutMapping("/article/{id}")
    @ApiOperation("修改文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String", paramType = "path")
    public Results<Map<String, Object>> putArticles(@ApiParam(name = "要修改的文章信息", value = "传入json格式", required = true)
                                                    @RequestBody ArticleDTO articleDTO,
                                                    @PathVariable String id) {
        articlesService.updateArticle(articleDTO, id);
        return Results.ok("更新成功", MapUtil.of("id", id));


    }

    @ApiOperation("获取所有的标签")
    @GetMapping("/tags")
    public Results<Set<String>> getTags() {
        return Results.ok(articlesService.getAllTags());
    }

    @GetMapping("/tag/{name}")
    @ApiOperation("根据标签查询文章集合")
    @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String", paramType = "path")
    public Results<PageVO<ArticleVO>> getArticleByTag(
            @PathVariable("name") String tagName,
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "5") Integer limit) {
        PageVO<ArticleVO> pv = articlesService.getArticleByTag(tagName, page, limit);
        return Results.ok(pv);
    }

    @ApiOperation("获取所有的文章分类以及对应分类文章数")
    @GetMapping("/categories")
    public Results<List<CategoryVO>> getCategories() {
        return Results.ok(articlesService.getAllCategories());
    }

    @GetMapping("/category/{name}")
    @ApiOperation("根据分类查询文章集合")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String", paramType = "path")
    public Results<PageVO<ArticleVO>> getArticleByCate(
            @PathVariable("name") String categoryName,
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "5") Integer limit) {
        PageVO<ArticleVO> pv = articlesService.getArticleByCategory(categoryName, page, limit);
        return Results.ok(pv);
    }

    @GetMapping("/timeline")
    @ApiOperation("获取文章时间线")
    public Results<List<TimelineVO>> getTimeline() {
        List<TimelineVO> timeline = articlesService.timeline();
        return Results.ok(timeline);
    }


}
