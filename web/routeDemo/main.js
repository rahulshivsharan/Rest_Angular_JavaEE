angular.module("MainModule",[]).controller("mainCntrl",function($scope,$route, $routeParams, $location){
	$scope.studentList = [
	                      { studentName : 'Ashutosh Gowarikar', studentId : 123, college : 'Turbhe College'},
	                      { studentName : 'Zubin Mehta', studentId : 13, college : 'Sion Education Trust'},
	                      { studentName : 'Mahesh Mhaske', studentId : 132, college : 'Shah And Anchore College'},
	                      { studentName : 'Suresh Gangurde', studentId : 145, college : 'Datta Meghe Educational Trust'}
	                      ];
	$scope.CollegList = [
	                     {	Name : 'Turbhe College', id : 'Turbhe College' },
	                     {	Name : 'Sion Education Trust', id : 'Sion Education Trust' },
	                     {	Name : 'Shah And Anchore College', id : 'Shah And Anchore College' },
	                     {	Name : 'Datta Meghe Educational Trust', id : 'Datta Meghe Educational Trust' }
	                     ];
	
	$scope.loadEditData = function(editedName,editedCollege,sId){
		//console.log(" Edited Name : "+editedName);
		//console.log(" Edited College  : "+editedCollege);
		//console.log(" Edited StudentId : "+sId);
		var x=0,studentSize = $scope.studentList.length;
		
		for(x=0; x < studentSize; x++){
			
			if($scope.studentList[x].studentId == sId){
				$scope.studentList[x].college = editedCollege;
				$scope.studentList[x].studentName = editedName;
				break;
			}else{
				continue;
			}
		}
		$location.url('/doNothing');
	}
	
	$scope.loadStudent = function(sName,sId,sCollege){
		$scope.selectedStudent = {
				studentName : sName,
				studentId : sId,
				college : sCollege
		}
	}
});
