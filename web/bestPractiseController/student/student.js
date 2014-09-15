angular.module("StudentModule",[])
.constant("studentListURL","../rest/ser/studentEntity/list/One")
.constant("collegeListURL","../rest/ser/college/list/One")
.controller("StudentController",function( $location, $http, studentListURL, collegeListURL){
	$location.url("/loadingImage");
	
	(function(studentCntrl){
		$http.get(studentListURL).success(function(data){
			studentCntrl.studentList = data;
			$location.url("/student/list");
		});
		
		$http.get(collegeListURL).success(function(data){
			studentCntrl.CollegList = data;			
		});
	})(this);
	
	this.editStudent = function(selectedStudent){
		this.selectedStudent = selectedStudent;
		$location.url("/student/edit");
	}
	
	this.saveEditStudent = function(){
		$location.url("/doLoading");
		var serializedData = jQuery.param({
			studentName : this.selectedStudent.studentName,
			studentId : this.selectedStudent.studentId,
			collegeId : this.selectedStudent.collegeId
		});
		
		(function(studentCntrl){
			$http({
				method : 'POST',
				url : '../rest/ser/studentEntity/edit',
				data : serializedData,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(data){
				studentCntrl.studentList = data;
				$location.url("/student/list");
			});
		})(this);
	}
	
	this.saveNewStudent = function(){
		$location.url("/doLoading");
		var serializedData = jQuery.param({
			studentName : this.newStudent.studentName,			
			collegeId : this.newStudent.collegeId
		});
		
		(function(studentCntrl){
			$http({
				method : 'POST',
				url : '../rest/ser/studentEntity/add',
				data : serializedData,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(data){
				studentCntrl.studentList = data;				
				studentCntrl.newStudent.studentName = "";
				studentCntrl.newStudent.collegeId = "";
				$location.url("/student/list");
			});
		})(this);
	}
	
	this.cancel = function(){
		$location.url("/student/list");
	}
	
});
