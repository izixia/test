package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbSpecificationOptionMapper;
import com.aisile.mapper.TbTypeTemplateMapper;
import com.aisile.pojo.TbSpecificationOption;
import com.aisile.pojo.TbSpecificationOptionExample;
import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.TbTypeTemplateExample;
import com.aisile.pojo.entity.PageResult;
import com.aisile.sellergoods.service.TypeTemplateService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

	@Autowired
	private TbTypeTemplateMapper tbTypeTemplateMapper;
	
	@Autowired
	private TbSpecificationOptionMapper  tbSpecificationOptionMapper;
	
	@Override   //查询所有的信息
	public List<TbTypeTemplate> findAll() {
		return null;
	}

	@Override   
	public PageResult findPage(int pageNum, int pageSize) {
		return null;
	}

	@Override    
	public PageResult findSearch(int pageNum, int pageSize, TbTypeTemplate tbTypeTemplate) {
		PageHelper.startPage(pageNum,pageSize);//分页
		TbTypeTemplateExample example=new TbTypeTemplateExample();//条件
		com.aisile.pojo.TbTypeTemplateExample.Criteria criteria = example.createCriteria();//开始拼接条件
		if(tbTypeTemplate.getName()!=null && !tbTypeTemplate.getName().equals("")){
			criteria.andNameLike("%"+tbTypeTemplate.getName()+"%");//规格名称
		}
		Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override   //添加方法
	public void add(TbTypeTemplate tbTypeTemplate) {
		tbTypeTemplateMapper.insert(tbTypeTemplate);
	}

	@Override   //修改方法
	public void update(TbTypeTemplate tbTypeTemplate) {
		tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);
	}

	@Override   //根据id查询一个实体对象
	public TbTypeTemplate findOne(Long id) {
		return tbTypeTemplateMapper.selectByPrimaryKey(id);
	}

	@Override   //删除
	public void delete(Long[] ids) {
		for (Long long1 : ids) {
			tbTypeTemplateMapper.deleteByPrimaryKey(long1);
		}
	}

	@Override   //返回规格列表
	public List<Map> findOptionsList(Long id) {
		TbTypeTemplate template = tbTypeTemplateMapper.selectByPrimaryKey(id);
		List<Map> list = JSON.parseArray(template.getSpecIds(), Map.class);
		for (Map map : list) {
			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			example.createCriteria().andSpecIdNotEqualTo(new Long( (Integer)map.get("id")));
			List<TbSpecificationOption> list2 = tbSpecificationOptionMapper.selectByExample(example);
			map.put("options", list2);
		}
		return list;
	}
	
	
}
