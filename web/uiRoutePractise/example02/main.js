/**
 * 
 */

angular.module("StudentApp",["ui.router","ngResource"]).run(function($rootScope,$state){
	$rootScope.$state = $state;
}).factory("CityFactory",function($resource){
	return $resource("../../rest/studentTwo/colleges");
}).factory("CollegeFactory",function($resource){
	return $resource("../../rest/studentTwo/colleges/:cityName",{cityName : '@cityName'},{
		getStudents : {
			url : "../../rest/studentTwo/:collegeId",
			method : "GET",
			isArray : true,
			params : {
				collegeId : '@collegeId'
			}
		}
	});
}).config(function($stateProvider){
	$stateProvider.state("colleges",{
		url : "/colleges",
		templateUrl : "college-list.html", 
		controller : "studentAppCtrl"	
	}).state("colleges.students",{
		url : "/studentList",
		templateUrl : "student-list.html",
		controller : "studentAppCtrl"	
	});
}).controller("studentAppCtrl",function($scope,CityFactory,CollegeFactory,$state){
	$scope.cityList = CityFactory.query();
	
	$scope.setSelectedCity= function(city){
		$scope.selectedCity = city;
		$scope.collegeList = CollegeFactory.query({'cityName' : $scope.selectedCity});
		$state.go("colleges");
	}
	
	$scope.setSelectedCollege = function(collegeId){
		$scope.selectedCollege = collegeId;
		$scope.studentList = CollegeFactory.getStudents({'collegeId' : collegeId});
		$state.go(".students");
	}
	
});
