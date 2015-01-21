'use strict';

var app = angular.module('ingredient',
    ['ngRoute', 'ingredient.directives', 'ingredient.services']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'IngredientListCtrl',
            resolve: {
                ingredients: ["MultiIngredientLoader", function(MultiIngredientLoader) {
                    return MultiIngredientLoader();
                }]
            },
            templateUrl:'/cookbook/views/ingredient/list.html'
        })
        .when('/create', {
            controller: 'NewIngredientCtrl',
            templateUrl:'/cookbook/views/ingredient/add.html'
        })
        .when('/:ingredientId', {
            controller: 'ViewIngredientCtrl',
            resolve: {
                ingredient: ["IngredientLoader", function(IngredientLoader) {
                    return IngredientLoader();
                }]
            },
            templateUrl:'/cookbook/views/ingredient/view.html'
        })
        .otherwise({redirectTo:'/'});
}]);

app.controller('IngredientListCtrl', ['$scope', '$location', 'ingredients',
    function($scope, $location, ingredients) {
        $scope.ingredients = ingredients;

        $scope.remove = function() {
            $scope.ingredient.$delete(function(){
                $location.path('/');
            });

        };
    }
]);

app.controller('NewIngredientCtrl', ['$scope', '$location', 'Ingredient',
    function($scope, $location, Ingredient) {
        $scope.ingredient = new Ingredient({
            "title":""
        });

        $scope.save = function() {
            $scope.ingredient.$save(function(ingredient) {
                $location.path('/' + ingredient.id);
            });
        };
    }
]);

app.controller('ViewIngredientCtrl', ['$scope', '$location', 'ingredient',
    function($scope, $location, ingredient) {
        $scope.ingredient = ingredient;

        $scope.edit = function() {
            $location.path('/edit/' + ingredient.id);
        };
    }
]);