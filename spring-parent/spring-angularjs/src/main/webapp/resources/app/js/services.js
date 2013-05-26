'use strict';

/* Services */
angular.module('myApp.services', [])
.factory('customerService', function($http) {
	var selectedCustomer = null;
	
	return {
		getCustomer: function(){
			return selectedCustomer;
		},
		setCustomer:function(customer){
			selectedCustomer = customer;
		}
	};
  });
