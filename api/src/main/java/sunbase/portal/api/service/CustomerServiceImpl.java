package sunbase.portal.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sunbase.portal.api.DAO.CustomerDAO;
import sunbase.portal.api.mapper.SearchByEnum;
import sunbase.portal.api.model.Customer;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerDAO customerDAO;
    @Override
    public Customer createCuctomer(Customer customer) {
       return customerDAO.save(customer);

    }

    @Override
    public Customer updateCuctomer(Customer customer) {
     Optional<Customer> optionalCustomer= customerDAO.findById(customer.getId());
     if(optionalCustomer.isEmpty()){
         return null;
     }
      return  customerDAO.save(customer);
    }
@Override
    public List<Customer> searchCustomerUsingAnyMatch(String keyword, Pageable pageable) {
        return customerDAO.SearchByCustomQuery(keyword, pageable);
    }

    @Override
    public Customer getCuctomer(long id) {
        return customerDAO.findById(id).get();
    }

    @Override
    public void deleteCuctomer(long id) {
         customerDAO.deleteById(id);
    }

    @Override
    public List<Customer> searchByProperty( String searchBy,String searchTerm, Pageable pageable) {
        if (searchBy != null && searchTerm != null && searchTerm!="") {
            if (searchBy.equalsIgnoreCase(SearchByEnum.FIRSTNAME.getDisplayName())) {
                return customerDAO.findByFirstName(searchTerm, pageable);
            } else if (searchBy.equalsIgnoreCase(SearchByEnum.LASTNAME.getDisplayName())) {
                return customerDAO.findByLastName(searchTerm, pageable);
            } else if (searchBy.equalsIgnoreCase(SearchByEnum.STREET.getDisplayName())) {
                return customerDAO.findByStreet(searchTerm, pageable);
            } else if (searchBy.equalsIgnoreCase(SearchByEnum.ADDRESS.getDisplayName())) {
                return customerDAO.findByAddress(searchTerm, pageable);
            } else if (searchBy.equalsIgnoreCase(SearchByEnum.CITY.getDisplayName())) {
                return customerDAO.findByCity(searchTerm, pageable);
            } else if (searchBy.equalsIgnoreCase(SearchByEnum.STATE.getDisplayName())) {
                return customerDAO.findByState(searchTerm, pageable);
            } else if (searchBy.equalsIgnoreCase(SearchByEnum.EMAIL.getDisplayName())) {
                return customerDAO.findByEmail(searchTerm, pageable);
            } else if (searchBy.equalsIgnoreCase(SearchByEnum.PHONE.getDisplayName())) {
                return customerDAO.findByPhone(searchTerm, pageable);
            }
        }
            return  customerDAO.findAll(pageable).getContent();

    }
}
