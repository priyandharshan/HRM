package com.invicta.human.resource.management.security.details;

import com.invicta.human.resource.management.entities.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class CustomUserDetails implements UserDetails {
  private UserCredentials userCredentials;

  public CustomUserDetails(UserCredentials userCredentials) {
    super();
    this.userCredentials = userCredentials;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return userCredentials.getPassword();
  }

  @Override
  public String getUsername() {
    return userCredentials.getUsername();
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
    return userCredentials.isUserActive();
  }

  public UserCredentials getUserCredentials() {
    return this.userCredentials;
  }
}
