package br.com.fcoromoto.desafios.totvs.security.responses;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private long expiresIn;
}
