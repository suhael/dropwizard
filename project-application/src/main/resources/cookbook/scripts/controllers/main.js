'use strict';

var app = angular.module('cookbook',
    ['ngRoute', 'cookbook.directives', 'recipe.services', 'ingredient.services',
        'tag.services', 'mealtime.services', 'ui.bootstrap']);

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
            resolve: {
                ingredients: ["MultiIngredientLoader", function(MultiIngredientLoader) {
                    return MultiIngredientLoader();
                }],
                tags: ["MultiTagLoader", function(MultiTagLoader) {
                    return MultiTagLoader();
                }],
                mealtimes: ["MultiMealTimeLoader", function(MultiMealTimeLoader) {
                    return MultiMealTimeLoader();
                }]
            },
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