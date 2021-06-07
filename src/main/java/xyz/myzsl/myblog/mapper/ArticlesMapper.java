package xyz.myzsl.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.myzsl.myblog.domain.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ArticlesMapper extends BaseMapper<Article> {

}




