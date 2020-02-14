package org.planeswalker.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.planeswalker.base.Response;
import org.planeswalker.mapper.CommentMapper;
import org.planeswalker.mapper.GoodsMapper;
import org.planeswalker.pojo.dto.GoodsDetail;
import org.planeswalker.pojo.dto.PageMessage;
import org.planeswalker.pojo.entity.Comment;
import org.planeswalker.pojo.entity.Goods;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private CommentMapper commentMapper;

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

    /**
     * 查询商品详情+该商品的评测
     * @param goodsId
     * @return {@link Response}
     */
    @GetMapping("/detail")
    public Response<GoodsDetail> getGoodsDetail(String goodsId) {
        Goods goods = goodsMapper.selectById(goodsId);
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("goods_id", goodsId);
        List<Comment> comments = commentMapper.selectByMap(condition);
        GoodsDetail goodsDetail = new GoodsDetail();
        BeanUtils.copyProperties(goods, goodsDetail);
        goodsDetail.setComments(comments);
        return Response.success(goodsDetail);
    }

    /**
     * 商品搜索接口
     * @param keyword
     * @param pageMessage
     * @return
     */
    @GetMapping("/search")
    public Response<PageInfo<Goods>> searchGoods(String keyword, PageMessage pageMessage) {
        PageHelper.startPage(pageMessage.getPageNum(), pageMessage.getPageSize());
        // 模糊匹配 goods 的类型、名称、品牌、型号、需求
        List<Goods> goodsList = goodsMapper.selectList(Wrappers.<Goods>lambdaQuery()
                // 搜索已启用的商品
                .eq(Goods::getState, Constant.ONE)
                .like(Goods::getGoodsName, keyword)
                .or().like(Goods::getBrand, keyword)
                .or().like(Goods::getType, keyword)
                .or().like(Goods::getSet, keyword)
                .or().like(Goods::getRequirement, keyword));
        return Response.success(new PageInfo<>(goodsList));
    }
}
