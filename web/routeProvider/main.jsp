<%@ page language="java" 
		 contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Route Provider Example</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/angular1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/angular-resource.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/angular-route.js"></script>
		<script type="text/javascript"	src="<%=request.getContextPath()%>/scripts/jquery-1.11.1.js"></script>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css"></link>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap-theme.css"></link>
		<script type="text/javascript">
		(function() {
			angular.module("myApp", ['ngResource','ngRoute']);
			angular.module("myApp").controller("myCtrl",myCtrl);
			angular.module("myApp").controller("collegeCtrl",collegeCtrl);
			angular.module("myApp").controller("studentCtrl",studentCtrl);
			
			angular.module("myApp").config(configFn);
			angular.module("myApp").factory("collegeService", collegeService);
			
			
			
			configFn.$inject = ["$routeProvider"]
			myCtrl.$inject = ["$scope","$log","$location"];
			collegeCtrl.$inject = ["$scope","$log","$location","collegeService"];
			studentCtrl.$inject = ["$scope","$log","$location","collegeService"];
			
			function myCtrl($scope,$log,$location){
				this.routeTo = function(path){
					$location.url(path);
				}
			}
			
			function collegeCtrl($scope,$log,$location,collegeService){
				var vm = this;
				collegeService.getcolleges().then(function(response) {					
					vm.msg = "Loaded Successfully";
					vm.list = collegeService.colleges;
					vm.response = "success";
				},function(response){
					vm.response = "error";
					vm.msg = response.data.msg;
				});
			}
			
			function studentCtrl($scope,$log,$location,collegeService){
				var vm = this;
				collegeService.getStudents().then(function(response) {					
					vm.msg = "Loaded Successfully";
					vm.list = collegeService.students;
					vm.response = "success";
				},function(response){
					vm.response = "error";
					vm.msg = response.data.msg;
				});
			}
			
			function configFn($routeProvider){
				$routeProvider.when("/college",{
					templateUrl : "college.html",
					controller : "collegeCtrl",
					controllerAs : "vm",
					resolve:{
						list : function(collegeService){
							return collegeService.getcolleges("refresh");
						} 
					}
				});
				
				$routeProvider.when("/student",{
					templateUrl : "Student.html",					
					controller : "studentCtrl",
					controllerAs : "vm",
					resolve:{
						list : function(collegeService){
							return collegeService.getStudents("refresh");
						} 
					}
				});
				
				$routeProvider.otherwise({
					templateUrl : "base.html"
				});
			}
			
			function collegeService($resource, $q) {
				var obj = new Object();
				obj.colleges = undefined;
				obj.students = undefined;

				obj.getcolleges = function() {
					return (arguments[0] === "refresh" || obj.colleges === undefined) ? $q(fn) : $q.when(obj.colleges);  
					
					function fn(resolve, reject) {
						var promiseObj = $resource("/sample/rest/studentdemo/colleges").query().$promise;
						promiseObj.then(successFn, errorFn);

						function successFn(response) {
							obj.colleges = response;
							resolve(response);
						}

						function errorFn(response) {						
							reject(response);
						}
					}
				}
				
				obj.getStudents = function() {
					return (arguments[0] === "refresh" || obj.students === undefined) ? $q(fn) : $q.when(obj.colleges);  
					
					function fn(resolve, reject) {
						var promiseObj = $resource("/sample/rest/studentdemo/getList").query().$promise;
						promiseObj.then(successFn, errorFn);

						function successFn(response) {
							obj.students = response;
							resolve(response);
						}

						function errorFn(response) {						
							reject(response);
						}
					}
				}

				return obj;
			}

		})();
		</script>
	</head>
	<body ng-controller="myCtrl as c">
		<div class="container">
			<div class="page-header">
				<h4>Route Resolver</h4>
			</div>
			<div class="row">
				<div class="col-md-2 col-md-offset-2">
					<a href="javascript:void(0)" ng-click="c.routeTo('college')">College</a>
				</div>
				<div class="col-md-2"><a href="javascript:void(0)" ng-click="c.routeTo('student')">Student</a></div>
			</div>
			<div class="row" style="padding-top:20px">
				<div class="col-md-6 col-md-offset-2">
					<ng-view />
				</div>
			</div>
		</div>
	</body>
</html>
