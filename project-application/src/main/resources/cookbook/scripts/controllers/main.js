'use strict';

var app = angular.module('cookbook',
    ['ngRoute', 'cookbook.directives', 'recipe.services']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl:'/cookbook/views/welcome.html'
        })
        .when('/recipe', {
            controller: 'RecipeListCtrl',
            resolve: {
                recipes: ["MultiRecipeLoader", function(MultiRecipeLoader) {
                    return MultiRecipeLoader();
                }]
            },
            templateUrl:'/cookbook/views/recipe/list.html'
        })
        .when('/recipe/create', {
            controller: 'NewRecipeCtrl',
            templateUrl:'/cookbook/views/recipe/add.html'
        })
        .when('/recipe/:recipeId', {
            controller: 'ViewRecipeCtrl',
            resolve: {
                recipe: ["RecipeLoader", function(RecipeLoader) {
                    return RecipeLoader();
                }]
            },
            templateUrl:'/cookbook/views/recipe/view.html'
        })
        .otherwise({redirectTo:'/'});
}]);