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

        $scope.save = function() {
            $scope.recipe.$save(function(recipe) {
                $location.path('/recipe/' + recipe.id);
            });
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