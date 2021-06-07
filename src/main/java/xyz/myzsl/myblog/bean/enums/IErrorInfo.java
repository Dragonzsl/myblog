package xyz.myzsl.myblog.bean.enums;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-04 22:26:46
 */
public interface IErrorInfo {

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMsg();

    /**
     * 获取错误码
     * @return 错误码
     */
    int getCode();
}
