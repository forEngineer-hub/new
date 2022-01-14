package ltd.newbee.mall.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ltd.newbee.mall.dao.GoodsImageMapper;
import ltd.newbee.mall.entity.GoodsImage;
import ltd.newbee.mall.service.GoodsImageService;

@Service
public class GoodsImageServiceImpl implements GoodsImageService {
	@Autowired
    private GoodsImageMapper goodsImageMapper;
	
	@Override
	public ArrayList<GoodsImage> getGoodsImages(Long goodsId) {
		
		return goodsImageMapper.getImagesByGoodsId(goodsId);
	}

}
