package xyz.myzsl.myblog.exception;

import xyz.myzsl.myblog.bean.enums.IErrorInfo;
import xyz.myzsl.myblog.bean.vo.Results;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 08:49:50
 */
public class BlogException extends RuntimeException {
    private final IErrorInfo errorInfo;

    public BlogException(IErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 将异常转换为 ResultVO 对象返回给前端
     *
     * @return 封装了异常信息的 ResultVO 对象
     */
    public Results toResultVO() {
        return Results.fromErrorInfo(errorInfo);
    }
}
