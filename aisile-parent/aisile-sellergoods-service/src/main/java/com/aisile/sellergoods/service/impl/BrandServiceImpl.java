package com.aisile.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile.mapper.TbBrandMapper;
import com.aisile.pojo.TbBrand;
import com.aisile.pojo.TbBrandExample;
import com.aisile.pojo.TbBrandExample.Criteria;
import com.aisile.pojo.entity.PageResult;
import com.aisile.sellergoods.service.BrandService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private TbBrandMapper tbBrandMapper; 
	
	@Override    // 形参填null是查询全部
	public List<TbBrand> findAll() {
		return tbBrandMapper.selectByExample(null);
	}

	@Override   // 分页
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbBrand> pageinfo = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
		return new PageResult(pageinfo.getTotal(),pageinfo.getResult());
	}

	@Override   //添加
	public void add(TbBrand brand) {
		tbBrandMapper.insert(brand);
	}

	@Override   //修改
	public void update(TbBrand brand) {
		tbBrandMapper.updateByPrimaryKey(brand);
	}

	@Override   //根据id查询对象
	public TbBrand findOne(Long id) {
		return tbBrandMapper.selectByPrimaryKey(id);
	}

	@Override   //批删
	public void delete(Long[] ids) {
		for (Long id : ids) {
			tbBrandMapper.deleteByPrimaryKey(id);
		}
	}

	@Override    //模糊分页
	public PageResult findSearch(int pageNum, int pageSize, TbBrand brand) {
		PageHelper.startPage(pageNum, pageSize);	//分页	
		TbBrandExample example=new TbBrandExample();  //条件
		Criteria criteria = example.createCriteria(); //开始拼接条件 		
			if(brand.getName()!=null && !brand.getName().equals("")){
				criteria.andNameLike("%"+brand.getName()+"%");//名称
			}
			if(brand.getFirstChar()!=null && !brand.getFirstChar().equals("")){
				criteria.andFirstCharEqualTo(brand.getFirstChar());//首字母
			}		
		Page<TbBrand> page= (Page<TbBrand>)tbBrandMapper.selectByExample(example);	
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override   //模板查询
	public List<Map> selectOptionList() {
		return tbBrandMapper.selectOptionList();
	}

	
	

}
