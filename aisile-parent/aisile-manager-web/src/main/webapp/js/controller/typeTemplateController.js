

app.controller('typeTemplateController',function($scope,typeTemplateService,brandService,specificationService,$controller){
	//继承baseController
	$controller('baseController',{$scope:$scope});
	
			$scope.list=[];
			//查询所有信息
			$scope.findAll=function(){
				typeTemplateService.findAll.success(
					function(response){
						$scope.list=response;
					}		
				);
			}
			
			//增加行
			$scope.addTableRow=function(){
				//增加一行
				$scope.entity.customAttributeItems.push({});
			}
			
			//减少一行
			$scope.deleTableRow=function(index){
				//删除一行
				//splice方法有两个参数,第一个是从谁(索引/下标)开始删除,第二个是删除几个
				$scope.entity.customAttributeItems.splice(index,1);
			}
			
			//品牌下拉列表
			$scope.brandList={data:[]};
			$scope.findBrandList=function(){
				brandService.selectOptionList().success(function(response){
					$scope.brandList={data:response};
				});	
			};
			//规格明细下拉列表
			$scope.specList={data:[]};
			$scope.findSpecList=function(){
				specificationService.selectOptionList().success(function(response){
					$scope.specList={data:response};
				});
			}
			
			
			//分页获取数据
			$scope.findPage=function(page,rows){
				typeTemplateService.findPage(page,rows).success(
						function(response){
							$scope.list=response.rows;
							$scope.paginationConf.totalItems=response.total;
						}
				);
			}
			
			$scope.searchEntity={};
			//模糊分页
			$scope.findSearch=function(page,rows){
				typeTemplateService.findSearch(page,rows,$scope.searchEntity).success(
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
					returnPojo=typeTemplateService.update($scope.entity);
				}else{
					returnPojo=typeTemplateService.add($scope.entity);
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
				typeTemplateService.findOne(id).success(
						function(response){
							$scope.entity= response;
							$scope.entity.brandIds=  JSON.parse($scope.entity.brandIds);//转换品牌列表
							$scope.entity.specIds=  JSON.parse($scope.entity.specIds);//转换规格列表
							$scope.entity.customAttributeItems= JSON.parse($scope.entity.customAttributeItems);//转换扩展属性
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
							
							typeTemplateService.dele($scope.selectIds).success(
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