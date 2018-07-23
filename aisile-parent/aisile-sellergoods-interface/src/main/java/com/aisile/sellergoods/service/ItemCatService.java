package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Specification;


public interface ItemCatService {
	//查询全部规格的方法
	public List<TbItemCat> findAll();
	
	//分页方法
	public PageResult findPage(int pageNum,int pageSize);
	
	//模糊分页方法
	public PageResult findSearch(int pageNum,int pageSize,TbItemCat itemCat);
	
	//添加方法
	public void add(TbItemCat itemCat);
		
	//修改方法
	public void update(TbItemCat itemCat);
		
	//根据id获取实体对象
	public TbItemCat findOne(Long id);
		
	//批量删除
	public boolean delete(Long ids);
	
	//下拉列表使用查询
	public List<Map> selectOptionList();

	//根据父类id查询数据
	public List<TbItemCat> findAllByParentId(Long parentid);
}
