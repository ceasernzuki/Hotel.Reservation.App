package service;
import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public class CustomerService {
    Collection<Customer>customers = new ArrayList<>();
    private static CustomerService customerService = null;
    private CustomerService(){

    }

    public static CustomerService getInstance() {

        if (null == customerService) {
            customerService = new CustomerService();
        }
        return customerService;
    }
    public void addCustomer(String email, String firstName, String lastName) {

        Customer customer = new Customer(firstName + " " ,lastName + " ", email);
        customers.add(customer);
    }

    public Customer getCustomer(String customerEmail) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(customerEmail).matches()){
            throw new IllegalArgumentException("Error, Invalid email");
        };
        Customer found = customers.stream()
                .filter(customer ->customerEmail.equals(customer.getEmail()))
                .findAny()
                .orElse(null);
        return found;

    }
    public Collection<Customer>getAllCustomers(){
        return customers;
    }
}
