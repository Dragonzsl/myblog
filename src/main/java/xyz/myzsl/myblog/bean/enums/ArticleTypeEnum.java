package xyz.myzsl.myblog.bean.enums;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 09:35:23
 */
public enum ArticleTypeEnum {

    /**
     * 文章类型：原创
     */
    ORIGINAL("原创", 1),
    /**
     * 文章类型：转载
     */
    REPRINT("转载", 0);;
    private String notes;
    private int flag;

    public String getNotes() {
        return notes;
    }

    public int getFlag() {
        return flag;
    }

    ArticleTypeEnum(String notes, int flag) {
        this.notes = notes;
        this.flag = flag;
    }
}
