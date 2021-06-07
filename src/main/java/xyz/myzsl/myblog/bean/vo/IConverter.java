package xyz.myzsl.myblog.bean.vo;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 09:23:13
 */
public interface IConverter<T,E> {

    /**
     * VO 转换函数
     *
     * @param t 目标对象
     * @return 转换结果
     */
    E convertToVO(T t);

    /**
     * 将对应的 DTO 转换为 PO
     * @param t 需要转换的 DTO 类
     * @return 转换结果
     */
    E convertToPO(T t);
}
