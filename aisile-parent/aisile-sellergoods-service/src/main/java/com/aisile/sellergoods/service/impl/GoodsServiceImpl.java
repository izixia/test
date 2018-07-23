package com.aisile.sellergoods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbBrandMapper;
import com.aisile.mapper.TbGoodsDescMapper;
import com.aisile.mapper.TbGoodsMapper;
import com.aisile.mapper.TbItemCatMapper;
import com.aisile.mapper.TbItemMapper;
import com.aisile.mapper.TbSellerMapper;
import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbItem;
import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Goods;
import com.aisile.sellergoods.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;


@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper tbGoodsMapper;
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbGoodsDescMapper tbGoodsDescMapper;
	
	@Autowired
	private TbBrandMapper brandMapper;
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private TbSellerMapper sellerMapper;
	
	@Override  // 添加商品
	public void add(Goods goods) {	
		//第一步保存SPU
		goods.getGoods().setAuditStatus("0");//未审核状态
		tbGoodsMapper.insert(goods.getGoods());	
		//可以获得goods插入的id了
		//第二步desc表
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
		tbGoodsDescMapper.insert(goods.getGoodsDesc());
		//第三步SKU
		List<TbItem> list=goods.getItemList();
		for (TbItem tbItem : list) {
			//需要哪些属性
			
			//商品标题 根据规格拼接
			String title=goods.getGoods().getGoodsName();
			Map<String,Object> specMap=JSON.parseObject(tbItem.getSpec());
			for (String maps : specMap.keySet()) {
				title+=specMap.get(maps)+" ";
			}
			tbItem.setTitle(title);//标题
			tbItem.setCategoryid(goods.getGoods().getCategory3Id());//商品分类编号
			tbItem.setCreateTime(new Date());//添加时间
			tbItem.setUpdateTime(new Date());//修改时间
			tbItem.setGoodsId(goods.getGoods().getId());//spuid
			TbSeller seller=sellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());//获得商家对象
			tbItem.setSellerId(seller.getSellerId());//商家店铺id
			tbItem.setSeller(seller.getNickName());//商家店铺id
			tbItem.setBrand(brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId()).getName());//品牌名称
			tbItem.setCategory(itemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id()).getName());//分类名称
			//加载图片
			List<Map> imgs=JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class);
			if(imgs!=null){
				if(imgs.size()>0){
					tbItem.setImage((String)imgs.get(0).get("url"));//图片路径
				}
			}
			itemMapper.insert(tbItem);
			
		}
		
	}

	@Override
	public List<TbGoods> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findSearch(int pageNum, int pageSize, TbGoods goods) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(TbGoods goods) {
		// TODO Auto-generated method stub

	}

	@Override
	public TbGoods findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long[] ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbGoods> findAllByParentId(Long parentid) {
		// TODO Auto-generated method stub
		return null;
	}

}
