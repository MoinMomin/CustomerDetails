package sunbase.portal.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sunbase.portal.api.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer createCuctomer(Customer customer);
    public Customer updateCuctomer(Customer customer);
    public List<Customer> searchCustomerUsingAnyMatch(String keyword, Pageable pageable);
    public Customer getCuctomer(long id);
    public void deleteCuctomer(long id);
    public List<Customer> searchByProperty( String searchBy,String searchTerm, Pageable pageable);
}
