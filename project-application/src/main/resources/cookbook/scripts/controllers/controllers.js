'use strict';

var app = angular.module('cookbook',
    ['ngRoute', 'cookbook.directives', 'cookbook.services']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/', {
            controller: 'ListCtrl',
            templateUrl:'/cookbook/views/list.html'
        })
        .when('/test', {
            controller: 'TestCtrl',
            templateUrl:'/cookbook/views/test.html'
        })
        .when('/ingredient', {
            controller: 'IngredientListCtrl',
            resolve: {
                ingredients: ["MultiIngredientLoader", function(MultiIngredientLoader) {
                    return MultiIngredientLoader();
                }]
            },
            templateUrl:'/cookbook/views/ingredient/list.html'
        })
        .when('/ingredient/create', {
            controller: 'NewIngredientCtrl',
            templateUrl:'/cookbook/views/ingredient/add.html'
        })
        .when('/ingredient/:ingredientId', {
            controller: 'ViewIngredientCtrl',
            resolve: {
                ingredient: ["IngredientLoader", function(IngredientLoader) {
                    return IngredientLoader();
                }]
            },
            templateUrl:'/cookbook/views/ingredient/view.html'
        })
        .when('/recipe', {
            controller: 'RecipeListCtrl',
            resolve: {
                recipes: ["MultiRecipeLoader", function(MultiRecipeLoader) {
                    return MultiRecipeLoader();
                }]
            },
            templateUrl:'/cookbook/views/recipe/list.html'
        })
        .when('/recipe/create', {
            controller: 'NewRecipeCtrl',
            templateUrl:'/cookbook/views/recipe/add.html'
        })
        .when('/recipe/:recipeId', {
            controller: 'ViewRecipeCtrl',
            resolve: {
                recipe: ["RecipeLoader", function(RecipeLoader) {
                    return RecipeLoader();
                }]
            },
            templateUrl:'/cookbook/views/recipe/view.html'
        })
        .otherwise({redirectTo:'/'});
}]);

app.controller('TestCtrl', ['$scope', '$location',
  function($scope, $location) {
    console.log("testing");
  }]);

app.controller('ListCtrl', ['$scope',
    function($scope) {

}]);

app.controller('IngredientListCtrl', ['$scope', '$location', 'ingredients',
    function($scope, $location, ingredients) {
        $scope.ingredients = ingredients;

        $scope.remove = function() {
            $scope.ingredient.$delete(function(){
                    $location.path('/');
            });

        };
    }
]);

app.controller('NewIngredientCtrl', ['$scope', '$location', 'Ingredient',
    function($scope, $location, Ingredient) {
        $scope.ingredient = new Ingredient({
            "title":""
        });

        $scope.save = function() {
            $scope.ingredient.$save(function(ingredient) {
                $location.path('/ingredient/' + ingredient.id);
            });
        };
    }
]);

app.controller('ViewIngredientCtrl', ['$scope', '$location', 'ingredient',
    function($scope, $location, ingredient) {
        $scope.ingredient = ingredient;

        $scope.edit = function() {
            $location.path('/ingredient/edit/' + ingredient.id);
        };
    }
]);


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

app.controller('NewRecipeCtrl', ['$scope', '$location', 'Recipe',
    function($scope, $location, Recipe) {
        $scope.recipe = new Recipe({
            "title":""
        });

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