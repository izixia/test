package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbSeller;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.aisile.sellergoods.service.SellerService;
import com.alibaba.dubbo.config.annotation.Reference;


/**
 * @author admin
 *
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference
	private SellerService sellerService;
	
	//查询所有商品
	@RequestMapping("/findAll")
	public List<TbSeller> findAll(){
		return sellerService.findAll();
	}
	
	//商品分页
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return sellerService.findPage(page, rows);
	}
	
	//添加
	@RequestMapping("/add")
	public Result add(@RequestBody TbSeller seller) {
		try {
			sellerService.add(seller);
			return new Result(true, "保存成功!");
		} catch (Exception e) {
			return new Result(false,"保存失败!");
		}
	}
	
	//商品修改
	@RequestMapping("/update")
	public Result update(@RequestBody TbSeller seller){
		try {
			sellerService.update(seller);
			return new Result(true, "修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败!");
		}
	}
	//商品修改
	@RequestMapping("/updateStatus")
	public Result updateStatus(@RequestBody TbSeller seller,String status){
		try {
			sellerService.updateStatus(seller,status);
			return new Result(true, "修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败!");
		}
	}
	
	//商品修改前根据id查询对象
	@RequestMapping("/findOne")
	public TbSeller findOne(String id){
		return sellerService.findOne(id);		
	}
	
	//商品删除
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			sellerService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	//商品模糊分页
	@RequestMapping("/findSearch")
	public PageResult  findSearch(int page,int rows,@RequestBody TbSeller seller){			
		return sellerService.findSearch(page, rows,seller);
	}
	
	//模板查询
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList(){
		return sellerService.selectOptionList();
	}
	
	
}
