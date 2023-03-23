package edu.miu.eaFinalProject.service.Impl;

import edu.miu.eaFinalProject.dto.LoginRequestDTO;
import edu.miu.eaFinalProject.dto.LoginResponseDTO;
import edu.miu.eaFinalProject.dto.adapter.MemberAdapter;
import edu.miu.eaFinalProject.repository.MemberRepository;
import edu.miu.eaFinalProject.security.JWTHelper;
import edu.miu.eaFinalProject.service.MemberService;
import edu.miu.eaFinalProject.service.UaaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UaaServiceImpl implements UaaService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) throws Exception{
        LoginResponseDTO loginResponse;
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            loginResponse = generateAccessAndRefreshToken(loginRequest.getEmail());
        } catch (BadCredentialsException e) {
            log.info(e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return loginResponse;
    }

    private LoginResponseDTO generateAccessAndRefreshToken(String username) {
        return new LoginResponseDTO(
                jwtHelper.generateAccessToken(username),
                MemberAdapter.getMemberDTOFromMember(memberRepository.findByEmail(username))
        );
    }
}
