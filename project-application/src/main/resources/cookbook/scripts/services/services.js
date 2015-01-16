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
