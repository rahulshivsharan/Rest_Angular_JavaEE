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
			var personApp = angular.module("PersonAPP", []);
			
			personApp.run(function($http){
				var url = "<%=request.getContextPath()%>/rest/ser/person/list";
				var promise = $http.get(url);
				promise.success(function(data){
					//console.log(data);
					obj.list = data;					
				});
			});
			
			personApp.controller("personCntrl",function($scope){
				$scope.personList = obj;
			});
			
			var studentApp = angular.module("StudentAPP", []);
			
			studentApp.run(function($http){
				var url = "<%=request.getContextPath()%>/rest/ser/student/list";
				var promise = $http.get(url);
				promise.success(function(data){
					//console.log(data);
					obj.studentList = data;					
				});
			});
			
			studentApp.controller("StudentCntrl",function($scope){
				$scope.studentObj = obj; 				
			});
			
			var addStudentApp = angular.module("AddStudentAPP",[]);
			addStudentApp.run(function($http){
				var url = "<%=request.getContextPath()%>/rest/ser/student/list";
				var promise = $http.get(url);
				promise.success(function(data){
					//console.log(data);
					obj.studentList2 = data;					
				});
			});
			
			addStudentApp.controller("AddStudentCntrl",function($scope){
				$scope.studentObj = obj;
				
				$scope.addStudent = function(name,age,marks){
					
					$scope.studentObj.studentList2.push({
						studentName : name,
						age : age,
						marksScored : marks
					});
					
					$scope.inputStudentName = "";
					$scope.inputStudentAge = "";
					$scope.inputStudentMarks = "";
				}
			});
			
			angular.element(document).ready(function(){
				angular.bootstrap(document.getElementById("StudentAppID"),['StudentAPP']);
				angular.bootstrap(document.getElementById("AddPersonID"),['AddStudentAPP']);
			});
		</script>
	</head>
	<body>		
		<div class="container">
			<div class="page-header">
				<h3>Display Angular multiple Modules and Bootstrap</h3>
			</div>
			<div class="row">
				<div id="PersonAppID" class="col-md-5" ng-app="PersonAPP">
					<ul ng-controller="personCntrl">
						<li ng-repeat="person in personList.list">
							Person Name : <strong>{{person.personName}}</strong>, Profession : <strong>{{person.personId}}</strong>
						</li>
					</ul>					
				</div>
				<div id="StudentAppID" class="col-md-7" ng-app="StudentAPP">
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
			<div class="row" id="AddPersonID" ng-app="AddStudentAPP" ng-controller="AddStudentCntrl">
				<div class="col-md-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Add Student</strong>
						</div>
						<div class="panel-body">
							<div class="panel panel-default">
								<div class="panel-body">
									<form role="form">
										<div class="form-group">
											<label for="StudentNameID">Enter Student Name</label>
											<input type="text" class="form-control" id="StudentNameID" ng-model="inputStudentName" placeholder="Name" />
										</div>
										<div class="form-group">
											<label for="StudentAgeID">Enter Student Age</label>
											<input type="text" class="form-control" id="StudentAgeID" ng-model="inputStudentAge" placeholder="Age"/>
										</div>
										<div class="form-group">
											<label for="StudentMarksID">Enter Student Marks</label>
											<input type="text" class="form-control" id="StudentMarksID" ng-model="inputStudentMarks" placeholder="Marks"/>
										</div>
										<button type="button" ng-click="addStudent(inputStudentName,inputStudentAge,inputStudentMarks)" class="btn btn-default">Enter</button>
									</form>
								</div>		
							</div>
						</div>	
					</div>
				</div>
				<div class="col-md-7">
					<table class="table table-striped table-bordered">
						<thead>
							<th>Name</th>
							<th>Age</th>
							<th>Marks</th>
						</thead>
						<tbody>
							<tr ng-repeat="stu in studentObj.studentList2 | orderBy : 'studentName'">
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
