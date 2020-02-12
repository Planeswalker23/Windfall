package org.planeswalker.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.utils.JacksonUtil;

import java.io.Serializable;

/**
 * 评测表
 * @author Planeswalker23
 * @date Created in 2020/2/3
 */
@Data
@NoArgsConstructor
public class RootCommentInfo extends Comment implements Serializable {

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
