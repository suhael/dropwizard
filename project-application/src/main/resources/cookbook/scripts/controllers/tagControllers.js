'use strict';

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/tag', {
            controller: 'TagListCtrl',
            resolve: {
                tags: ["MultiTagLoader", function(MultiTagLoader) {
                    return MultiTagLoader();
                }]
            },
            templateUrl:'/cookbook/views/tag/list.html'
        })
        .when('/tag/create', {
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