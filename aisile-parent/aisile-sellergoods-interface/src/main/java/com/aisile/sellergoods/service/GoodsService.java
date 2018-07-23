package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbGoods;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Goods;
import com.aisile.pojo.group.Specification;


public interface GoodsService {
	
	//使用组合类进行添加,添加方法
	public void add(Goods goods);
	
	//查询全部规格的方法
	public List<TbGoods> findAll();
	
	//分页方法
	public PageResult findPage(int pageNum,int pageSize);
	
	//模糊分页方法
	public PageResult findSearch(int pageNum,int pageSize,TbGoods goods);
	
	//修改方法
	public void update(TbGoods goods);
		
	//根据id获取实体对象
	public TbGoods findOne(Long id);
		
	//批量删除
	public boolean delete(Long[] ids);
	
	//下拉列表使用查询
	public List<Map> selectOptionList();

	//根据父类id查询数据
	public List<TbGoods> findAllByParentId(Long parentid);
}
