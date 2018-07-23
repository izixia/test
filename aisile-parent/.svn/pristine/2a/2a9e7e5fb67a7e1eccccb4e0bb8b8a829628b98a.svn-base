

app.controller('ItemCatController',function($scope,ItemCatService,brandService,$controller){
	//继承baseController
	$controller('baseController',{$scope:$scope});
	
	//模板下拉列表
	/*$scope.typeTemplateList=[];
	$scope.typeTemplateList=function(){
		brandService.findAll.success(function(response){
			$scope.typeTemplateList=response;
		});	
	};*/
	
	
			$scope.list=[];
			$scope.getParentIdW=0;
			$scope.grade=1;//默认级别
			$scope.setGrade=function(value){//设置用户级别
				$scope.grade=value;
				
			}
			
			
			$scope.findAllList=function(p_entity){
				if($scope.grade==1){
					$scope.entity_1=null;
					$scope.entity_2=null;
				}
				if($scope.grade==2){
					$scope.entity_1=p_entity;
					$scope.entity_2=null;
				}
				if($scope.grade==3){
					$scope.entity_2=p_entity;
				}
				$scope.findAllByParentId(p_entity.id);
			}
			
			//根据父类id查询所有信息
			$scope.findAllByParentId=function(parentid){
				$scope.getParentIdW=parentid;
				ItemCatService.findAllByParentId(parentid).success(
					function(response){
						$scope.list=response;
					}		
				);
			}
			
			
			//查询所有信息
			$scope.findAll=function(){
				ItemCatService.findAll.success(
						function(response){
							$scope.list=response;
						}		
				);
			}
			
			
			//增加行
			$scope.addTableRow=function(){
				//增加一行
				$scope.entity.specificationOptionList.push({});
			}
			
			//减少一行
			$scope.deleTableRow=function(index){
				//删除一行
				//splice方法有两个参数,第一个是从谁(索引/下标)开始删除,第二个是删除几个
				$scope.entity.specificationOptionList.splice(index,1);
			}
			
			//分页获取数据
			$scope.findPage=function(page,rows){
				ItemCatService.findPage(page,rows).success(
						function(response){
							$scope.list=response.rows;
							$scope.paginationConf.totalItems=response.total;
						}
				);
			}
			
			$scope.searchEntity={};
			//模糊分页
			$scope.findSearch=function(page,rows){
				ItemCatService.findSearch(page,rows,$scope.searchEntity).success(
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
					returnPojo=ItemCatService.update($scope.entity);
				}else{
					$scope.entity.parentId=$scope.getParentIdW;//赋予上级ID
					returnPojo=ItemCatService.add($scope.entity);
				}
				
				 returnPojo.success(
					function(response){
						if(response.success){
							//重新查询 
							$scope.findAllByParentId($scope.getParentIdW);//重新加载
							 $scope.reloadList();//重新加载
						 }else{
							 alert(response.message);
						 }
					}		
				);				
			}
			
			//修改根据id查询实体对象 
			$scope.findOne=function(id){
				ItemCatService.findOne(id).success(
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
							
							ItemCatService.dele($scope.selectIds).success(
									function(response){
										if(response.success){
												$scope.findAllByParentId($scope.getParentIdW);//刷新列表
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