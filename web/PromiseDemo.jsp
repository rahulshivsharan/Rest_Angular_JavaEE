<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Multi App in Angular</title>		
		<script type="text/javascript"	src="<%=request.getContextPath()%>/scripts/angular.js"></script>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.js"></script>		
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css"></link>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap-theme.css"></link>
		<script tpye="text/javascript">
			var obj = new Object();			
			
			var promisePersonApp = angular.module("PersonPromise",[]);

			promisePersonApp.run(function($http){
				var url = "<%=request.getContextPath()%>/rest/ser/student/list/two";
				var promise = $http.get(url);
				
				var successCallback = function(response){
					obj.studentList3 = response.data;	
				}
				var errorCallback = function(){
					obj.studentList3 = [{
						studentName : "ERROR",
						age : "ERROR",
						marksScored : "ERROR"
					}];					
				}
				var notifyCallback = function(){
					alert("Its done");
				}
				promise.then(successCallback,errorCallback,notifyCallback);
			});
			
			promisePersonApp.controller("promiseCntrl",function($scope){
				$scope.studentObj = obj;				
			});
		</script>
	</head>
	<body>		
		<div class="container">
			<div class="page-header">
				<h3>Display Angular multiple Modules and Bootstrap</h3>
			</div>
			<div class="row" id="PersonPromiseID" ng-app="PersonPromise" ng-controller="promiseCntrl">
				<div class="col-md-4">
					<table class="table table-striped table-bordered">
						<thead>
							<th>Name</th>
							<th>Age</th>
							<th>Marks</th>
						</thead>
						<tbody>
							<tr ng-repeat="stu in studentObj.studentList3 | orderBy : 'studentName'">
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
