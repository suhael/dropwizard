/**
 * Created by sakhtar on 12/01/2015.
 */
var app = angular.module('cookbook', ['ngRoute', 'cookbook.services']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/test/', {
            controller: 'ListCtrl',
            resolve: {
                recipes: ["IngredientsLoader", function(IngredientsLoader) {
                    return IngredientsLoader();
                }]
            },
            templateUrl:'/views/list.html'
        }).otherwise({redirectTo:'/'});
}]);

app.controller('ListCtrl', ['$scope', 'ingredients',
    function($scope, ingredients) {
        console.log("here");
        $scope.ingredients = ingredients;
    }]);