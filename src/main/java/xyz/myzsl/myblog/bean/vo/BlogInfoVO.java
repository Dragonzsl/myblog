package xyz.myzsl.myblog.bean.vo;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import xyz.myzsl.myblog.domain.BlogSetting;

import java.util.List;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 11:10:24
 */
@Data
public class BlogInfoVO {
    private String title;
    private String desc;
    private List<String> covers;
    private String avatar;
    private String nickname;
    private int articleCount;
    private int totalViews;

    public static BlogInfoVO fromBlogSetting(BlogSetting blogSetting) {
        return new BlogInfoVO.Converter().convertToVO(blogSetting);
    }

    private static class Converter implements IConverter<BlogSetting, BlogInfoVO> {
        @Override
        public BlogInfoVO convertToVO(BlogSetting blogSetting) {
            BlogInfoVO vo = new BlogInfoVO();
            BeanUtil.copyProperties(blogSetting, vo);
            return vo;
        }

        @Override
        public BlogInfoVO convertToPO(BlogSetting blogSetting) {
            return null;
        }
    }

}
