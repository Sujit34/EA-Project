package edu.miu.eaFinalProject.Controller;

import edu.miu.eaFinalProject.dto.LoginRequestDTO;
import edu.miu.eaFinalProject.dto.LoginResponseDTO;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.security.JWTHelper;
import edu.miu.eaFinalProject.service.UaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class UaaController {
    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private UaaService uaaService;
    @GetMapping
    public String getFirst() {
        return "Hello World!!";
    }

    @PostMapping
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) throws Exception{
        LoginResponseDTO loginResponseDTO = null;
        try {
            loginResponseDTO = uaaService.login(loginRequest);
        } catch (Exception e) {
            throw new ApiRequestException("Error while logging in!!", HttpStatus.FORBIDDEN);
        }
        return loginResponseDTO;
    }
}
