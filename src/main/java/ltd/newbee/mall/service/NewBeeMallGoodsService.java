/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service;

import java.util.List;

import ltd.newbee.mall.controller.vo.GoodsReviewVo;
import ltd.newbee.mall.entity.GoodsDescEntity;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReviewHelpNum;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.Review;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

public interface NewBeeMallGoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 批量新增商品数据
     *
     * @param newBeeMallGoodsList
     * @return
     */
    void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    NewBeeMallGoods getNewBeeMallGoodsById(Long id);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil);
    
    /**
     * 商品画像リスト
     * 2021/04/16
     * @param goods@Override
	Id
     * @return List<GoodsImage>
     * @author foren
     */
    List<GoodsImage> getImageList(Long goodsId);
    
    // added by ka 2021/04/16　レビューリストを取得
    List<Review> getReviewList(String goodsId);
    
    GoodsDescEntity getGoodsDesc(Long goodsId);
    
    //added by ka 2021/04/29 QA挿入サービス
    int insertQa(GoodsQa qa);
    
    Long getMaxQaId(Long goodsId);
    
    //get goods review list
    
    List<GoodsReviewVo> getGoodsReviews(Long goodsId);

	boolean addHelpNum(GoodsReviewHelpNum goodsReviewHelpNum);
	
    boolean updateReviewNum(GoodsReviewHelpNum goodsReviewHelpNum);
    
    long getGoodsReviewHelpNum(int reviewId);

	PageResult getHitGoodsPage(PageQueryUtil pageUtil);
}
