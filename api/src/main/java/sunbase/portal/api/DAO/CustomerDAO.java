package sunbase.portal.api.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sunbase.portal.api.model.Customer;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c WHERE " +
            "c.firstName LIKE %:keyword% OR " +
            "c.lastName LIKE %:keyword% OR " +
            "c.street LIKE %:keyword% OR " +
            "c.address LIKE %:keyword% OR " +
            "c.city LIKE %:keyword% OR " +
            "c.state LIKE %:keyword% OR " +
            "c.email LIKE %:keyword% OR " +
            "c.phone LIKE %:keyword%")
    List<Customer> SearchByCustomQuery(String keyword, Pageable pageable);

    List<Customer> findByFirstName(String firstName,Pageable pageable);
    List<Customer> findByLastName(String lastName,Pageable pageable);
    List<Customer> findByStreet(String street,Pageable pageable);
    List<Customer> findByAddress(String address,Pageable pageable);
    List<Customer> findByCity(String city,Pageable pageable);
    List<Customer> findByState(String state,Pageable pageable);
    List<Customer> findByEmail(String email,Pageable pageable);
    List<Customer> findByPhone(String phone,Pageable pageable);

}
