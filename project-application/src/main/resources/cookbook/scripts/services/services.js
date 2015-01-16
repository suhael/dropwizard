'use strict';

var services = angular.module('cookbook.services',
    ['ngResource']);

services.factory('Ingredient', ['$resource',
    function($resource) {
        return $resource('/api/ingredient/:id', {id: '@id'});
    }]);


services.factory('MultiIngredientLoader', ['Ingredient', '$q',
    function(Ingredient, $q) {
        return function() {
            var delay = $q.defer();
            Ingredient.query(function(ingredients) {
                delay.resolve(ingredients);
            }, function() {
                delay.reject('Unable to fetch ingredients');
            });
            return delay.promise;
        };
    }]);

services.factory('IngredientLoader', ['Ingredient', '$route', '$q',
    function(Ingredient, $route, $q) {
        return function() {
            var delay = $q.defer();
            Ingredient.get({id: $route.current.params.ingredientId}, function(ingredient) {
                delay.resolve(ingredient);
            }, function() {
                delay.reject('Unable to fetch ingredient '  + $route.current.params.ingredientId);
            });
            return delay.promise;
        };
    }]);

services.factory('Recipe', ['$resource',
    function($resource) {
        return $resource('/api/recipe/:id', {id: '@id'});
    }]);

services.factory('MultiRecipeLoader', ['Recipe', '$q',
    function(Recipe, $q) {
        return function() {
            var delay = $q.defer();
            Recipe.query(function(recipes) {
                delay.resolve(recipes);
            }, function() {
                delay.reject('Unable to fetch recipes');
            });
            return delay.promise;
        };
    }]);

services.factory('RecipeLoader', ['Recipe', '$route', '$q',
    function(Recipe, $route, $q) {
        return function() {
            var delay = $q.defer();
            Recipe.get({id: $route.current.params.recipeId}, function(recipe) {
                delay.resolve(recipe);
            }, function() {
                delay.reject('Unable to fetch recipe '  + $route.current.params.recipeId);
            });
            return delay.promise;
        };
    }]);