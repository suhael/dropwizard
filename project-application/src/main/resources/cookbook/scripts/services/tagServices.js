'use strict';

var tagServices = angular.module('tag.services',
    ['ngResource']);

tagServices.factory('Tag', ['$resource',
    function($resource) {
        return $resource('/api/tag/:id', {id: '@id'});
    }]);

tagServices.factory('MultiTagLoader', ['Tag', '$q',
    function(Tag, $q) {
        return function() {
            var delay = $q.defer();
            Tag.query(function(tags) {
                delay.resolve(tags);
            }, function() {
                delay.reject('Unable to fetch tags');
            });
            return delay.promise;
        };
    }]);

tagServices.factory('TagLoader', ['Tag', '$route', '$q',
    function(Tag, $route, $q) {
        return function() {
            var delay = $q.defer();
            Tag.get({id: $route.current.params.tagId}, function(tag) {
                delay.resolve(tag);
            }, function() {
                delay.reject('Unable to fetch tag '  + $route.current.params.tagId);
            });
            return delay.promise;
        };
    }
]);