package xyz.myzsl.myblog.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.setting.Setting;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.myzsl.myblog.bean.vo.BlogInfoVO;
import xyz.myzsl.myblog.domain.Article;
import xyz.myzsl.myblog.domain.BlogSetting;
import xyz.myzsl.myblog.mapper.ArticlesMapper;
import xyz.myzsl.myblog.service.CommService;

import java.util.List;
import java.util.Map;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 11:07:15
 */
@Service
public class CommServiceImpl implements CommService {

    @Autowired
    private ArticlesMapper articleMapper;
    @Autowired
    private Setting setting;

    public BlogInfoVO getBlogInfo() {
        BlogSetting blogSetting = BlogSetting.fromSetting(setting);
        BlogInfoVO vo = BlogInfoVO.fromBlogSetting(blogSetting);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select("sum(views) as total_views");
        List<Map<String, Object>> maps = articleMapper.selectMaps(wrapper);
        int totalViews = 0;
        if (!maps.isEmpty()) {
            totalViews = Convert.toInt(maps.get(0).get("total_views"), 0);
        }
        Integer articleCount = articleMapper.selectCount(null);
        vo.setTotalViews(totalViews);
        vo.setArticleCount(articleCount);
        return vo;
    }
}
