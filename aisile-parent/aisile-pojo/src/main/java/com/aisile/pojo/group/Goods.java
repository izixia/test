package com.aisile.pojo.group;

import java.io.Serializable;
import java.util.List;

import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbGoodsDesc;
import com.aisile.pojo.TbItem;


public class Goods implements Serializable{
	
	private TbGoods goods;   //SPU商品表
	
	private TbGoodsDesc goodsDesc; //SPU desc描述
	
	private List<TbItem> itemList; //SKU列表  单个具体商品,一种商品,包括多个单个具体商品
	public TbGoods getGoods() {
		return goods;
	}
	public void setGoods(TbGoods goods) {
		this.goods = goods;
	}
	public TbGoodsDesc getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(TbGoodsDesc goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public List<TbItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<TbItem> itemList) {
		this.itemList = itemList;
	}
	
	
	
}
