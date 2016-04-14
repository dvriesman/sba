'use strict';

//Setting up route
angular.module('core').config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function($stateProvider, $urlRouterProvider, $locationProvider) {

		$urlRouterProvider.otherwise('/');

		$stateProvider.
		state('home', {
			url: '/',
			templateUrl: 'modules/core/views/login.client.view.html',
         resolve: {
        	 
             verifyAlreadyLogged: ['$window', '$rootScope', '$location', function($window, $rootScope, $location) {
                 if ($window.sessionStorage['access_token'] != null && $window.sessionStorage['access_token'] != "") {
                     var authentication = angular.fromJson($window.sessionStorage['user_info']);
                	 if (authentication != null) {
                		 if (authentication.user.name == 'admin') {
                			 $location.path('/void');		 
                		 } else {
                			 $location.path('/void');
                		 }
                	 }
                 }
             }]
		
         	}
		}).
		state('core', {
			url: '/core',
			templateUrl: 'modules/core/views/core.client.view.html'
		}).
        state('void', {
            parent: 'core',
            url: '^/void',
            views: {
                'header': {templateUrl: 'modules/core/views/void-header.html'},
                'content': {templateUrl: 'modules/core/views/void-content.html'}
            },
            data: {
                access: 'ADMIN'
            }
        });
	}
]);
