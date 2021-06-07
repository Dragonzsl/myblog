package xyz.myzsl.myblog.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import xyz.myzsl.myblog.bean.enums.IErrorInfo;
import xyz.myzsl.myblog.utils.BlogUtils;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-04 22:22:42
 */
@Data
@Accessors(chain = true)
@Builder
@ApiModel("通用接口返回对象")

public class Results<T> {
    @ApiModelProperty(required = true, notes = "结果码", example = "0")
    private int code;
    @ApiModelProperty(required = true, notes = "返回信息", example = "操作成功")
    private String msg;
    @ApiModelProperty(required = true, notes = "返回数据", example = "{\"id\":2001}")
    private T data;
    @ApiModelProperty(required = true, notes = "时间戳", example = "2020-06-29 09:07:34")
    private String timestamp;

    public static <T> Results<T> ok(T data) {
        return Results.<T>builder()
                .msg("操作成功")
                .data(data)
                .timestamp(BlogUtils.now())
                .build();
    }

    public static Results fromErrorInfo(IErrorInfo errorInfo) {
        return Results.builder()
                .code(errorInfo.getCode())
                .msg(errorInfo.getMsg())
                .timestamp(BlogUtils.now())
                .build();
    }

    public static <T> Results<T> ok(String msg, T data) {
        return Results.<T>builder()
                .msg(msg)
                .data(data)
                .timestamp(BlogUtils.now())
                .build();
    }

    public static <T> Results<T> error(T data) {
        return Results.<T>builder()
                .code(5000)
                .msg("操作失败")
                .data(data)
                .timestamp(BlogUtils.now())
                .build();
    }

    public static <T> Results<T> error(String msg, T data) {
        return Results.<T>builder()
                .code(5000)
                .msg(msg)
                .data(data)
                .timestamp(BlogUtils.now())
                .build();
    }
}
