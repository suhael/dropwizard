'use strict';

var app = angular.module('admin',
    ['ngRoute', 'cookbook.directives', 'ingredient.services', 'tag.services', 'mealtime.services']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl:'/cookbook/views/welcome.html'
        })
        .when('/tag', {
            controller: 'TagListCtrl',
            resolve: {
                tags: ["MultiTagLoader", function(MultiTagLoader) {
                    return MultiTagLoader();
                }]
            },
            templateUrl:'/cookbook/views/tag/list.html'
        })
        .when('/tag/create', {
            controller: 'NewTagCtrl',
            templateUrl:'/cookbook/views/tag/add.html'
        })
        .when('/mealtime', {
            controller: 'MealTimeListCtrl',
            resolve: {
                mealtimes: ["MultiMealTimeLoader", function(MultiMealTimeLoader) {
                    return MultiMealTimeLoader();
                }]
            },
            templateUrl:'/cookbook/views/mealtime/list.html'
        })
        .when('/mealtime/create', {
            controller: 'NewMealTimeCtrl',
            templateUrl:'/cookbook/views/mealtime/add.html'
        })
        .when('/ingredient', {
            controller: 'IngredientListCtrl',
            resolve: {
                ingredients: ["MultiIngredientLoader", function(MultiIngredientLoader) {
                    return MultiIngredientLoader();
                }]
            },
            templateUrl:'/cookbook/views/ingredient/list.html'
        })
        .when('/ingredient/create', {
            controller: 'NewIngredientCtrl',
            templateUrl:'/cookbook/views/ingredient/add.html'
        })
        .when('/ingredient/:ingredientId', {
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