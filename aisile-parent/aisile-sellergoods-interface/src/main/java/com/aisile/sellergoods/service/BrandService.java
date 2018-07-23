package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbBrand;
import com.aisile.pojo.entity.PageResult;


public interface BrandService {
	
	//查询全部商品的方法
	public List<TbBrand> findAll();
	
	//分页
	public PageResult findPage(int pageNum,int pageSize);
	
	//模糊分页
	public PageResult findSearch(int pageNum,int pageSize,TbBrand brand);
	
	//添加方法
	public void add(TbBrand brand);
	
	//修改方法
	public void update(TbBrand brand);
	
	//根据id获取实体对象
	public TbBrand findOne(Long id);
	
	//批量删除
	public void delete(Long [] ids);
	
	//查询模板
	public List<Map> selectOptionList();
	
}