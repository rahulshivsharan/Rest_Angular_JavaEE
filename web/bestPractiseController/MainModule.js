angular.module("MainModule",[]).controller("MainCntrl",function(){
	this.getScreen = function(){
		var url = "";
		if(this.screenName == "College"){
			url = "college/College.html";
		}else if(this.screenName == "Student"){
			url = "student/Student.html";
		}else{
			url = "InitMsg.html";
		}
		return url;
	} 
	
	this.setScreen = function(urlName){
		this.screenName = urlName;
	}
	
});
