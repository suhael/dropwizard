'use strict';

app.controller('RecipeListCtrl', ['$scope', '$location', 'recipes',
    function($scope, $location, recipes) {
        $scope.recipes = recipes;

        $scope.remove = function() {
            $scope.recipe.$delete(function(){
                $location.path('/');
            });

        };
    }
]);

app.controller('NewRecipeCtrl', ['$scope', '$location', 'Recipe', 'ingredients', 'tags', 'mealtimes',
    function($scope, $location, Recipe, ingredients, tags, mealtimes) {
        $scope.recipe = new Recipe({
            "title":""
        });

        $scope.ingredients = ingredients;
        $scope.tags = tags;
        $scope.mealtimes = mealtimes;

        $scope.tselection = {
            ids: {},
            objects: []
        };

        $scope.mselection = {
            ids: {},
            objects: []
        };

        $scope.iselection = {
            ids: {},
            objects: []
        };

        $scope.save = function() {
            $scope.recipe.ingredients = $scope.iselection.objects;
            $scope.recipe.tags = $scope.tselection.objects;
            $scope.recipe.mealtimes = $scope.mselection.objects;
            $scope.recipe.$save(function(recipe) {
                $location.path('/recipe/' + recipe.id);
            });

        };

        $scope.addIngredient = function (obj){
            var index = $scope.ingredients.indexOf(obj)
            $scope.ingredients.splice(index, 1);
            $scope.iselection.objects.push(obj);
        }

        $scope.removeIngredient = function (obj){
            var index = $scope.iselection.objects.indexOf(obj)
            $scope.iselection.objects.splice(index, 1);
            $scope.ingredients.push(obj);
        }

        $scope.$watch(function() {
            return $scope.tselection.ids;
        }, function(value) {
            $scope.tselection.objects = [];
            angular.forEach($scope.tselection.ids, function(v, k) {
                v && $scope.tselection.objects.push(getObjectById($scope.tags,k));
            });
        }, true);

        $scope.$watch(function() {
            return $scope.mselection.ids;
        }, function(value) {
            $scope.mselection.objects = [];
            angular.forEach($scope.mselection.ids, function(v, k) {
                v && $scope.mselection.objects.push(getObjectById($scope.mealtimes,k));
            });
        }, true);



        function getObjectById (list, id) {
            for (var i = 0; i < list.length; i++) {
                if (list[i].id == id) {
                    return list[i];
                }
            }
        };
    }
]);

app.controller('ViewRecipeCtrl', ['$scope', '$location', 'recipe',
    function($scope, $location, recipe) {
        $scope.recipe = recipe;

        $scope.edit = function() {
            $location.path('/recipe/edit/' + recipe.id);
        };
    }
]);