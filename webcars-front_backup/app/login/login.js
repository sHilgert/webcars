'use strict';

angular.module('myApp.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'login/login.html',
    controller: 'LoginController'
  });
}])

.controller('LoginController', [function($scope, $routeParams) {
  var vm = this;

  $scope.user = {};

  function login(){
    var body = JSON.stringify(temp);
    var request = new http.ClientRequest({
      hostname: conecao,
      path: "/app/chatDescription",
      method: "POST",
      headers: {
          "Content-Type": "application/json",
          "Content-Length": Buffer.byteLength(body)
      }
    });

    request.end(body);
    request.on('response', function (response) {
      console.log('STATUS: ' + response.statusCode);
      console.log('HEADERS: ' + JSON.stringify(response.headers));
      response.setEncoding('utf8');
      response.on('data', function (chunk) {
        console.log('BODY: ' + chunk);
      });
    });
  };

}]);