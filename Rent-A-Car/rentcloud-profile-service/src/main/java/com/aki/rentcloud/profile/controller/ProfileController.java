package com.aki.rentcloud.profile.controller;

import com.aki.rentcloud.commons.model.Customer;
import com.aki.rentcloud.profile.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/services")
public class ProfileController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public Customer customer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}
}
