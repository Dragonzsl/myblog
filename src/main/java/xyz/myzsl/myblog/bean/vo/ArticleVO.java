package xyz.myzsl.myblog.bean.vo;

import cn.hutool.core.bean.*;
import cn.hutool.core.bean.copier.*;
import lombok.Data;
import xyz.myzsl.myblog.bean.enums.ArticleTypeEnum;
import xyz.myzsl.myblog.domain.Article;
import xyz.myzsl.myblog.utils.BlogUtils;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 09:24:44
 */

@Data
public class ArticleVO {

    private String id;
    private String author;
    private String title;
    private String content;
    private String[] tags;
    private String type;
    private String category;
    private String gmtCreate;
    private String gmtUpdate;
    private String tabloid;
    private Integer views;

    /**
     * 根据 PO 创建 VO 对象
     *
     * @param article PO对象
     * @return VO对象
     */
    public static ArticleVO fromArticlePO(Article article) {
        return new Converter().convertToVO(article);
    }

    private static class Converter implements IConverter<Article, ArticleVO> {
        @Override
        public ArticleVO convertToVO(Article article) {
            final ArticleVO vo = new ArticleVO();
            BeanUtil.copyProperties(article, vo, CopyOptions.create()
                    .ignoreNullValue().ignoreError());
            vo.setTags(article.getTags().split(","));
            for (ArticleTypeEnum item : ArticleTypeEnum.values()) {
                if (item.getFlag() == article.getType()) {
                    vo.setType(item.getNotes());
                }
            }
            vo.setGmtCreate(BlogUtils.formatDatetime(article.getGmtCreate()));
            vo.setGmtUpdate(BlogUtils.formatDatetime(article.getGmtUpdate()));
            return vo;
        }

        @Override
        public ArticleVO convertToPO(Article article) {
            return null;
        }
    }
}
