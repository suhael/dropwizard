'use strict';

var app = angular.module('guthub',
    ['guthub.services']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/', {
            controller: 'ListCtrl',
            templateUrl:'/cookbook/views/main.html'
        }).when('/new', {
            controller: 'NewCtrl',
            templateUrl:'/cookbook/views/main.html'
        }).otherwise({redirectTo:'/'});
}]);

app.controller('ListCtrl', ['$scope',
    function($scope) {
    }]);


app.controller('NewCtrl', ['$scope', '$location',
    function($scope, $location) {

    }]);

