/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.dao;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ltd.newbee.mall.entity.Answer;
import ltd.newbee.mall.entity.GoodsDescEntity;
import ltd.newbee.mall.entity.GoodsDetail;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsInfo;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsReviewHelpNum;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.Review;
import ltd.newbee.mall.entity.StockNumDTO;
import ltd.newbee.mall.util.PageQueryUtil;

public interface NewBeeMallGoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(NewBeeMallGoods record);

    int insertSelective(NewBeeMallGoods record);

    NewBeeMallGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(NewBeeMallGoods record);

    int updateByPrimaryKeyWithBLOBs(NewBeeMallGoods record);

    int updateByPrimaryKey(NewBeeMallGoods record);

    
    List<NewBeeMallGoods> findNewBeeMallGoodsList(PageQueryUtil pageUtil);
    int getTotalNewBeeMallGoods(PageQueryUtil pageUtil);

    List<NewBeeMallGoods> selectByPrimaryKeys(List<Long> goodsIds);

    List<NewBeeMallGoods> findNewBeeMallGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoodsBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("newBeeMallGoodsList") List<NewBeeMallGoods> newBeeMallGoodsList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds,@Param("sellStatus") int sellStatus);

    // added by ka 2021/02/07 add second category id search.
    List<NewBeeMallGoods> searchGoodsBySecCategoryId(PageQueryUtil pageUtil);
    // added by ka 2021/04/16 イメージリストを取得
    List<GoodsImage> getImageList(Long goodsId);
    // added by ka 2021/04/16　レビューリストを取得
    List<Review> getReviewList(String goodsId);
    
    GoodsDescEntity getGoodsDesc(Long goodsId);
    // added by ka 2021/04/29 insert qa
    int insertQa(GoodsQa qa);
    // get max qa id
    Long getMaxQaId(Long goodsId);
    
    //get goods review
    List<GoodsReview> getGoodsReview(Long goodsId);
    
    //insert HelpNum
    boolean insertHelpNum(GoodsReviewHelpNum goodsReviewHelpNum);
    
    // update review help num
    boolean updateReviewNum(GoodsReviewHelpNum goodsReviewHelpNum);
    
    long getGoodsReviewHelpNum(int reviewId);
    
    ArrayList<GoodsDetail> getGoodsDetail(long goodsId);
    
    GoodsInfo getGoodsInfoByPK(long id); //ctrl shift o
    
    ArrayList<GoodsImage> getGoodsImage(long id);
    
    ArrayList<NewBeeMallGoods> selectBygoodsPage(Map<String,Object> map);
    
    Answer getAnswerById(long answerId); //ctrl shift o
}