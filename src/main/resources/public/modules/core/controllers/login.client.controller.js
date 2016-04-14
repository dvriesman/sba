angular.module('core').controller('LoginController', ['$http', '$scope', '$rootScope', '$state', '$window', 'Base64', 'SetFocus',
    function ($http, $scope, $rootScope, $state, $window, Base64, SetFocus) {
        $scope.errorLogin = false;
        $scope.init = function() {
        	SetFocus("email");
        }
        $scope.login = function () {
        	
        $http({
            method: 'POST',
            url: "oauth/token",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data: {client_id: "spLoader", username: $scope.email, password: $scope.password, grant_type: "password"}
                }).success(function (response, status, headers, config) {
                	
                    	var token = response.access_token || response.data[config.tokenName];
                    
                    	$window.sessionStorage['access_token'] = token;
                        
                        $rootScope.authentication = new AuthenticationInfo([]);
                        $rootScope.authentication.user.id = 1;
                        $rootScope.authentication.user.name = $scope.email;
                        $rootScope.authentication.user.displayName = $scope.email;
                        $rootScope.authentication.user.isAdmin = true;
                        
                        if ($scope.email == 'versao') {
                        	$rootScope.authentication.user.roles.push("ADMIN");	
                        } else {
                        	$rootScope.authentication.user.roles.push("USER");
                        }
                        
                        $window.sessionStorage['user_info'] = angular.toJson($rootScope.authentication); 
                        
                        var isAdmin = true;
                        
                        if ($scope.email == 'admin') {
                        	$state.go('void');
                        } else {
                        	$state.go('void');
                        }
                        
                }).error(function (data, status, headers, config) {
                	$scope.email = "";
                	$scope.password = "";
                    $scope.errorLogin = true;
                    SetFocus("email");
                    $window.sessionStorage['access_token'] = "";
                    
                });
        };
        
}]);

function AuthenticationInfo(args) {
	this.user = args.user || new UserInfo([]);
};

function UserInfo(args) {
	this.id = args.id || undefined;
	this.email = args.email || undefined;
	this.name = args.name || undefined;
	this.displayName = args.displayName || undefined;
	this.isAdmin = args.isAdmin || undefined;
	this.roles = args.roles || [];
};