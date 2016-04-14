'use strict';

angular.module('core').controller('HeaderController', ['$scope', '$http', '$window', '$state', '$rootScope', 'Menus', '$modal',
	function($scope, $http, $window, $state, $rootScope, Menus, $modal) {

		$scope.version = "0.0.0.0";
		$scope.isCollapsed = false;
		$scope.menu = Menus.getMenu('topbar');

		$scope.toggleCollapsibleMenu = function() {
			$scope.isCollapsed = !$scope.isCollapsed;
		};

		// Collapsing the menu after navigation
		$scope.$on('$stateChangeSuccess', function() {
			$scope.isCollapsed = false;
		});

		$scope.init = function() {
            
			$scope.authentication = $rootScope.authentication;
            
            $http.get("/updater/version").success(function(result) {
            	$scope.version = result;
            });
            
		};

        $scope.logoff = function() {
            $window.sessionStorage['access_token'] = "";
            $window.sessionStorage['user_info'] = undefined;
            $state.go('home');
        }
        
        $scope.updateVersion = function() {
    		$http.get("/updater/update").success(function(result) {
    			
    			
    			var modalInstance = $modal.open({
                    templateUrl: 'modules/core/views/message-modal.client.view.html',
                    controller: 'MessageModalCtrl',
                    size: 'lg',
                    resolve: {
                   	 message: function () {

	             			if (result == 0) {
	             				return "Esta versão já é a mais rescente.";
	             			} else {
	             				if (result == 1) {
	             					return "Atualizando a versão, aguarde alguns instantes e atualize a página (CTRL+F5).";
	             				} else {
	             					 return "Não foi possível atualizar a versão, entre em contato com o suporte técnico.";
	             				}
	             			}
                            
                        }
                    }
                });
    		
                modalInstance.result.then(function () {
            
                }, function () {
            
                });
            	
    			
    		}).error(function (data, status, headers, config) {
    			
    			var modalInstance = $modal.open({
                    templateUrl: 'modules/core/views/message-modal.client.view.html',
                    controller: 'MessageModalCtrl',
                    size: 'lg',
                    resolve: {
                   	 message: function () {
                   		 return "Error: " + data + " " + status;
                        }
                    }
                });
    		
                modalInstance.result.then(function () {
            
                }, function () {
            
                });
    			

    		});
        }
	}
]);