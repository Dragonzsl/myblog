package xyz.myzsl.myblog.bean.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 15:29:48
 */
@Data
@Builder
public class TimelineVO {

    private String year;
    private List<Item> items;

    @Data
    @Builder
    public static class Item {
        private String id;
        private String gmtCreate;
        private String title;
    }
}
