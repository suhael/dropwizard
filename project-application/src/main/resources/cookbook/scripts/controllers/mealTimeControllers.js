'use strict';

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/mealtime', {
            controller: 'MealTimeListCtrl',
            resolve: {
                mealtimes: ["MultiMealTimeLoader", function(MultiMealTimeLoader) {
                    return MultiMealTimeLoader();
                }]
            },
            templateUrl:'/cookbook/views/mealtime/list.html'
        })
        .when('/mealtime/create', {
            controller: 'NewMealTimeCtrl',
            templateUrl:'/cookbook/views/mealtime/add.html'
        })
        .otherwise({redirectTo:'/'});
}]);

app.controller('MealTimeListCtrl', ['$scope', '$location', 'mealtimes',
    function($scope, $location, mealtimes) {
        $scope.mealtimes = mealtimes;
    }
]);

app.controller('NewMealTimeCtrl', ['$scope', '$location', 'MealTime',
    function($scope, $location, MealTime) {
        $scope.mealtime = new MealTime({
            "title":""
        });

        $scope.save = function() {
            $scope.mealtime.$save(function(mealtime) {
                $location.path('/');
            });
        };
    }
]);