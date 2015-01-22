'use strict';

var rceipeServices = angular.module('recipe.services',
    ['ngResource']);

rceipeServices.factory('Recipe', ['$resource',
    function($resource) {
        return $resource('/api/recipe/:id', {id: '@id'});
    }]);

rceipeServices.factory('MultiRecipeLoader', ['Recipe', '$q',
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

rceipeServices.factory('RecipeLoader', ['Recipe', '$route', '$q',
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