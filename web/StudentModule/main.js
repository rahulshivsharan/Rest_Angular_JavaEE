var obj = new Object();
angular.module("myController",[])
		.run(function($http){
			var url = "../rest/ser/student/list/two";
			
			$http.get(url).success(function(data){
				obj.list = data;
			});
		})		
		.controller("studentCntrl",function($scope){
			$scope.studentInfo = obj;
			
			$scope.addStudent = function(stName,stAge,stMarks){
				
				$scope.studentInfo.list.push({
					studentName : stName,
					age : parseInt(stAge),
					marksScored : parseInt(stMarks)
				});
				
				/*
				console.log("Entered Student "+$scope.enteredName); 
				console.log("Entered Student "+$scope.enteredAge); 
				console.log("Entered Student "+$scope.enteredMarks); 
				*/
				
				$scope.enteredName = "";
				$scope.enteredAge = "";
				$scope.enteredMarks = "";
				
			};
		});
