package org.planeswalker.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.mapper.GoodsMapper;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品接口 控制层
 * @author Planeswalker23
 * @date Created in 2020/2/8
 */

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询所有商品（分页）
     * @param goods
     * @param pageMessage
     * @return {@link Response}
     */
    @GetMapping("/all")
    public Response<PageInfo<Goods>> getGoods(Goods goods, PageMessage pageMessage) {
        // 设置分页信息
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        List<Goods> goodsList = goodsMapper.selectList(Wrappers.lambdaQuery(goods));
        return Response.success(new PageInfo<>(goodsList));
    }
}
