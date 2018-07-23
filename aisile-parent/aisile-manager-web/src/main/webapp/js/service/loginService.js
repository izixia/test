//封装所有连接后端的代码,需要$http
app.service('loginService',function($http){
		    	//读取列表数据绑定到表单中
				//查询全部
		    	this.loginName=function(){
		    		return $http.get('../login/name.do');		
		    	}
		   
}); 