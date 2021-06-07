package xyz.myzsl.myblog.domain;

import cn.hutool.setting.Setting;
import lombok.Data;

import java.util.List;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 11:02:14
 */
@Data
public class BlogSetting {

    private String title;
    private String desc;
    private List<String> covers;
    private String avatar;
    private String nickname;

    public static BlogSetting fromSetting(Setting setting){
        return (BlogSetting) setting.toBean(new BlogSetting());
    }
}
