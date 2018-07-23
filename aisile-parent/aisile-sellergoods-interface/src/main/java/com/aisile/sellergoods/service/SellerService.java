package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.PageResult;


public interface SellerService {
	
	//查询全部商品的方法
	public List<TbSeller> findAll();
	
	//分页
	public PageResult findPage(int pageNum,int pageSize);
	
	//模糊分页
	public PageResult findSearch(int pageNum,int pageSize,TbSeller seller);
	
	//添加方法
	public void add(TbSeller seller);
	
	//修改方法
	public void update(TbSeller seller);
	//审核
	public void updateStatus(TbSeller seller,String status);
	
	//根据id获取实体对象
	public TbSeller findOne(String id);
	
	//批量删除
	public void delete(Long [] ids);
	
	//查询模板
	public List<Map> selectOptionList();
	
}
