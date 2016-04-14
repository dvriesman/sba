'use strict';

var ApplicationConfiguration = (function() {

	var applicationModuleName = 'sba';
	var applicationModuleVendorDependencies = ['ngResource', 'ui.router', 'ui.bootstrap', 'ui.utils', 'xeditable'];

	var registerModule = function(moduleName, dependencies) {
		angular.module(moduleName, dependencies || []);
		angular.module(applicationModuleName).requires.push(moduleName);
	};

	return {
		applicationModuleName: applicationModuleName,
		applicationModuleVendorDependencies: applicationModuleVendorDependencies,
		registerModule: registerModule
	};
})();
