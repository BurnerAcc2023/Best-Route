package com.lucidity.BestRoute.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lucidity.BestRoute.entity.Customer;
import com.lucidity.BestRoute.exception.NotFoundException;
import com.lucidity.BestRoute.service.CustomerService;
import com.lucidity.BestRoute.util.FileReaderUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private List<Customer> customerList;
    @PostConstruct
    public void init() {
        TypeReference<List<Customer>> typeReference = new TypeReference<>() {};
        customerList = FileReaderUtils.read("ExampleCustomerData.json",typeReference);
    }
    @Override
    public Customer getCustomerById(Long id) {
        return customerList.stream()
                .filter(customer -> customer.getCustomerId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Customer Not Found"));
    }

}
