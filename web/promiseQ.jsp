<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Promise differed</title>
		<style type="text/css">
			.error{
			   color : red;
			}
			.success{
				color : green;
			} 
		</style>		
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/angular.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/angular-resource.js"></script>
		<script type="text/javascript">
			(function(){
				angular.module("myApp",['ngResource']);
				angular.module("myApp").controller("myCtrl",myCtrl);
				angular.module("myApp").factory("studentService",studentService);
				
				myCtrl.$inject = ["$scope","$log","studentService"];
				studentService.$inject = ["$resource","$q"];
				
				function myCtrl($scope,$log,studentService){
					var vm = this;
					vm.msg = "List not loaded";
					vm.list = [];
					vm.response = "";
					
					$scope.$watch("vm.list",function(newlist,oldlist){						
						angular.forEach(vm.list,function(person,index){
							$log.log(index+" -- "+person.personName);
						});
					});
					
					studentService.getStudents().then(function(response){
						$log.log("Promise Resolved "+response.$resolved);
						if(response.$resolved == true){
							vm.msg = "Loaded Successfully";
							vm.list = response;
							vm.response = "success";
						}else{
							vm.response = "error";
							vm.msg = response.data;
						}
					});
					
				}
				
				function studentService($resource,$q){
					var obj = new Object();
					obj.students = undefined;
					
					obj.getStudents = function(){
						var deferred = $q.defer();
						var rsPromise = $resource("/sample/rest/studentdemo/getList").query().$promise;						
						rsPromise.then(successFn,errorFn);
						
						function successFn(response){																			
							deferred.resolve(response);
						}
						
						function errorFn(response){														
							deferred.resolve(response);
						}
						return deferred.promise;
					}
					
					return obj;
				}
			})();
		</script>		
	</head>
	<body ng-controller="myCtrl as vm">
		<h4 ng-class="{'error' : (vm.response === 'error'),'success' : (vm.response === 'success')}">{{vm.msg}}</h4>
		<div ng-if="vm.response === 'success'">
			<ul style="font-style:italic">
				<li ng-repeat="person in vm.list">{{person.personName}}</li>
			</ul>
		</div>
	</body>
</html>
