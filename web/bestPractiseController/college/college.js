angular.module("CollegeModule",[])
.constant("collegeListURL","../rest/ser/college/list/One")
.controller("CollegeController",function($location, $http, collegeListURL){
	$location.url("/loadingImage");
	
	(function(collegeCntrl){
		$http.get(collegeListURL).success(function(data){
			collegeCntrl.collegeList = data;
			$location.url("/college/list");
		});
	})(this);
	
	
	this.editCollege = function(college){
		this.selectedCollege = college;
	}
	
	this.cancel = function(){
		$location.url("/college/list");
	}
	
	this.saveEditCollege = function(){
		$location.url("/doLoading");
		
		var serializedData = jQuery.param({
			collegeName : this.selectedCollege.collegeName,
			collegeId : this.selectedCollege.collegeId		
		});
		
		(function(collegeCntrl){
			$http({
				method : 'POST',
				url : '../rest/ser/collegeEntity/edit',
				data : serializedData,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(data){
				collegeCntrl.collegeList = data;
				$location.url("/college/list");
			});
		})(this);
	}
	
	this.saveAddCollege = function(){
		$location.url("/doLoading");
		
		var serializedData = jQuery.param({		
			collegeName : this.newCollege.collegeName		
		});
		
		(function(collegeCntrl){
			$http({
				method : 'POST',
				url : '../rest/ser/collegeEntity/add',
				data : serializedData,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(data){
				collegeCntrl.collegeList = data;
				$location.url("/college/list");
			});
		})(this);
	}
	
});
