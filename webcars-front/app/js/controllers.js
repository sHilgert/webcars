angular.module('WebCarsFront.controllers', []).
  // Home Controller
  controller('homeController', function($scope) {
    
  }).

   // Users Controller
  controller('userController', function($rootScope, $scope, userService) {
    userService.getAllUsers($rootScope.token).success(function(data, status, headers, config){
      $scope.users = data;
      console.log($scope.users);
    }).error(function(data, status, headers, config){
      swal("Deu pau","Erro na hora de pegar os usuarios", "error");
      console.log("deu pau");
    });

    $scope.removeUser = function (index) {
      var deletedUser = $scope.users[index];
      console.log(deletedUser);
      userService.deleteUser(deletedUser.nome, $rootScope.token).success(function(data, status, headers, config){
        swal("Trabalho nota 10","Usuario deletado com sucesso", "success");
        console.log("usuario deletado");
      }).error(function(data, status, headers, config){
        swal("Deu pau","Erro na hora de deletar o usuario", "error");
        console.log("nao deletou, deu pau");
      });
      
      $scope.users.splice(index, 1);
    };
  }).

  // Login Controller
  controller('loginController', function($rootScope ,$scope, $state ,loginService) {
    
    $scope.logout = function (){
      $rootScope.isLogged = false;
      $rootScope.token = "";
      $rootScope.isAdmin = false;

      $state.go('home');

      console.log("fez o logout");
    };

    $scope.login = function() {
      loginService.login($scope.dashboard.user)
        .success(function(data, status, headers, config) {
          //console.log(data);
          //console.log(status);
          $rootScope.token = headers('token');
          if (data.autorizacoes[0].authority == "ROLE_ADMIN")
            $rootScope.isAdmin = true;
          else
            $rootScope.isAdmin = false;
          $rootScope.isLogged = true;

          console.log("fez o login " + $rootScope.token);
          $state.go('cars');
          //console.log(headers('token'));
          //console.log(config);
        })
        .error(function(data, status, headers, config) {
          $rootScope.isLogged = false;
          $rootScope.token = "";
          $rootScope.isAdmin = false;
          swal("Deu pau","Usuario não encontrado", "error");
          console.log("usuario invalido");
        });
    };
  }).
  
  // Register Controller
  controller('registerController', function($scope, $state ,userService) {
    
    $scope.register = function() {
      userService.register($scope.dashboard.user)
        .success(function(data, status, headers, config) {
          //console.log(data);
          //console.log(status);
          //loginService.isLogged = true;
          //loginService.token = headers('token');
          //loginService.auth = data.autorizacoes;
          $state.go('login');
          swal("Trabalho nota 10","Usuario cadastrado com sucesso", "success");
          console.log("usuario cadastrado com sucesso");
          //console.log(headers('token'));
          //console.log(config);
        })
        .error(function(data, status, headers, config) {
          swal("Deu pau","Username invalido", "error");
          console.log("username invalido, deu pau")
        });
    }
  }).
    
  // Car Controller
  controller('carController', function($rootScope, $scope, $state , carService) {
    
    carService.getAllCars().success(function(data, status, headers, config){
      $scope.cars = data;
      console.log($scope.cars);
    }).error(function(data, status, headers, config){
      console.log("deu pau");
    });


    $scope.register = function() {
      carService.register($scope.dashboard.car, $rootScope.token)
        .success(function(data, status, headers, config) {
          //console.log(data);
          //console.log(status);
          //loginService.isLogged = true;
          //loginService.token = headers('token');
          //loginService.auth = data.autorizacoes;
          $state.go('cars');
          console.log("carro cadastrado com sucesso");
          swal("Trabalho nota 10","Carro cadastrado com sucesso", "success");
          //console.log(headers('token'));
          //console.log(config);
        })
        .error(function(data, status, headers, config) {
          $state.go('login');
          swal("Deu pau","Usuario não logado", "error");
          console.log("usuario nao logado");
        });
    }
  })
;