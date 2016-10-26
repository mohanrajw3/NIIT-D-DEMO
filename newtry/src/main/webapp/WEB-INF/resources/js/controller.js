var app=angular.module("app",[])
.controller("myController",function($scope,$http){

	$scope.getBookList = function(){
		   $http.get('http://localhost:8002/newtry/getBooksList').success(function (data){
		       $scope.books = data;
		   });
		};
});
