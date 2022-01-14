package ltd.newbee.mall.service;

import java.util.ArrayList;

import ltd.newbee.mall.entity.GoodsImage;

public interface GoodsImageService {
	public  ArrayList<GoodsImage> getGoodsImages(Long goodsId);
}
