package com.mbaigo.trainingtools.training_tools.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService {
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
