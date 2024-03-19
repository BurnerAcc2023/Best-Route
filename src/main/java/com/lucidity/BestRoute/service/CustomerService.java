package com.lucidity.BestRoute.service;

import  com.lucidity.BestRoute.entity.Customer;
import org.mapstruct.Named;

public interface CustomerService {
    @Named("getCustomer")
    Customer getCustomerById(Long id);
}
