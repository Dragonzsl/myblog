package xyz.myzsl.myblog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.myzsl.myblog.bean.dto.ArticleDTO;
import xyz.myzsl.myblog.bean.enums.impl.ErrorInfoEnum;
import xyz.myzsl.myblog.bean.vo.ArticleVO;
import xyz.myzsl.myblog.bean.vo.CategoryVO;
import xyz.myzsl.myblog.bean.vo.PageVO;
import xyz.myzsl.myblog.bean.vo.TimelineVO;
import xyz.myzsl.myblog.domain.Article;
import xyz.myzsl.myblog.exception.BlogException;
import xyz.myzsl.myblog.service.ArticlesService;
import xyz.myzsl.myblog.mapper.ArticlesMapper;
import org.springframework.stereotype.Service;
import xyz.myzsl.myblog.utils.BlogUtils;

import java.util.*;
import java.util.stream.Collectors;

import static xyz.myzsl.myblog.bean.enums.impl.ErrorInfoEnum.INVALID_ID;

/**
 *
 */
@Service
@Slf4j
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Article>
        implements ArticlesService {

    @Autowired
    private ArticlesMapper articlesMapper;

    public PageVO<ArticleVO> getArticles(int page, int limit) {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.select(Article.class, i -> !"content".equals(i.getColumn()));
        Page<Article> res = articlesMapper.selectPage(new Page<>(page, limit), qw);
        List<ArticleVO> articleVOS = res.getRecords().stream()
                .map(ArticleVO::fromArticlePO)
                .collect(Collectors.toList());
        PageVO<ArticleVO> pageVO = PageVO.<ArticleVO>builder()
                .records(articleVOS.isEmpty() ? new ArrayList<>() : articleVOS)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
    }

    public String insArticle(ArticleDTO articleDTO) {
        Article po = articleDTO.toArticlePO(false);
        String id = IdUtil.objectId();
        po.setId(id);
        articlesMapper.insert(po);
        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    public ArticleVO findById(String id) {
        Article article = articlesMapper.selectById(id);
        if (Objects.isNull(article)) {
            throw new BlogException(INVALID_ID);
        }
        article.setViews(article.getViews() + 1);
        articlesMapper.updateById(article);
        return ArticleVO.fromArticlePO(article);
    }

    public void deleteArticle(String id) {
        int i = articlesMapper.deleteById(id);
        if (i <= 0) {
            throw new BlogException(INVALID_ID);
        }
    }

    public void updateArticle(ArticleDTO articleDTO, String id) {
        Article dbArticle = articlesMapper.selectById(id);
        if (Objects.isNull(dbArticle)) {
            throw new BlogException(INVALID_ID);
        }
        Article article = articleDTO.toArticlePO(true);
        article.setId(id);
        articlesMapper.updateById(article);
    }

    /***
     * 查询所有标签
     * @return 标签集合
     */
    public Set<String> getAllTags() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select("tags");
        List<Article> articles = articlesMapper.selectList(wrapper);
        Set<String> tags = articles.stream()
                .map(Article::getTags)
                .flatMap(s -> Arrays.stream(s.split(",")))
                .collect(Collectors.toSet());
        log.info("tags : {}", tags);
        return tags.isEmpty() ? new HashSet<>() : tags;
    }

    public PageVO<ArticleVO> getArticleByTag(String tagName, Integer page, Integer limit) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select(Article.class, i -> !"content".equals(i.getColumn()))
                .like("tags", tagName);
        Page<Article> res = articlesMapper.selectPage(new Page<>(page, limit), wrapper);
        List<ArticleVO> articleVOS = res.getRecords().stream()
                .map(ArticleVO::fromArticlePO)
                .collect(Collectors.toList());
        return PageVO.<ArticleVO>builder()
                .records(articleVOS.isEmpty() ? new ArrayList<>() : articleVOS)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
    }

    public List<CategoryVO> getAllCategories() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select("category as `name`", "count(1) as `count`").groupBy("category");
        List<CategoryVO> list = articlesMapper.selectMaps(wrapper).stream()
                .map(CategoryVO::fromMap)
                .collect(Collectors.toList());
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    public PageVO<ArticleVO> getArticleByCategory(String categoryName, Integer page, Integer limit) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select(Article.class, i -> !"content".equals(i.getColumn()))
                .eq("category", categoryName);
        Page<Article> res = articlesMapper.selectPage(new Page<>(page, limit), wrapper);
        List<ArticleVO> articleVOS = res.getRecords().stream()
                .map(ArticleVO::fromArticlePO)
                .collect(Collectors.toList());
        return PageVO.<ArticleVO>builder()
                .records(articleVOS.isEmpty() ? new ArrayList<>() : articleVOS)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
    }

    public List<TimelineVO> timeline() {
        ArrayList<TimelineVO> res = new ArrayList<>();
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select("id", "title", "gmt_create");
        List<Map<String, Object>> maps = articlesMapper.selectMaps(wrapper);
        maps.stream().map(m -> TimelineVO.Item.builder()
                .id((String) m.get("id"))
                .gmtCreate(BlogUtils.formatDate((Long) m.get("gmt_create")))
                .title((String) m.get("title"))
                .build())
                .collect(Collectors.groupingBy(item -> {
                    String[] arr = item.getGmtCreate().split("-");
                    String year = arr[0];
                    return year;
                })).forEach((k, v) -> res.add(TimelineVO.builder().year(k).items(v).build()));
        res.sort(Comparator.comparing(TimelineVO::getYear).reversed());
        // log.info("timeline list : {}", JSONUtil.toJsonStr(res));
        return res;
    }



}




