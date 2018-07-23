

app.controller('brandController',function($scope,brandService,$controller){
	//继承baseController
	$controller('baseController',{$scope:$scope});
	
			$scope.list=[];
			//查询所有信息
			$scope.findAll=function(){
				brandService.findAll.success(
					function(response){
						$scope.list=response;
					}		
				);
			}
			
			//分页获取数据
			$scope.findPage=function(page,rows){
				brandService.findPage(page,rows).success(
						function(response){
							$scope.list=response.rows;
							$scope.paginationConf.totalItems=response.total;
						}
				);
			}
			
			$scope.searchEntity={};
			//模糊分页
			$scope.findSearch=function(page,rows){
				brandService.findSearch(page,rows,$scope.searchEntity).success(
						function(response){
							$scope.list=response.rows;
							$scope.paginationConf.totalItems=response.total;
						}
				);
			}
			
			//添加
			$scope.save=function(){
				var returnPojo = {};//方法名称
				if($scope.entity.id!=null){//如果有ID
					returnPojo=brandService.update($scope.entity);
				}else{
					returnPojo=brandService.add($scope.entity);
				}
				
				 returnPojo.success(
					function(response){
						if(response.success){
							//重新查询 
							 $scope.reloadList();//重新加载
						 }else{
							 alert(response.message);
						 }
					}		
				);				
			}
			
			//修改根据id查询实体对象 
			$scope.findOne=function(id){
				brandService.findOne(id).success(
						function(response){
							$scope.entity= response;					
					     }
				);				
			}
						 
			//批量删除 
			$scope.dele=function(){			
					//获取选中的复选框			
					/* $http.get('../brand/delete.do?ids='+$scope.selectIds).success(
							function(response){
								if(response.success){
										$scope.reloadList();//刷新列表
								}						
							}		
					); */
					swal({
						title : '确定删除吗？',
						text : '你将无法恢复它！',
						type : 'warning',
						showCancelButton : true,
						confirmButtonColor : '#3085d6',
						cancelButtonColor : '#d33',
						confirmButtonText : '确定！',
						cancelButtonText : '取消！',
						confirmButtonClass : 'btn btn-success',
						cancelButtonClass : 'btn btn-danger'
					}).then(function(isConfirm) {
						if (isConfirm.value == true) {
							
							brandService.dele($scope.selectIds).success(
									function(response){
										if(response.success){
												$scope.reloadList();//刷新列表
										}else{
											swall({
												title:'删除失败!',
												text:'至少选中一条数据进行删除.(2秒后自动关闭)',
												timer:2000,
												type:'error'
											});
										}						
									}		
							);
							
							swal('已删除！', '信息已经被删除。')
						}else{
							swal('删除失败！', '请稍后再试。', 'error');
					}
					})	
					
			}
			
			
			
			
			
			
			
			
})