package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Specification;


public interface SpecificationService {
	//查询全部规格的方法
	public List<TbSpecification> findAll();
	
	//分页方法
	public PageResult findPage(int pageNum,int pageSize);
	
	//模糊分页方法
	public PageResult findSearch(int pageNum,int pageSize,TbSpecification tbSpecification);
	
	//添加方法
	public void add(Specification specification);
		
	//修改方法
	public void update(Specification specification);
		
	//根据id获取实体对象
	public Specification findOne(Long id);
		
	//批量删除
	public void delete(Long [] ids);
	
	//下拉列表使用查询
	public List<Map> selectOptionList();
}
