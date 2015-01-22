'use strict';

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