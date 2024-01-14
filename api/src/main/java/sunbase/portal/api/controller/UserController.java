package sunbase.portal.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import sunbase.portal.api.DAO.UserDAO;
import sunbase.portal.api.mapper.LoginMapper;
import sunbase.portal.api.mapper.LoginResponse;
import sunbase.portal.api.service.UserDetailedService;
import sunbase.portal.api.service.UserService;
import sunbase.portal.api.utils.CustomResponse;
import sunbase.portal.api.utils.JwtUtil;
import sunbase.portal.api.model.User;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserDetailedService userDetailedService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;
    @PostMapping("/signup")
    public ResponseEntity<Map> signUp(@RequestBody User user){
        userService.signUp(user);
        return CustomResponse.ok(user);
    }
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<Map> getUser(@PathVariable long userId){
        User user=userService.getUserById(userId);
        if(user!=null) {
            return CustomResponse.ok(user);
        }
        return CustomResponse.conflict("user Not Found");
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody LoginMapper loginRequest){
        try {log.info(String.valueOf(loginRequest));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        }catch(UsernameNotFoundException e){
            // throw new RuntimeException();
            return CustomResponse.conflict("------------------------");
        }catch (Exception e){
            return CustomResponse.conflict(e.getMessage());
        }
        UserDetails userDetails= userDetailedService.loadUserByUsername(loginRequest.getUserName());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUser(userDAO.findByUserName(loginRequest.getUserName()));
        return  CustomResponse.ok(loginResponse);
    }
}
