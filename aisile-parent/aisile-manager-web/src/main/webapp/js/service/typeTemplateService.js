//封装所有连接后端的代码,需要$http
app.service('typeTemplateService',function($http){
		    	//读取列表数据绑定到表单中
				//查询全部
		    	this.findAll=function(){
		    		return $http.get('../typeTemplate/findAll.do');		
		    	}
		    	//分页查询
		    	this.findPage=function(page,rows){
		    		return $http.get('../typeTemplate/findPage.do?page='+page+'&rows='+rows);		
		    	}
		    	//模糊分页查询
		    	this.findSearch=function(page,rows,searchEntity){
		    		return $http.post('../typeTemplate/findSearch.do?page='+page+'&rows='+rows,searchEntity);		
		    	}
		    	//修改
		    	this.update=function(entity){
		    		return $http.post('../typeTemplate/update.do',entity);		
		    	}
		    	//添加
		    	this.add=function(entity){
		    		return $http.post('../typeTemplate/add.do',entity);		
		    	}
		    	//根据id查询实体对象
		    	this.findOne=function(id){
		    		return $http.get('../typeTemplate/findOne.do?id='+id);		
		    	}
		    	//删除方法
		    	this.dele=function(selectIds){
		    		return $http.get('../typeTemplate/delete.do?ids='+selectIds);		
		    	}
		    	
}); 