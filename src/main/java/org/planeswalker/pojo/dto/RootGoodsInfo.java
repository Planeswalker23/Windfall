package org.planeswalker.pojo.dto;

import lombok.Data;
import org.planeswalker.pojo.entity.Goods;
import org.planeswalker.utils.JacksonUtil;

import java.io.Serializable;

/**
 * @author Planeswalker23
 * @date Created in 2020/2/10
 */
@Data
public class RootGoodsInfo extends Goods implements Serializable {

    /**
     * 返回到前端的序号
     */
    private Integer no;

    /**
     * 创建时间格式
     * @return
     */
    public String getCreateTimeString() {
        return JacksonUtil.date2String(super.getCreateTime());
    }
}