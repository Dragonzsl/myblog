package xyz.myzsl.myblog.service;

import xyz.myzsl.myblog.bean.dto.ArticleDTO;
import xyz.myzsl.myblog.bean.vo.ArticleVO;
import xyz.myzsl.myblog.bean.vo.CategoryVO;
import xyz.myzsl.myblog.bean.vo.PageVO;
import xyz.myzsl.myblog.bean.vo.TimelineVO;
import xyz.myzsl.myblog.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface ArticlesService extends IService<Article> {

    PageVO<ArticleVO> getArticles(int page, int limit);

    String insArticle(ArticleDTO articleDTO);

    ArticleVO findById(String id);

    void deleteArticle(String id);

    void updateArticle(ArticleDTO articleDTO, String id);

    Set<String> getAllTags();

    PageVO<ArticleVO> getArticleByTag(String tagName, Integer page, Integer limit);

    List<CategoryVO> getAllCategories();

    PageVO<ArticleVO> getArticleByCategory(String categoryName, Integer page, Integer limit);

    List<TimelineVO> timeline();
}
