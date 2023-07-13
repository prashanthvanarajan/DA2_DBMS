package com.project_1;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//To mention that this is a spring boot application we have to add the below annotation --> @Configuration +
//@EnableAutoConfiguration + @ComponentScan
@SpringBootApplication

//Spring Web MVC , Make RESTful services very easy
@RestController //Marks the class as web controller , all them methods in the marked class and will return a JSON response
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args){

        SpringApplication.run(Main.class,args);
    }

    //Creating an API
    @GetMapping
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }
    record NewCustomerRequest(String name,
                              String email,
                              int age;){

    }

    public void addCustomer(NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setAge((request.age()));
        customer.setEmail(request.email);
        customerRepository.save(customer)
    }


}
