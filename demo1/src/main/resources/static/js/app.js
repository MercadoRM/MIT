var app = angular.module('demo1',['ngRoute','ngResource']);
//https://docs.angularjs.org/tutorial/step_09
app.config( function($routeProvider){
		
		$routeProvider.when('/lista-usuarios',{
			templateUrl : '/templates/lista_usuarios.html',
			controller : 'controladorLista'
		}).when('/registro-usuario',{
			templateUrl : '/templates/registro_usuarios.html',
			controller : 'controladorRegistro'
		}).otherwise({
			redirectTo: '/home',
			templateUrl: '/templates/home.html'
		});
	
	});