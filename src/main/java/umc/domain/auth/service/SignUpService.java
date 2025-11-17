package umc.domain.auth.service;

import umc.domain.auth.dto.req.AuthReqDTO;
import umc.domain.auth.dto.res.AuthResDTO;

public interface SignUpService {

    AuthResDTO.SignUpDTO signup(
            AuthReqDTO.SignUpDTO dto
    );

}
