angular.module("MultiModuleApp").controller("studentCntrl",function($scope){
	$scope.studentList = [
		{
			studentName : "Sunil Agarwal",
			dept : "Computer Networks"
		},
		{
			studentName : "Anil Singh",
			dept : "Master of Arts"
		},
		{
			studentName : "Pankaj Unhale",
			dept : "Bechelor of Commerce"
		},
		{
			studentName : "Kaustubh Kale",
			dept : "Engineering In Computer Science"
		},
	];
	
	$scope.addStudent = function(sName,dName){
		$scope.studentList.push({
			studentName : sName,
			dept : dName
		});
		$scope.studentName = "";
		$scope.deptName = "";
	}
});
