package com.aisile.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisile.pojo.TbItemCat;
import com.aisile.pojo.entity.PageResult;
import com.aisile.pojo.entity.Result;
import com.aisile.sellergoods.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;


/**
 * @author admin
 *
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;
	
	//根据父类id查询数据
		@RequestMapping("/findAllByParentId")
		public List<TbItemCat> findAllByParentId(Long parentid){
			return itemCatService.findAllByParentId(parentid);
		}
	
	
	//查询所有商品
	@RequestMapping("/findAll")
	public List<TbItemCat> findAll(){
		return itemCatService.findAll();
	}
	
	
	
	//商品分页
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return itemCatService.findPage(page, rows);
	}
	
	//添加
	@RequestMapping("/add")
	public Result add(@RequestBody TbItemCat itemCat) {
		try {
			itemCatService.add(itemCat);
			return new Result(true, "保存成功!");
		} catch (Exception e) {
			return new Result(false,"保存失败!");
		}
	}
	
	//商品修改
	@RequestMapping("/update")
	public Result update(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.update(itemCat);
			return new Result(true, "修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败!");
		}
	}
	
	
	//商品修改前根据id查询对象
	@RequestMapping("/findOne")
	public TbItemCat findOne(Long id){
		return itemCatService.findOne(id);		
	}
	
	//商品删除
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		/*try {
			for (Long long1 : ids) {
					itemCatService.delete(long1);
			}
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}*/
				for (Long long1 : ids) {
					boolean delete = itemCatService.delete(long1);
					if(delete){
						return new Result(true, "删除成功"); 
					}else{
						return new Result(false, "删除失败"); 
					}
				}
				return new Result(false, "删除失败"); 
	}
	
	//商品模糊分页
	@RequestMapping("/findSearch")
	public PageResult  findSearch(int page,int rows,@RequestBody TbItemCat itemCat){			
		return itemCatService.findSearch(page, rows,itemCat);
	}
	
	//模板查询
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList(){
		return itemCatService.selectOptionList();
	}
	
	
}
