<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Promises in Ajax Request in Angular.js</title>
		<script type="text/javascript"	src="<%=request.getContextPath()%>/scripts/angular.js"></script>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.js"></script>		
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css"></link>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap-theme.css"></link>
		<script type="text/javascript">
			var myData = new Object();
			
			angular.module("AjaxPractiseApp",[]).run(function($http){
				var url = "<%=request.getContextPath()%>/rest/s/student/list/two";
				var returnedPromise = $http.get(url);
				
				var successFn = function(response){
					myData.personList =  response.data;
				}
				var errorFn = function(error){
					myData.returnedError = error;
				}
				
				returnedPromise.then(successFn,errorFn);
				
			}).controller("AjaxLoadCntrl",function($scope){
				$scope.PersonInfo = myData;	   	
			});
		</script>
	</head>
	<body>
		<div class="container" ng-app="AjaxPractiseApp">
			<div class="page-header">
				<h3>Ajax Promises in Angular.js</h3>
			</div>
			<div class="row" ng-controller="AjaxLoadCntrl">
				<div class="col-md-4">		
					<div class="alert alert-info" role="alert" ng-show="PersonInfo.returnedError">
						<strong>{{PersonInfo.returnedError.status}} !</strong> facing some error at backend
					</div>
					<ul class="list-group" ng-hide="PersonInfo.returnedError">					
						<li class="list-group-item" ng-repeat="person in PersonInfo.personList">
							Student Name : <strong>{{person.studentName}}</strong>, Age : <strong>{{person.age}}</strong>
						</li>
					</ul>				
				</div>
			</div>			
		</div>
	</body>
</html>
