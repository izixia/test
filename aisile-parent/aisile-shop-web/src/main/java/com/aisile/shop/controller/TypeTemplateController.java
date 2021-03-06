package com.aisile.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbTypeTemplate;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.aisile.sellergoods.service.TypeTemplateService;
import com.alibaba.dubbo.config.annotation.Reference;


@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
	
	@Reference
	private TypeTemplateService typeTemplateService;
	
	//查询所有的
	@RequestMapping("/findAll")
	public List<TbTypeTemplate> findAll(){
		return typeTemplateService.findAll();
	}
	
	//分页
	@RequestMapping("/findPage")
	public PageResult findPage(int page,int rows){
		return typeTemplateService.findPage(page, rows);
	}
	
	//模糊分页
	@RequestMapping("/findSearch")
	public PageResult findSearch(int page,int rows,@RequestBody TbTypeTemplate typeTemplate){
		return typeTemplateService.findSearch(page, rows, typeTemplate);
	}
	
	//添加
		@RequestMapping("/add")
		public Result add(@RequestBody TbTypeTemplate typeTemplate) {
			try {
				typeTemplateService.add(typeTemplate);
				return new Result(true, "保存成功!");
			} catch (Exception e) {
				return new Result(false,"保存失败!");
			}
		}
		
		//商品修改
		@RequestMapping("/update")
		public Result update(@RequestBody TbTypeTemplate typeTemplate){
			try {
				typeTemplateService.update(typeTemplate);
				return new Result(true, "修改成功!");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "修改失败!");
			}
		}
		
		//商品修改前根据id查询对象
		@RequestMapping("/findOne")
		public TbTypeTemplate findOne(Long id){
			return typeTemplateService.findOne(id);		
		}
		
		//商品删除
		@RequestMapping("/delete")
		public Result delete(Long [] ids){
			try {
				typeTemplateService.delete(ids);
				return new Result(true, "删除成功"); 
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "删除失败");
			}
		}
		
		@RequestMapping("/findOptionsList")
		public List<Map> findOptionsList(Long id){
			return typeTemplateService.findOptionsList(id);
		}
}
