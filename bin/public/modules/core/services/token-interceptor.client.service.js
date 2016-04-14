'use strict';

angular.module('core').factory('tokenInterceptor', ['$q', '$window', function($q, $window) {
    var tokenName = 'access_token';
    return {
        request: function(httpConfig) {
            var token = $window.sessionStorage[tokenName];
            if (token != null && token != "") {
                httpConfig.headers['Authorization'] = 'Bearer ' + token;
            }
            return httpConfig;
        },
        responseError: function(response) {
        	if (response.status == 406) {
        		$window.sessionStorage[tokenName] = "";
        		$window.location = "/";
                return $q.defer();                
        	} else {
        		return $q.reject(response);	
        	}
        	
            
        }
    };
}]);