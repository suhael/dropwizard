'use strict';

var app = angular.module('tag',
    ['ngRoute', 'tag.directives', 'tag.services']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/', {
            controller: 'TagListCtrl',
            resolve: {
                tags: ["MultiTagLoader", function(MultiTagLoader) {
                    return MultiTagLoader();
                }]
            },
            templateUrl:'/cookbook/views/tag/list.html'
        })
        .when('/create', {
            controller: 'NewTagCtrl',
            templateUrl:'/cookbook/views/tag/add.html'
        })
        .otherwise({redirectTo:'/'});
}]);

app.controller('TagListCtrl', ['$scope', '$location', 'tags',
    function($scope, $location, tags) {
        $scope.tags = tags;
    }
]);

app.controller('NewTagCtrl', ['$scope', '$location', 'Tag',
    function($scope, $location, Tag) {
        $scope.tag = new Tag({
            "title":""
        });

        $scope.save = function() {
            $scope.tag.$save(function(tag) {
                $location.path('/');
            });
        };
    }
]);