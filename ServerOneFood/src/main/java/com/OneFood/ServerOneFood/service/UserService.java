package com.OneFood.ServerOneFood.service;

import com.OneFood.ServerOneFood.DTO.UserDTO;
import com.OneFood.ServerOneFood.exception.ErrorAccessDeniedException;
import com.OneFood.ServerOneFood.exception.ErrorExecutionFailedException;
import com.OneFood.ServerOneFood.exception.ErrorNotFoundException;
import com.OneFood.ServerOneFood.model.*;
import com.OneFood.ServerOneFood.reponsitory.RoleRepository;
import com.OneFood.ServerOneFood.reponsitory.UserRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final MyService myService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, MyService myService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.myService = myService;
    }

    public ResponseEntity<ResponseObject> getInfoUser() throws ErrorAccessDeniedException, ErrorNotFoundException {
        Long idUser = myService.getPrincipal();
        if(idUser ==null){
            throw new ErrorAccessDeniedException("Access is denied");
        }
        User user = userRepository.findById(idUser).orElseThrow(() -> new ErrorNotFoundException("Cannot find account with id "));
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Success ", new UserDTO(user)));

    }

    public ResponseEntity<ResponseObject> addNewUser(User newUser) throws ErrorExecutionFailedException {
        User mUser = userRepository.findByUserName(newUser.getUserName());
        if(mUser !=null) throw new ErrorExecutionFailedException("User name has exits !");
        newUser.setUserMoney("0");
        newUser.setEnable(true);
        Role role = roleRepository.findRoleByRoleName("USER").orElse(new Role("USER", new ArrayList<>()));
        newUser.addRole(role);
        User user = userRepository.save(newUser);
        if(user == null)
            throw new ErrorExecutionFailedException("New User create failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"New User successfully created ",new UserDTO(user)));

    }

    public ResponseEntity<ResponseObject> updateUserById(Long id, User newUser) throws ErrorNotFoundException, ErrorExecutionFailedException, ErrorAccessDeniedException {
        User user = userRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find account with id "));
        checkPermission(user);
        //user.setUserEmail(newUser.getUserEmail());
        user.setUserImage(newUser.getUserImage());
        user.setUserName(newUser.getUserName());
        user.setUserSex(newUser.getUserSex());
        user.setUserDateOfBirth(newUser.getUserDateOfBirth());
        //user.setUserNumberPhone(newUser.getUserNumberPhone());

        User updatedUser = userRepository.save(user);
        if(updatedUser == null)
            throw  new ErrorExecutionFailedException("Account update failed ");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Account successfully updated ",new UserDTO(user)));

    }

    public ResponseEntity<ResponseObject> getUserById(Long id) throws ErrorNotFoundException, ErrorAccessDeniedException {
        User user = userRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find account with id "));
        checkPermission(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Find successful account with id "+id,new UserDTO(user)));
    }

    public ResponseEntity<ResponseObject> deleteUserById(Long id) throws ErrorNotFoundException, ErrorAccessDeniedException {
        User user = userRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find account with id "));
        checkPermission(user);
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Delete successful account with id "+id,new UserDTO(user)));
    }

    public ResponseEntity<ResponseObject> removeUserRoleByIdUser(Long id, String newRole) throws ErrorNotFoundException, ErrorExecutionFailedException {
        User user = userRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Cannot find account with id "+id));
        Set<Role> roles = user.getRoles();
        Set<String> nameRoles = roles.stream().map(role -> {return role.getRoleName();}).collect(Collectors.toSet());
        if(! nameRoles.contains(newRole))
            throw new ErrorExecutionFailedException("User hasn't this role "+newRole);
        try {
            roles.stream().forEach(role -> {
                System.out.println(role.getIdRole()+"_________________________");
                if(role.getRoleName().equals(newRole)){
                    user.removeRole(role);
                   // roleRepository.deleteById(role.getIdRole());
                    return;}
            });
            userRepository.save(user);
        }catch (Exception e){
            throw new ErrorExecutionFailedException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"Remove role successful for use with id "+id,new UserDTO(user)));
    }

    public ResponseEntity<ResponseObject> addUserRoleByIdUser(Long id, String newRole) throws ErrorNotFoundException, ErrorExecutionFailedException {
        User user = userRepository.findById(id).orElseThrow(() -> new ErrorNotFoundException("Can not find account with id "+id));
        Set<Role> roles = user.getRoles();
        Set<String> nameRoles = roles.stream().map(role -> {return role.getRoleName();}).collect(Collectors.toSet());
        if(nameRoles.contains(newRole))
            throw new ErrorExecutionFailedException("User has this role "+newRole);
        try {
            Role role = roleRepository.findRoleByRoleName(newRole).orElse(new Role(newRole, new ArrayList<>()));
           user.addRole(role);
           userRepository.save(user);
        }catch (Exception e){
           throw new ErrorExecutionFailedException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"add role successful for use with id "+id,null));
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username.toString());
        }
        return new CustomUserDetails(user);
    }

    public void checkPermission(User user) throws ErrorAccessDeniedException {
        Long idUser = myService.getPrincipal();
        if(user == null || idUser==null)
            throw new ErrorAccessDeniedException("Access is denied");
        if(!myService.isRoleAdmin() && user.getIdUser()!= idUser ){
            throw new ErrorAccessDeniedException("Access is denied");
        }
    }

    public ResponseEntity<ResponseObject> checkPhoneExists(String phoneNumber) {
        User user = userRepository.findByUserNumberPhone(phoneNumber);
        if(user == null){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(false,"This phone number does not exist yet",null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(true,"This phone number already exists",null));

    }

    public void updateResetPassword(String token, String email) throws ErrorNotFoundException {
        User user = userRepository.findByUserEmail(email);
        if(user != null){
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }else {
            throw new ErrorNotFoundException("Can not find user with email "+email);
        }
    }

    public User getUserByResetPasswordToken(String resetPasswordToken){
        return  userRepository.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setUserPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
}
