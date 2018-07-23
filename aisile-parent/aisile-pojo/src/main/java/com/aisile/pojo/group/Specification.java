package com.aisile.pojo.group;

import java.io.Serializable;
import java.util.List;

import com.aisile.pojo.TbSpecification;
import com.aisile.pojo.TbSpecificationOption;

/**
*规格和规格明细实体类
*使用doubox,webservice..传对象的时候都需要序列化Serializable
*/
public class Specification implements Serializable{
	private TbSpecification specification;
	private List<TbSpecificationOption> specificationOptionList;
	
	public TbSpecification getSpecification() {
		return specification;
	}
	public void setSpecification(TbSpecification specification) {
		this.specification = specification;
	}
	public List<TbSpecificationOption> getSpecificationOptionList() {
		return specificationOptionList;
	}
	public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
		this.specificationOptionList = specificationOptionList;
	}
	
	
}
