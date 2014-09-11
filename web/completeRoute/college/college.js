angular.module("CollegeModule",[])
.constant("collegeListURL","../rest/ser/college/list/One")
.controller("CollegeController",function($scope, $route, $routeParams, $location, $http,collegeListURL){
	
	$location.url("/loadingImage");
	
	$http.get(collegeListURL).success(function(data){
		$scope.collegeList = data;
		$location.url("/collegeList");
	});
	
	$scope.saveEditCollege = function(){
		$location.url("/doLoading");
		
		var serializedData = jQuery.param({
			collegeName : $scope.selectedCollege.collegeName,
			collegeId : $scope.selectedCollege.collegeId		
		});
		
		$http({
			method : 'POST',
			url : '../rest/ser/collegeEntity/edit',
			data : serializedData,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data){
			$scope.collegeList = data;
			$location.url("/collegeList");
		});
	}
	
	$scope.saveAddCollege = function(){
		$location.url("/doLoading");
		
		var serializedData = jQuery.param({
			collegeName : $scope.newCollege.collegeName			
		});
		
		$http({
			method : 'POST',
			url : '../rest/ser/collegeEntity/add',
			data : serializedData,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data){
			$scope.collegeList = data;
			$location.url("/collegeList");
		});
	}
	
	$scope.editCollege = function(college){
		$scope.selectedCollege = college;
	}
	
	$scope.cancel = function(){
		$location.url("/collegeList");
	}
});
