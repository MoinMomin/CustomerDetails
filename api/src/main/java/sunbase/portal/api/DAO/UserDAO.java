package sunbase.portal.api.DAO;
import sunbase.portal.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {
    public User findByUserName(String userName);
}
