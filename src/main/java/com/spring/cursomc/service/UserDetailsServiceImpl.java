package com.spring.cursomc.service;

import com.spring.cursomc.domain.Cliente;
import com.spring.cursomc.repositories.ClienteRepository;
import com.spring.cursomc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cli = clienteRepository.findByEmail(email);

        if (cli == null) {
            throw new UsernameNotFoundException("Cliente não encontrado" + email);
        }
        return new UserSS(cli.getId(), cli.getEmail(),cli.getSenha(), cli.getPerfis());
    }
}
