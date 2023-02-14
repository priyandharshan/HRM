package com.invicta.human.resource.management.security.details;

import com.invicta.human.resource.management.entities.UserCredentials;
import com.invicta.human.resource.management.services.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImplementation implements UserDetailsService {

  @Autowired
  private UserCredentialsService userCredentialsService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserCredentials userCredentials = userCredentialsService.getUserCredentialsByUsername(username);

    if (userCredentials == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return new CustomUserDetails(userCredentials);
  }
}
