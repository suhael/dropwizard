'use strict';

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