angular.module('WebCarsFront', [
  'WebCarsFront.services',
  'WebCarsFront.controllers',
  'ngRoute',
  'ui.router'
]).
config(function($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise('/');

  $stateProvider.
  state('home', {
    url: '/',
    controller: 'homeController',
    templateUrl: './partials/home.html'
  })
  .state('register', {
    url: '/register',
    controller: 'registerController',
    templateUrl: './partials/register.html'
  })
  .state('user', {
    url: '/user',
    controller: 'userController',
    templateUrl: './partials/user.html'
  })
  .state('newCar', {
    url: '/novoCarro',
    controller: 'carController',
    templateUrl: './partials/newCar.html'
  })
  .state('cars', {
    url: '/carros',
    controller: 'carController',
    templateUrl: './partials/cars.html'
  })
  .state('login', {
    url: '/login',
    controller: 'loginController',
    templateUrl: './partials/login.html'
  });
});