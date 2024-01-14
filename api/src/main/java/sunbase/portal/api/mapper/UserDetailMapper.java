package sunbase.portal.api.mapper;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sunbase.portal.api.model.User;
import java.util.Collection;
import java.util.HashSet;

@Service
@NoArgsConstructor
public class UserDetailMapper implements UserDetails {

    User user;
    public UserDetailMapper(User user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> grantedAuthority=new HashSet<>();
        grantedAuthority.add(new SimpleGrantedAuthority(this.user.getRole()));
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


