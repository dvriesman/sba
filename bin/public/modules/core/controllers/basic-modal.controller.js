angular.module('core').controller('BasicModalCtrl', ['$scope','$modalInstance','items' ,function ($scope, $modalInstance, items) {

    $scope.items = items;
    $scope.selected = null;

    $scope.ok = function () {
        $modalInstance.close($scope.selected);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.selectItem = function(value) {
        $scope.selected = value;
    }

    $scope.isActive = function(value) {
        if (value == $scope.selected) {
            return "active";
        } else {
            return "";
        }
    };

}]);
