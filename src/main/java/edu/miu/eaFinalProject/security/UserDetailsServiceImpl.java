package edu.miu.eaFinalProject.security;


import edu.miu.eaFinalProject.domain.Member;
import edu.miu.eaFinalProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = memberRepo.findByEmail(username);
        var userDetails = new UserDetailsImpl(user);
        return userDetails;
    }
}