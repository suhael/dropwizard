'use strict';

var services = angular.module('cookbook.services',
    ['ngResource']);

services.factory('Recipe', ['$resource',
    function($resource) {
  return $resource('/api/recipe/:id', {id: '@id'});
}]);