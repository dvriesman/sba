'use strict';

angular.module('core').config(['$sceDelegateProvider', function ($sceDelegateProvider) {
    $sceDelegateProvider.resourceUrlWhitelist([
        'self',
        'https://fonts.gstatic.com'
    ]);
}]);

angular.module('core').config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('tokenInterceptor');
}]);

angular.module('core').run(['$rootScope', '$state', '$stateParams', '$window', '$http', '$location', '$urlRouter', '$modal',
    function ($rootScope, $state, $stateParams, $window, $http, $location, $urlRouter, $modal) {
        
        var token = $window.sessionStorage['access_token'];
        if (token != null && token != "") {
        	$rootScope.authentication = angular.fromJson($window.sessionStorage['user_info']);
        }

        $rootScope.$on("$stateChangeStart", function (event, to, toParams, from, fromParams) {
            if (to.url != "/") {
                var token = $window.sessionStorage['access_token'];
                if (token != null && token != "") {
                	
                    if (to.data != null) {
                        if (to.data.access != null) {
                        	
                        	var found = false;
                        	for (var i = 0; i < $rootScope.authentication.user.roles.length; i++) {
                        		if (to.data.access == $rootScope.authentication.user.roles[i]) {
                        			found = true;
                        		}
                        	}
                        	if (!found) {
                        		event.preventDefault();
                        	}
                        }
                    }
                } else {
                    
                    event.preventDefault();
                    $location.path('/');
                }                
                
            }

        });
        
        
        $rootScope.showError = function(message) {
			 var modalInstance = $modal.open({
                templateUrl: 'modules/core/views/message-modal.client.view.html',
                controller: 'MessageModalCtrl',
                size: 'lg',
                resolve: {
               	 message: function () {
                        return "Error: " + message;
                    }
                }
            });
            modalInstance.result.then(function () {
                $log.info('ok');
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
		}


    }
]);

angular.module('core').run(['Menus',
	function(Menus) {
		Menus.addMenuItem('topbar', 'Void Admin', 'void_adm', 'item', '/void', false, ['ADMIN']);
		Menus.addMenuItem('topbar', 'Void User', 'void_user', 'item', '/void', false, ['USER']);
	}
]);

angular.module('core').run(['editableOptions', 'editableThemes', function(editableOptions, editableThemes) {
    editableThemes.bs3.inputClass = 'input-sm';
    editableThemes.bs3.buttonsClass = 'btn-sm';
    editableOptions.theme = 'bs3';
}]);

