angular.module("MainModule",[]).controller("mainCntrl",function($scope){
	$scope.getScreen = function(){
		var url = "";
		if($scope.screenName == "College"){
			url = "college/College.html";
		}else if($scope.screenName == "Student"){
			url = "student/Student.html";
		}else{
			url = "BaseInfo.html";
		}
		return url;
	} 
	
	$scope.setScreen = function(urlName){
		$scope.screenName = urlName;
	}
	
});
