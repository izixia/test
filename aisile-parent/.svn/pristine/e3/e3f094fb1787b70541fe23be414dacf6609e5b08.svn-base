//封装所有连接后端的代码,需要$http
app.service('brandService',function($http){
		    	//读取列表数据绑定到表单中
				//查询全部
		    	this.findAll=function(){
		    		return $http.get('../brand/findAll.do');		
		    	}
		    	//分页查询
		    	this.findPage=function(page,rows){
		    		return $http.get('../brand/findPage.do?page='+page+'&rows='+rows);		
		    	}
		    	//模糊分页查询
		    	this.findSearch=function(page,rows,searchEntity){
		    		return $http.post('../brand/findSearch.do?page='+page+'&rows='+rows,searchEntity);		
		    	}
		    	//修改
		    	this.update=function(entity){
		    		return $http.post('../brand/update.do',entity);		
		    	}
		    	//添加
		    	this.add=function(entity){
		    		return $http.post('../brand/add.do',entity);		
		    	}
		    	//根据id查询实体对象
		    	this.findOne=function(id){
		    		return $http.get('../brand/findOne.do?id='+id);		
		    	}
		    	//删除方法
		    	this.dele=function(selectIds){
		    		return $http.get('../brand/delete.do?ids='+selectIds);		
		    	}
		    	//下拉模板查询
		    	this.selectOptionList=function(){
		    		return $http.get('../brand/selectOptionList.do');		
		    	}
		    	
}); 