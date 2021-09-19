package com.smalltektcc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smalltektcc.model.Usuario;
import com.smalltektcc.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario user = userRepository.findByEmail(email);
        if(user ==null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new CustomUserDetails(user);
    }
}
