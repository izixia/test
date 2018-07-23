

app.controller('goodsController',function($scope,goodsService,uploadService,itemCatService,typeTemplateService,$controller){
	//继承baseController
	$controller('baseController',{$scope:$scope});
	
	//定义初始数组
	$scope.entity={goodsDesc:{itemImages:[],specificationItems:[]}};
			$scope.list=[];
			//查询所有信息
			$scope.findAll=function(){
				goodsService.findAll.success(
					function(response){
						$scope.list=response;
					}		
				);
			}
			
			//保存
			$scope.add=function(){
				$scope.entity.goodsDesc.introduction=editor.html();
				 goodsService.add($scope.entity).success(
					function(response){
						if(response.success){
							$scope.entity={};
							editor.html('');
							//注册成功跳转到登录页面
 							swal({
								title:response.message,
								text:'添加成功!',
								timer:3000,
								type:'success'
							});
							
						 }else{
							 swal({
									title:response.message,
									text:'添加成功!',
									type:'error'
								});
						 }
					}		
				);				
			}
			
			
			//分页获取数据
			$scope.findPage=function(page,rows){
				goodsService.findPage(page,rows).success(
						function(response){
							$scope.list=response.rows;
							$scope.paginationConf.totalItems=response.total;
						}
				);
			}
			
			$scope.searchEntity={};
			//模糊分页
			$scope.findSearch=function(page,rows){
				goodsService.findSearch(page,rows,$scope.searchEntity).success(
						function(response){
							$scope.list=response.rows;
							$scope.paginationConf.totalItems=response.total;
						}
				);
			}
			
			//上传图片
			$scope.uploadFile=function(){	  
				uploadService.uploadFile().success(function(response) {        	
		        	if(response.success){//如果上传成功，取出url
		        		//把这个url给entity.goods对象
		        		//把图片回显(双线数据绑定)
		        		$scope.image_entity.url=response.message;//设置文件地址
		        	}else{
		        		swal({
							title:response.message,
							text:'上传失败!',
							type:'error'
						});
		        	}
		        }).error(function() {           
		        	     alert("上传发生错误");
		        });        
		    };    
			
			
			
			//添加
			$scope.save=function(){
				var returnPojo = {};//方法名称
				if($scope.entity.id!=null){//如果有ID
					returnPojo=goodsService.update($scope.entity);
				}else{
					returnPojo=goodsService.add($scope.entity);
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
				goodsService.findOne(id).success(
						function(response){
							$scope.entity= response;					
					     }
				);				
			}
						 
			//批量删除 
			$scope.dele=function(){			
					//获取选中的复选框			
					 $http.get('../brand/delete.do?ids='+$scope.selectIds).success(
							function(response){
								if(response.success){
										$scope.reloadList();//刷新列表
								}						
							}		
					); 
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
							
							goodsService.dele($scope.selectIds).success(
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
			
			 //$scope.entity={goods:{},goodsDesc:{itemImages:[]}};//定义页面实体结构
			 //添加图片列表  
			 $scope.add_image_entity=function(){    	
			        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
			 }
			 //删除图片列表
			 $scope.remove_image_entity=function(index){    	
				 $scope.entity.goodsDesc.itemImages.splice(index,1);
			 }
			
			 
		//获取一级分类
		$scope.selectItemCatList=function(){
			itemCatService.findAllByParentId(0).success(
			function(response){
				$scope.itemCat1List=response;
			}	
			);
		}	 	
			
		//获取二级分类	 第一个参数监控谁entity.goods.category1Id
		$scope.$watch("entity.goods.category1Id",function(newVal,oldVal){
			itemCatService.findAllByParentId(newVal).success(
					function(response){
						$scope.itemCat2List=response;
					}	
			);
		})
		
		
		//获取三级分类	 第一个参数监控谁entity.goods.category2Id  
 		$scope.$watch("entity.goods.category2Id",function(newVal,oldVal){
			itemCatService.findAllByParentId(newVal).success(
					function(response){
						$scope.itemCat3List=response;
					}	
			);
		})
		
		//监控三级分类	获取模板id  
		$scope.$watch("entity.goods.category3Id",function(newVal,oldVal){
			itemCatService.findOne(newVal).success(
					function(response){
						$scope.entity.goods.typeTemplateId=response.typeId;
					}	
			);
		})	
			
		//获取品牌列表  获取拓展属性,获取规格列表
		//模板选择后更新品牌列表
		$scope.$watch("entity.goods.typeTemplateId",function(newVal,oldVal){
			typeTemplateService.findOne(newVal).success(function(response){
				//获取结果是一个对象,需要把品牌的列表拿出来
				$scope.typeTemplate=response;
				$scope.typeTemplate.brandIds=JSON.parse(response.brandIds);
				$scope.entity.goodsDesc.customAttributeItems=JSON.parse(response.customAttributeItems);
			})
			//获取规格列表
			typeTemplateService.findSpecList(newVal).success(function(response){
				$scope.specList=response;
			})
		})
			
		//保存规格拼接json对象使用
		$scope.updateSpecAttribute=function($event,name,value){
			//做什么? 下面这个方法的三个参数 分别是第一个:要查询的集合 第二个:查询哪个属性 第三个;关键字
			var object= $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems,'attributeName',name);
			if(object!=null){
				
				//判断选中还是没有选中
				if($event.target.checked){
					//判断是谁的
					//如果没有就加进去
					object.attributeValue.push(value);
					
				}else{
					//如果有就删了
				     object.attributeValue.splice(object.attributeValue.indexOf(value),1);
				     //如果选项都消除了，将此消息移除
				     if(object.attributeValue.length==0){
				    	 $scope.entity.goodsDesc.specificationItems.splice(
				    			 $scope.entity.goodsDesc.specificationItems.indexOf(object),1);
				     }
				    
				     
				}
				
				
			}else{
				//新增
				$scope.entity.goodsDesc.specificationItems.push(
					{"attributeName":name,"attributeValue":[value]}	
				);
			}
		}
		
		//生成sku列表
		$scope.createItemList=function(){
			//初始化一个列表
			$scope.entity.itemList=[{spec:{},price:0,num:9999,status:'0',isDefault:'0'}];
			//获取用户选中的规格列表
			var items=$scope.entity.goodsDesc.specificationItems;
			//循环
			for (var i = 0; i < items.length; i++) {
				$scope.entity.itemList=addColumn($scope.entity.itemList,items[i].attributeName,items[i].attributeValue);
			}
		}
		//添加列
		addColumn=function(list,columnName,columnValues){
			var newList=[];//新的集合
			for (var i = 0; i < list.length; i++) {
				var oldRow=list[i];
				for (var j = 0; j < columnValues.length; j++) {
					var newRow=JSON.parse(JSON.stringify(oldRow))//深克隆
					newRow.spec[columnName]=columnValues[j];
					newList.push(newRow);
				}
			}
			return newList;
		}

		
})