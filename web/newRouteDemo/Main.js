 angular.module("MainModule",[])
		.constant("studentListURL","../rest/ser/studentEntity/list/One")
		.constant("collegeListURL","../rest/ser/college/list/One")
		.controller("MainController",function(	$scope,
												$route,
												$routeParams,
												$location,
												$http,
												studentListURL,
												collegeListURL){
		
		$http.get(studentListURL).success(function(data){
			$scope.studentList = data;
			$location.url("/list");
		});
		
		$http.get(collegeListURL).success(function(data){
			$scope.CollegList = data;			
		});
		
		$scope.editStudent = function(selectedStudent){
			$scope.selectedStudent = selectedStudent;
			$location.url("/edit");
		}
		
		$scope.saveEditStudent = function(){
			//console.log("Student Name "+$scope.selectedStudent.studentName);
			//console.log("College Id "+$scope.selectedStudent.collegeId);
			
			
			var serializedData = jQuery.param({
				studentName : $scope.selectedStudent.studentName,
				studentId : $scope.selectedStudent.studentId,
				collegeId : $scope.selectedStudent.collegeId
			});
			$http({
				method : 'POST',
				url : '../rest/ser/studentEntity/edit',
				data : serializedData,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(data){
				$scope.studentList = data;
				$location.url("/list");
			});			
		}
		
		$scope.addStudent = function(){
			$location.url("/add");
		}
		
		$scope.saveNewStudent = function(){
			
			var serializedData = jQuery.param({
				studentName : $scope.newStudent.studentName,				
				collegeId : $scope.newStudent.collegeId
			});
			
			$http({
				method : 'POST',
				url : '../rest/ser/studentEntity/add',
				data : serializedData,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(data){
				$scope.studentList = data;
				$scope.newStudent.studentName = "";
				$scope.newStudent.collegeId = "";
				$location.url("/list");
			});
		}
		
});
