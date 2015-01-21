'use strict';

var mealTimeServices = angular.module('mealtime.services',
    ['ngResource']);

mealTimeServices.factory('MealTime', ['$resource',
    function($resource) {
        return $resource('/api/mealtime/:id', {id: '@id'});
    }]);

mealTimeServices.factory('MultiMealTimeLoader', ['MealTime', '$q',
    function(MealTime, $q) {
        return function() {
            var delay = $q.defer();
            MealTime.query(function(mealtimes) {
                delay.resolve(mealtimes);
            }, function() {
                delay.reject('Unable to fetch mealtimes');
            });
            return delay.promise;
        };
    }]);