package org.planeswalker.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 发送 email 的实体类
 * @author Planeswalker23
 * @date Created in 2020/2/7
 */
@Data
public class EmailDto {

    /**
     * 针对哪篇文章发送邮件
     */
    @NotEmpty(message = "参数缺失")
    private String commentId;

    private String sender;

    private String accepter;

    private String title;

    private String content;
}
