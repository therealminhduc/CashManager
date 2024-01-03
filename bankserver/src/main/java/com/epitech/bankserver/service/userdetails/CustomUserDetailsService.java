//package com.epitech.bankserver.service.userdetails;
//
//import com.epitech.bankserver.model.account.Account;
//import com.epitech.bankserver.model.account.Admin;
//import com.epitech.bankserver.repository.account.AccountRepository;
//import com.epitech.bankserver.repository.account.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final AdminRepository adminRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public CustomUserDetailsService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
//        this.adminRepository = adminRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Loading user by username: " + username);
//
//        Admin admin = adminRepository.findAdminByUsername(username);
//
//        if(admin == null) {
//            throw new UsernameNotFoundException("Admin not found");
//        }
//
//        System.out.println("user: " + admin.getUsername() + " pass: " + admin.getPassword());
//
//        UserDetails userDetails = User.withUsername(admin.getUsername())
//                .passwordEncoder(passwordEncoder::encode)
//                .password(admin.getPassword())
//                .build();
//
//        System.out.println("Generated UserDetails: " + userDetails);
//
//        return userDetails;
//    }
//}
