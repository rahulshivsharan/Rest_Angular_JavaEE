angular.module("StudentModule",[])
	.constant("studentListURL","../rest/ser/studentEntity/list/One")
	.constant("collegeListURL","../rest/ser/college/list/One")
	.controller("StudentController",function($scope, $route, $routeParams, $location, $http, studentListURL, collegeListURL){
		
		$location.url("/loadingImage");
		
		$http.get(studentListURL).success(function(data){
			$scope.studentList = data;
			$location.url("/studentList");
		});
		
		$scope.editStudent = function(selectedStudent){
			$scope.selectedStudent = selectedStudent;
			$location.url("/student/edit");
		}
		
		$http.get(collegeListURL).success(function(data){
			$scope.CollegList = data;			
		});
				
		$scope.saveEditStudent = function(){
			
			$location.url("/doLoading");
			
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
				$location.url("/studentList");
			});
		}
		
		$scope.saveNewStudent = function(){
			$location.url("/doLoading");
			
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
				$location.url("/studentList");
			});
		}
		
		$scope.cancel = function(){
			$location.url("/studentList");
		}
		
		
});
