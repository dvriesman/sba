angular.module('core').controller('MessageModalCtrl', ['$scope','$modalInstance','message', function ($scope, $modalInstance, message) {

    $scope.message = message;$scope.message = message;

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

}]);
