<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Promise Demo in Angular</title>		
		<script type="text/javascript"	src="<%=request.getContextPath()%>/scripts/angular.js"></script>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.js"></script>		
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css"></link>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap-theme.css"></link>
		<script tpye="text/javascript">
			var obj = new Object();
			
			var myApplication = angular.module("myAPP", []);
			
			
			myApplication.run(function($http){
				var url = "<%=request.getContextPath()%>/rest/ser/person/list";
				var promise = $http.get(url);
				promise.success(function(data){
					//console.log(data);
					obj.list = data;					
				});
			});
			
			myApplication.controller("listCntrl",function($scope){
				$scope.personList = obj;
			});
			
			myApplication.run(function($http){
				var url = "<%=request.getContextPath()%>/rest/ser/student/list";
				var promise = $http.get(url);
				promise.success(function(data){
					//console.log(data);
					obj.studentList = data;					
				});
			});
			
			myApplication.controller("StudentCntrl",function($scope){
				$scope.studentObj = obj; 				
			});
		</script>
	</head>
	<body>
		<h3 class="text-center">List Display</h3>
		<div class="container">
			<div class="row" ng-app="myAPP">
				<div class="col-md-5">
					<ul ng-controller="listCntrl">
						<li ng-repeat="person in personList.list">
							Person Name : <strong>{{person.personName}}</strong>, Profession : <strong>{{person.personId}}</strong>
						</li>
					</ul>					
				</div>
				<div class="col-md-7">
					<table class="table table-striped table-bordered" ng-controller="StudentCntrl">
						<thead>
							<th>Name</th>
							<th>Age</th>
							<th>Marks</th>
						</thead>
						<tbody>
							<tr ng-repeat="stu in studentObj.studentList">
								<td>{{stu.studentName}}</td>
								<td>{{stu.age}}</td>
								<td>{{stu.marksScored}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>				
		</div>						
	</body>
</html>
