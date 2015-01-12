/**
 * Created by sakhtar on 12/01/2015.
 */
var services = angular.module('cookbook.services',
    ['ngResource']);

services.factory('Ingredient', ['$resource',
    function($resource) {
        return $resource('/ingredient/:id', {id: '@id'});
    }]);

services.factory('IngredientsLoader', ['Ingredient', '$q',
    function(Ingredient, $q) {
        return function() {
            var delay = $q.defer();
            console.log("im here");
            Ingredient.query(function(ingredients) {
                delay.resolve(ingredients);
            }, function() {
                delay.reject('Unable to fetch ingredients');
            });
            return delay.promise;
        };
    }]);