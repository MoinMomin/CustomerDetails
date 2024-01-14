package sunbase.portal.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunbase.portal.api.model.Customer;
import sunbase.portal.api.service.CustomerService;
import sunbase.portal.api.utils.CustomResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/createcustomer")
    public ResponseEntity<Map> createCustomer(@RequestBody Customer customer){
     return CustomResponse.ok(customerService.createCuctomer(customer));
    }
    @PatchMapping ("/updatecustomer")
    public ResponseEntity<Map> updateCustomer(@RequestBody Customer customer){
        Customer customer1=customerService.updateCuctomer(customer);
        if(customer1==null)
            return CustomResponse.conflict("customer with "+customer.getId()+" not available");
        return CustomResponse.ok(customer1);
    }
    //for search bar purpose
    // this api is not used
    @GetMapping("/getcustomerlist")
    public ResponseEntity<Map> getcustomerlist(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        // Sorting configuration
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);

        // Paging configuration
        Pageable pageable = PageRequest.of(page, size, sort);
        // here client send word or number so we can filter it by using all fields matching
       List<Customer> customers=customerService.searchCustomerUsingAnyMatch(keyword,pageable);
      return CustomResponse.ok(customers);
    }
    @GetMapping("/getcustomerbyid/{customerId}")
    public ResponseEntity<Map> getCustomerById(@PathVariable long customerId){
      return  CustomResponse.ok(customerService.getCuctomer(customerId));
    }
    @DeleteMapping("/deletecustomerbyid/{customerId}")
    public ResponseEntity<Map> deleteCustomerById(@PathVariable long customerId){
        customerService.deleteCuctomer(customerId);
        return  CustomResponse.ok("Customer deleted successfully");
    }
    //this is for specific property find with matcher
    @GetMapping("/searchCustomerbyproperty")
    public ResponseEntity<Map> searchCustomerByProperty(   @RequestParam(required = false) String searchBy,   @RequestParam(required = false) String searchTerm, @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "asc") String sortOrder){
        // Sorting configuration
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);

        // Paging configuration
        Pageable pageable = PageRequest.of(page, size, sort);
        return  CustomResponse.ok(customerService.searchByProperty( searchBy, searchTerm,pageable));
    }
}
