'use strict';

var app = angular.module('cookbook',
    ['cookbook.directives', 'cookbook.services']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
      when('/', {
        controller: 'ListCtrl',
        templateUrl:'/cookbook/views/list.html'
      }).when('/test', {
          controller: 'TestCtrl',
          templateUrl:'/cookbook/views/test.html'
        }).otherwise({redirectTo:'/'});
}]);

app.controller('TestCtrl', ['$scope', '$location',
  function($scope, $location) {
    console.log("testing");
  }]);

app.controller('ListCtrl', ['$scope',
    function($scope) {

}]);