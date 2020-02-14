package org.planeswalker.pojo.dto;

import lombok.Data;
import org.planeswalker.base.Constant;

/**
 * 各类别文章的数目
 * @author Planeswalker23
 * @date Created in 2020/2/7
 */
@Data
public class TypeNum {

    private Integer type;

    private Integer num;

    private String typeName;

    private String typeCode;

    public TypeNum(Integer type) {
        this.type = type;
        this.num = Constant.ZERO;
    }

    public String getTypeName() {
        if (Constant.ONE.equals(this.type)) {
            return "历史文化";
        } else if (Constant.TWO.equals(this.type)) {
            return "活动资讯";
        } else if (Constant.THREE.equals(this.type)) {
            return "美食特色";
        } else if (Constant.FOUR.equals(this.type)) {
            return "风物文化";
        } else {
            return null;
        }
    }

    public String getTypeCode() {
        if (Constant.ONE.equals(this.type)) {
            return "cultural";
        } else if (Constant.TWO.equals(this.type)) {
            return "news";
        } else if (Constant.THREE.equals(this.type)) {
            return "food";
        } else if (Constant.FOUR.equals(this.type)) {
            return "natural";
        } else {
            return null;
        }
    }
}
