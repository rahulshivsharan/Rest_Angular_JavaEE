/**
 * 
 */
angular.module("myReportCard",["ui.router"]).run(function($rootScope,$state){
	$rootScope.$state = $state;
}).config(function($stateProvider,$urlRouterProvider){
	$urlRouterProvider.otherwise("/home");
	$stateProvider.state("home",{
		url : "/home",
		templateUrl : "home.html",
		controller : "reportCardCtrl"	
	}).state("subject",{	
		url : "/subject/:subjectId",
		templateUrl : "studentList.html",
		controller : "reportCardCtrl"	
	});
}).controller("reportCardCtrl",function($scope,$stateParams){
	$scope.subjectList = [{ id : "EN" , name : "English"},
	                      { id : "MT" , name : "Maths"},
	                      { id : "HS" , name : "History"},
	                      { id : "SC" , name : "Science"},
	                      { id : "HN" , name : "Hindi"},
	                      { id : "GP" , name : "Geography"}
	                      ];
	var selectedSubject = $stateParams.subjectId;
	
	var subjectCodeInfo = {
			"EN" : {
				"subject" : "English",
				"failed" : [{ name : "Shamsher Singh" , marks : 34 },
				            { name : "Aslam Shaikh" , marks : 20 },
				            { name : "Asauddin Owaisi" , marks : 18 },
				            { name : "Faisal Shaikh" , marks : 15 },
				            { name : "Hamid Ansari" , marks : 23 },
				            { name : "Fahad Khan" , marks : 31 }
				            ],
				"passed" : [{ name : "Suresh Dharmadikari" , marks : 67 },
				            { name : "Mayank Kulkarni" , marks : 56 },
				            { name : "Sumedh Nadkarni" , marks : 89 },
				            { name : "Baban Joshi" , marks : 79 },
				            { name : "Vimal Kapoor" , marks : 86 },
				            { name : "Ganesh Bhat" , marks : 95 },
				            { name : "Venkat Acharya" , marks : 58 },
				            { name : "Raju Jain" , marks : 82 }
				            ]            
			},
			"MT" : {
				"subject" : "Mathematics",
				"failed" : [{ name : "Suresh Dharmadikari" , marks : 13 },
				            { name : "Faisal Shaikh" , marks : 25 },
				            { name : "Ganesh Bhat" , marks : 32 },
				            { name : "Vimal Kapoor" , marks : 26 },
				            { name : "Aslam Shaikh" , marks : 20 },
				            { name : "Raju Jain" , marks : 22 }
				            ],
				"passed" : [{ name : "Shamsher Singh" , marks : 94 },
				            { name : "Asauddin Owaisi" , marks : 88 },
				            { name : "Hamid Ansari" , marks : 73 },
				            { name : "Fahad Khan" , marks : 63 },
				            { name : "Mayank Kulkarni" , marks : 56 },
				            { name : "Sumedh Nadkarni" , marks : 89 },
				            { name : "Baban Joshi" , marks : 79 },				           
				            { name : "Venkat Acharya" , marks : 58 }
				            ]            
			},
			"HS" : {
				"subject" : "History",
				"failed" : [{ name : "Shamsher Singh" , marks : 34 },
				            { name : "Aslam Shaikh" , marks : 20 },
				            { name : "Asauddin Owaisi" , marks : 18 },
				            { name : "Faisal Shaikh" , marks : 15 },
				            { name : "Hamid Ansari" , marks : 23 },
				            { name : "Fahad Khan" , marks : 31 }
				            ],
				"passed" : [{ name : "Suresh Dharmadikari" , marks : 67 },
				            { name : "Mayank Kulkarni" , marks : 56 },
				            { name : "Sumedh Nadkarni" , marks : 89 },
				            { name : "Baban Joshi" , marks : 79 },
				            { name : "Vimal Kapoor" , marks : 86 },
				            { name : "Ganesh Bhat" , marks : 95 },
				            { name : "Venkat Acharya" , marks : 58 },
				            { name : "Raju Jain" , marks : 82 }
				            ]            
			},
			"SC" : {
				"subject" : "Science",
				"failed" : [{ name : "Suresh Dharmadikari" , marks : 13 },
				            { name : "Faisal Shaikh" , marks : 25 },
				            { name : "Ganesh Bhat" , marks : 32 },
				            { name : "Vimal Kapoor" , marks : 26 },
				            { name : "Aslam Shaikh" , marks : 20 },
				            { name : "Raju Jain" , marks : 22 }
				            ],
				"passed" : [{ name : "Shamsher Singh" , marks : 94 },
				            { name : "Asauddin Owaisi" , marks : 88 },
				            { name : "Hamid Ansari" , marks : 73 },
				            { name : "Fahad Khan" , marks : 63 },
				            { name : "Mayank Kulkarni" , marks : 56 },
				            { name : "Sumedh Nadkarni" , marks : 89 },
				            { name : "Baban Joshi" , marks : 79 },				           
				            { name : "Venkat Acharya" , marks : 58 }
				            ]   
			},
			"HN" : {
				"subject" : "Hindi",
				"failed" : [{ name : "Shamsher Singh" , marks : 34 },
				            { name : "Aslam Shaikh" , marks : 20 },
				            { name : "Asauddin Owaisi" , marks : 18 },
				            { name : "Faisal Shaikh" , marks : 15 },
				            { name : "Hamid Ansari" , marks : 23 },
				            { name : "Fahad Khan" , marks : 31 }
				            ],
				"passed" : [{ name : "Suresh Dharmadikari" , marks : 67 },
				            { name : "Mayank Kulkarni" , marks : 56 },
				            { name : "Sumedh Nadkarni" , marks : 89 },
				            { name : "Baban Joshi" , marks : 79 },
				            { name : "Vimal Kapoor" , marks : 86 },
				            { name : "Ganesh Bhat" , marks : 95 },
				            { name : "Venkat Acharya" , marks : 58 },
				            { name : "Raju Jain" , marks : 82 }
				            ]      
			},
			"GP" : {
				"subject" : "Geography",
				"failed" : [{ name : "Suresh Dharmadikari" , marks : 13 },
				            { name : "Faisal Shaikh" , marks : 25 },
				            { name : "Ganesh Bhat" , marks : 32 },
				            { name : "Vimal Kapoor" , marks : 26 },
				            { name : "Aslam Shaikh" , marks : 20 },
				            { name : "Raju Jain" , marks : 22 }
				            ],
				"passed" : [{ name : "Shamsher Singh" , marks : 94 },
				            { name : "Asauddin Owaisi" , marks : 88 },
				            { name : "Hamid Ansari" , marks : 73 },
				            { name : "Fahad Khan" , marks : 63 },
				            { name : "Mayank Kulkarni" , marks : 56 },
				            { name : "Sumedh Nadkarni" , marks : 89 },
				            { name : "Baban Joshi" , marks : 79 },				           
				            { name : "Venkat Acharya" , marks : 58 }
				            ]   
			}
	}
	
	$scope.getSelectedSubject = function(){
		 $scope.passList = 	subjectCodeInfo[selectedSubject]["passed"];
		 $scope.failList = 	subjectCodeInfo[selectedSubject]["failed"];
		 return subjectCodeInfo[selectedSubject]["subject"];
	}
	
	
});
