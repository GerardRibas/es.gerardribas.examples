'use strict';

// Declare app level module which depends on filters, and services
angular.module(
        'myApp',
        [ 'ui.bootstrap', 'myApp.filters', 'myApp.services',
            'myApp.directives', 'myApp.controllers' ]).config(
        [ '$routeProvider', function ($routeProvider) {
            $routeProvider.when('/bills', {
                templateUrl: 'resources/app/partials/customerBills.html',
                controller: 'BillsController'
            });
            $routeProvider.when('/product', {
                templateUrl: 'resources/app/partials/product.html',
                controller: 'ProductServiceImpl'
            });
            $routeProvider.when('/customer', {
                templateUrl: 'resources/app/partials/customer.html',
                controller: 'CustomerController'
            });
            $routeProvider.when('/notFound', {
                templateUrl: 'resources/app/partials/notFound.html'
            });
            $routeProvider.when('/error', {
                templateUrl: 'resources/app/partials/error.html'
            });
            $routeProvider.when('/', {
                templateUrl: 'resources/app/partials/home.html'
            });
        } ]);
