/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.GoodsReviewVo;
import ltd.newbee.mall.controller.vo.NewBeeMallSearchGoodsVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.entity.GoodsDescEntity;
import ltd.newbee.mall.entity.GoodsDetail;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.entity.GoodsInfo;
import ltd.newbee.mall.entity.GoodsQa;
import ltd.newbee.mall.entity.GoodsReview;
import ltd.newbee.mall.entity.GoodsReviewHelpNum;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.Review;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

@Service
public class NewBeeMallGoodsServiceImpl implements NewBeeMallGoodsService {

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Override
    public PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil) {
        List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsList(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoods(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveNewBeeMallGoods(NewBeeMallGoods goods) {
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList) {
        if (!CollectionUtils.isEmpty(newBeeMallGoodsList)) {
            goodsMapper.batchInsert(newBeeMallGoodsList);
        }
    }

    @Override
    public String updateNewBeeMallGoods(NewBeeMallGoods goods) {
        NewBeeMallGoods temp = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        goods.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public NewBeeMallGoods getNewBeeMallGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil) {
        List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoodsBySearch(pageUtil);
        List<NewBeeMallSearchGoodsVO> newBeeMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            newBeeMallSearchGoodsVOS = BeanUtil.copyList(goodsList, NewBeeMallSearchGoodsVO.class);
            for (NewBeeMallSearchGoodsVO newBeeMallSearchGoodsVO : newBeeMallSearchGoodsVOS) {
                String goodsName = newBeeMallSearchGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallSearchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    newBeeMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    newBeeMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(newBeeMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

	@Override
	public List<GoodsImage> getImageList(Long goodsId) {
		List<GoodsImage> imgList = goodsMapper.getImageList(goodsId);
		 return imgList; 
	}
	
	@Override
	public List<Review> getReviewList(String goodsId){
		List<Review> reviewList = goodsMapper.getReviewList(goodsId);
		return reviewList;
	}

	@Override
	public GoodsDescEntity getGoodsDesc(Long goodsId) {
		GoodsDescEntity goodsDesc = goodsMapper.getGoodsDesc(goodsId);
		return goodsDesc;
	}

	@Override
	public int insertQa(GoodsQa qa) {
		int count = goodsMapper.insertQa(qa);
		return count;
	}

	@Override
	public Long getMaxQaId(Long goodsId) {
		Long maxGoodsId = goodsMapper.getMaxQaId(goodsId);
		if(maxGoodsId !=null) {
			return maxGoodsId + 1;
		}else {
			return 1L;
		}
	}

	@Override
	public List<GoodsReviewVo> getGoodsReviews(Long goodsId) {
		List<GoodsReview> entityList = goodsMapper.getGoodsReview(goodsId);
		List<GoodsReviewVo> reviewVoList = BeanUtil.copyList(entityList, GoodsReviewVo.class);
		return reviewVoList;
	}

	@Override
	public boolean addHelpNum(GoodsReviewHelpNum goodsReviewHelpNum) {
		
		return goodsMapper.insertHelpNum(goodsReviewHelpNum);
	}

	@Override
	public boolean updateReviewNum(GoodsReviewHelpNum goodsReviewHelpNum) {
		return goodsMapper.updateReviewNum(goodsReviewHelpNum);
	}

	@Override
	public long getGoodsReviewHelpNum(int reviewId) {
		
		return goodsMapper.getGoodsReviewHelpNum(reviewId);
	}
	
	@Override
	public ArrayList<GoodsDetail> getGoodsDetail(long goodsId) {
		
		return goodsMapper.getGoodsDetail(goodsId);
	}

	@Override
	public GoodsInfo getGoodsInfoByPk(long id) {
		
		return goodsMapper.getGoodsInfoByPK(id);
	}

	@Override
	public ArrayList<GoodsImage> getGoodsImage(long id) {
		
		return goodsMapper.getGoodsImage(id);
	}

	@Override
	public ArrayList<NewBeeMallGoods> getGoodsPage(String keyword, int pageNo) {
		int start = (pageNo-1)*3;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("keyword", keyword);
		paraMap.put("start", start);
		return goodsMapper.selectBygoodsPage(paraMap);
	}
	
	
}
