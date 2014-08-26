angular.module("StudentInfo",[])
		.factory("studentMetric",function(){
			return {
				getTotalStudent : function(studentList){							
					return studentList.length;					
				},				
				getAvgAge : function(studentList){					
					var totalStudent = studentList.length; 
					//console.log("total Student "+totalStudent);
					var totalAge = 0;
					angular.forEach(studentList,function(student){						
						totalAge += student.age;
						//console.log("avg student age "+totalAge);
					});
					
					return (totalAge/totalStudent);
				},
				getAvgMarks : function(studentList){					
					var totalStudent = studentList.length; 
					var totalMarks = 0;
					
					angular.forEach(studentList,function(student){
						totalMarks += student.marksScored;
					});
					
					return (totalMarks/totalStudent);
				}
			}
		}).directive("studentInfo",function(studentMetric){
			return {
				restrict : "E",
				templateUrl : "StudentMetricInfo.html",
				controller : function($scope){
					var studentList = $scope.studentInfo.list;
					$scope.getStudentTotal = function(){
						return studentMetric.getTotalStudent(studentList);
					};
					
					$scope.getAvgAge4Student = function(){
						return studentMetric.getAvgAge(studentList);
					};
					
					$scope.getAvgMarks4Student = function(){
						return  studentMetric.getAvgMarks(studentList);
					}
				} 
			};
		});
