package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.dto.LoginRequestDTO;
import edu.miu.eaFinalProject.dto.LoginResponseDTO;

public interface UaaService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception;
}
