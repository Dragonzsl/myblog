package xyz.myzsl.myblog.bean.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 09:36:09
 */
@Data
@Builder
public class PageVO<T> {
    protected List<T> records;
    protected long total;
    protected long size;
    protected long current;
}
