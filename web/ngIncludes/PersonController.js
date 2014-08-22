angular.module("MultiModuleApp").controller("personCntrl",function($scope){
	$scope.personList = [
		{
			personName : "John Edward",
			profession : "Software Engineer"
		},
		{
			personName : "Sam Kimpinsky",
			profession : "Network Engineer"
		},
		{
			personName : "Jack Nickolson",
			profession : "Neurosurgan"
		},
		{
			personName : "Ashton Kutcher",
			profession : "Web Designer"
		}
	];
	
	$scope.addPerson = function(personName,profName){
		$scope.personList.push({
			personName : personName,
			profession : profName
		});
		
		$scope.personName = "";
		$scope.profName = "";
	}
});
