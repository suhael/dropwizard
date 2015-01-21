'use strict';

var ingredientServices = angular.module('ingredient.services',
    ['ngResource']);

ingredientServices.factory('Ingredient', ['$resource',
    function($resource) {
        return $resource('/api/ingredient/:id', {id: '@id'});
    }]);


ingredientServices.factory('MultiIngredientLoader', ['Ingredient', '$q',
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

ingredientServices.factory('IngredientLoader', ['Ingredient', '$route', '$q',
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