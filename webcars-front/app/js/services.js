angular.module('WebCarsFront.services', [])
  .factory('loginService', function($http) {

    var loginAPI = {};

    loginAPI.login = function(user){
      return $http({
        method: 'POST',
        url: 'http://localhost:8080/webcars-back/login',
        headers:{
            "Content-Type": "application/json"
        },
        data: user
      });
    }
  return loginAPI;
  })

  .factory('carService', function($http) {

    var carAPI = {};

    carAPI.getAllCars = function(){
      return $http({
        method: 'GET',
        url: 'http://localhost:8080/webcars-back/car',
        headers:{
          "Content-Type": "application/json"
        }
      });
    }

    carAPI.register = function(car, token){
      return $http({
        method: 'POST',
        url: 'http://localhost:8080/webcars-back/car',
        headers:{
          "Content-Type": "application/json",
          "Authorization": "Bearer " + token
        },
        data: car
      });
    }
  return carAPI;
  })

  .factory('userService', function($http) {

    var userAPI = {};

    userAPI.getAllUsers = function(token){
      return $http({
        method: 'GET',
        url: 'http://localhost:8080/webcars-back/usuario',
        headers:{
          "Authorization": "Bearer " + token
        }
      });
    }
    userAPI.deleteUser = function(nome, token){
      return $http({
        method: 'DELETE',
        url: 'http://localhost:8080/webcars-back/usuario',
        headers:{
          "Content-Type": "application/json",
          "Authorization": "Bearer " + token
        },
        data: nome
      });
    }

    userAPI.register = function(user){
      return $http({
        method: 'POST',
        url: 'http://localhost:8080/webcars-back/usuario',
        headers:{
          "Content-Type": "application/json"
        },
        data: user
      });
    }

  return userAPI;
  })
;