'use strict';

angular.module('myApp.controllers', []).controller('CustomerController',
    function ($scope, $http, $location, customerService) {

        $scope.getCustomers = function () {
            $http({
                method: 'GET',
                url: 'rest/customer.json'
            }).success(function (data, status, headers, config) {
                $scope.customers = data;
            }).error(function (data, status, headers, config) {
                if (404 == status) {
                    console.log("ERROR 404");
                    $location.path('/notFound');
                } else if (500 == status) {
                    $location.path('/error');
                }
            });
        };

        $scope.create = function () {
            $http({
                method: 'PUT',
                url: 'rest/customer.json',
                data: $scope.customer
            }).success(function (data, status, headers, config) {
                var alert = {
                    type: 'success',
                    msg: 'Successfully inserted'
                };
                $scope.addAlert(alert);
                $scope.customers.push($scope.customer);
            }).error(function (data, status, headers, config) {
                if (404 == status) {
                    console.log("ERROR 404");
                    $location.path('/notFound');
                } else if (500 == status) {
                    $location.path('/error');
                }
            });
            $scope.shouldBeOpen = false;
        };

        $scope.update = function () {
            $http({
                method: 'POST',
                url: 'rest/customer/' + $scope.customer.id + '.json',
                data: $scope.customer
            }).success(function (data, status, headers, config) {
                var alert = {
                    type: 'success',
                    msg: 'Successfully updated'
                };
                $scope.addAlert(alert);
            }).error(function (data, status, headers, config) {
                if (404 == status) {
                    console.log("ERROR 404");
                    $location.path('/notFound');
                } else if (500 == status) {
                    $location.path('/error');
                }
            });
            $scope.shouldBeOpen = false;
        };

        $scope.remove = function () {
            $http({
                method: 'DELETE',
                url: 'rest/customer/' + $scope.customer.id + '.json'
            }).success(function (data, status, headers, config) {
                var alert = {
                    type: 'success',
                    msg: 'Successfully deleted'
                };
                $scope.addAlert(alert);
            }).error(function (data, status, headers, config) {
                if (404 == status) {
                    console.log("ERROR 404");
                    $location.path('/notFound');
                } else if (500 == status) {
                    $location.path('/error');
                }
            });
            $scope.shouldBeOpen = false;
        };

        $scope.openUpdate = function (customer) {
            $scope.customer = customer;
            $scope.shouldBeOpen = true;
            $scope.isModalUpdate = true;
        };

        $scope.openCreate = function () {
            $scope.shouldBeOpen = true;
            $scope.isModalUpdate = false;
        };

        $scope.close = function () {
            $scope.closeMsg = 'I was closed at: ' + new Date();
            $scope.shouldBeOpen = false;
            $scope.customer = null;
        };

        $scope.opts = {
            backdropFade: true,
            dialogFade: true
        };

        $scope.addAlert = function (a) {
            if ($scope.alerts == null) {
                $scope.alerts = [];
            }
            $scope.alerts.push(a);
        };
        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

        $scope.goToBills = function (customer) {
            customerService.setCustomer(customer);
            $location.path("/bills");
        };
    }).controller('BillsController',
    function ($scope, $http, $location, customerService) {
        $scope.getCustomerBills = function () {
            $scope.customer = customerService.getCustomer();
            $http({
                method: 'GET',
                url: 'rest/bill/' + $scope.customer.id + '.json'
            }).success(function (data, status, headers, config) {
                $scope.bills = data;
            }).error(function (data, status, headers, config) {
                if (404 == status) {
                    console.log("ERROR 404");
                    $location.path('/notFound');
                } else if (500 == status) {
                    $location.path('/error');
                }
            });
        };

        $scope.openUpdate = function (bill) {
            $scope.bill = bill;
            $scope.shouldBeOpen = true;
        };

        $scope.openCreate = function () {
            $scope.shouldBeOpen = true;
        };

        $scope.close = function () {
            $scope.closeMsg = 'I was closed at: ' + new Date();
            $scope.shouldBeOpen = false;
        };

        $scope.opts = {
            backdropFade: true,
            dialogFade: true
        };

    }).controller('ProductServiceImpl',
    function ($scope, $http, $location, customerService) {

        $scope.getProducts = function () {
            $http({
                method: 'GET',
                url: 'rest/product.json'
            }).success(function (data, status, headers, config) {
                $scope.products = data;
            }).error(function (data, status, headers, config) {
                if (404 == status) {
                    console.log("ERROR 404");
                    $location.path('/notFound');
                } else if (500 == status) {
                    $location.path('/error');
                }
            });
        };

        $scope.create = function () {
            $http({
                method: 'PUT',
                url: 'rest/product.json',
                data: $scope.product
            }).success(function (data, status, headers, config) {
                var alert = {
                    type: 'success',
                    msg: 'Successfully inserted'
                };
                $scope.addAlert(alert);
                $scope.products.push($scope.product);
            }).error(function (data, status, headers, config) {
                if (404 == status) {
                    console.log("ERROR 404");
                    $location.path('/notFound');
                } else if (500 == status) {
                    $location.path('/error');
                }
            });
            $scope.shouldBeOpen = false;
        };

        $scope.update = function () {
            $http({
                method: 'POST',
                url: 'rest/product/' + $scope.product.id + '.json',
                data: $scope.product
            }).success(function (data, status, headers, config) {
                var alert = {
                    type: 'success',
                    msg: 'Successfully updated'
                };
                $scope.addAlert(alert);
            }).error(function (data, status, headers, config) {
                if (404 == status) {
                    console.log("ERROR 404");
                    $location.path('/notFound');
                } else if (500 == status) {
                    $location.path('/error');
                }
            });
            $scope.shouldBeOpen = false;
        };

        $scope.remove = function () {
            $http({
                method: 'DELETE',
                url: 'rest/product/' + $scope.product.id + '.json'
            }).success(function (data, status, headers, config) {
                var alert = {
                    type: 'success',
                    msg: data
                };
                $scope.addAlert(alert);
            }).error(function (data, status, headers, config) {
                var alert = {
                    type: 'error',
                    msg: data
                };
                $scope.addAlert(alert);
            });
            $scope.shouldBeOpen = false;
        };

        $scope.openUpdate = function (product) {
            $scope.product = product;
            $scope.shouldBeOpen = true;
            $scope.isModalUpdate = true;
        };

        $scope.openCreate = function () {
            $scope.shouldBeOpen = true;
            $scope.isModalUpdate = false;
        };

        $scope.close = function () {
            $scope.shouldBeOpen = false;
            $scope.product = null;
        };

        $scope.opts = {
            backdropFade: true,
            dialogFade: true
        };

        $scope.addAlert = function (a) {
            if ($scope.alerts == null) {
                $scope.alerts = [];
            }
            $scope.alerts.push(a);
        };
        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

    });