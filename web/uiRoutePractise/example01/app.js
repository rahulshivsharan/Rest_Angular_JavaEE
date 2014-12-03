/**
 * 
 */
angular.module("routerApp",["ui.router"]).config(function($stateProvider,$urlRouterProvider){
	$urlRouterProvider.otherwise("/home");
	
	$stateProvider.state("home",{
		url : "/home",
		templateUrl : "partial-home.html"
	}).state("home.list",{
		url : "/list",
		templateUrl : "partial-home-list.html",
		controller : function($scope){
			$scope.empList = [
			                      { name : "Rahul", location : "Mumbai", designation : "Software Developer" },
			                      { name : "Fahad", location : "Pune", designation : "Junior Engineer" },
			                      { name : "Amir", location : "Kalyan", designation : "Manager" },
			                      { name : "Sagar", location : "Dombivali", designation : "Senior Manager" },
			                      { name : "Kamal", location : "Udhampur", designation : "Manager" },
			                      { name : "Ramesh", location : "Satara", designation : "Consultant" }
			                 ];
		}
	}).state("home.para",{
		url : "/para",
		template : "<p style='color:red'>This is the plan text, nothing much.</p>"		
	}).state("about",{
		url : "/about",		
		views : {
			"" : {
				templateUrl : "partial-about.html"		
			},
			"movie@about" : {
				templateUrl : "partial-about-movie.html"		
			},
			"liqour@about" : {
				templateUrl : "partial-about-liqour.html",
				controller : "liqourCtrl"
			}
		}	
	});
}).controller("liqourCtrl",function($scope){
	$scope.liqourList = new Array();
	$scope.liqourList.push({ name : "White Mischif", type : "Vodka"});
	$scope.liqourList.push({ name : "Bacardi", type : "Vodka"});
	$scope.liqourList.push({ name : "Old Monk", type : "Rum"});
	$scope.liqourList.push({ name : "Blenders Pride", type : "Whisky"});
	$scope.liqourList.push({ name : "Mc Dowelles No 1", type : "Whisky"});
	$scope.liqourList.push({ name : "Bombay Saphirre", type : "Jin"});
});
