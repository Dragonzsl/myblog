package xyz.myzsl.myblog.bean.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-05 10:45:14
 */

@Data
@Builder
public class CategoryVO {
    private String name;
    private String count;

    public static CategoryVO fromMap(Map<String, Object> map) {
        return new CategoryVO.Converter().convertToVO(map);
    }

    private static class Converter implements IConverter<Map<String,Object>, CategoryVO> {
        @Override
        public CategoryVO convertToVO(Map<String, Object> map) {
            CategoryVO vo = CategoryVO.builder()
                    .name(String.valueOf(map.get("name")))
                    .count(String.valueOf(map.get("count")))
                    .build();
            return vo;
        }

        @Override
        public CategoryVO convertToPO(Map<String, Object> stringObjectMap) {
            return null;
        }
    }
}
