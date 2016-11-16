var app = angular.module("app", ['ngRoute', 'ngAnimate', 'ui.bootstrap'])
    .config(
        function($httpProvider, $routeProvider) {


            $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

            $routeProvider.when('/', {
                controller: 'home',
                templateUrl : '/index.html'
            }).when('/contractors', {
                controller: 'contractorsContrl',
                templateUrl : '/admin/contractors.html'
            }).otherwise({
                redirectTo: '/'
            });

});


app.service('CurrentStates', function($http) {
    this.title = true;
    this.author = true;
    this.date = true;
    this.submit = true;
    this.text = true;

});


app.controller("home", function($http, $location, $scope ,$window, $route, CurrentStates, $uibModal) {

    $scope.search = function (form_valid) {
        if (form_valid) {
            var selections = {
                title : CurrentStates.title,
                author : CurrentStates.author,
                date : CurrentStates.date,
                submit : CurrentStates.submit,
                text : CurrentStates.text
            };

            console.log(selections);
            $http.post('/search?query=' + $scope.query, selections)
                .success(function (data) {
                    $scope.results = data;
                }).error(function () {
                    $scope.results = {};

            });
        }
    };



    $scope.openSettingsWindow = function () {

        var modalInstance = $uibModal.open({
            templateUrl: '/deleteWindow.html',
            controller: 'ModalInstanceCtrl'
        });


    };


});

app.controller("ModalInstanceCtrl", function ($scope, $uibModalInstance, $route, $http, CurrentStates) {

    $scope.title = CurrentStates.title;
    $scope.author = CurrentStates.author;
    $scope.date = CurrentStates.date;
    $scope.text = CurrentStates.text;
    $scope.submit = CurrentStates.submit;

    $scope.cancel = function () {
        CurrentStates.title = $scope.title;
        CurrentStates.author = $scope.author;
        CurrentStates.date = $scope.date;
        CurrentStates.text = $scope.text;
        CurrentStates.submit = $scope.submit;
        
        $uibModalInstance.dismiss('cancel');
    };
});

app.controller("userContrl", function($http, $location, $scope) {
    $http.get('/admin/api/getUsers')
        .success(function (data) {
            $scope.users = data;
        });
});



