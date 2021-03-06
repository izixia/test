package com.aisile.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.group.Specification;

public interface TypeTemplateService {
	//查询全部规格的方法
	public List<TbTypeTemplate> findAll();
	
	//分页方法
	public PageResult findPage(int pageNum,int pageSize);
	
	//模糊分页方法
	public PageResult findSearch(int pageNum,int pageSize,TbTypeTemplate tbTypeTemplate);
	
	//添加方法
	public void add(TbTypeTemplate tbTypeTemplate);
		
	//修改方法
	public void update(TbTypeTemplate tbTypeTemplate);
		
	//根据id获取实体对象
	public TbTypeTemplate findOne(Long id);
		
	//批量删除
	public void delete(Long [] ids);
	
	//查询规格列表方法
	public List<Map> findOptionsList(Long id);
	
	
}
