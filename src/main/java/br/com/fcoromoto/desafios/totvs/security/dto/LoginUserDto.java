package br.com.fcoromoto.desafios.totvs.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginUserDto implements Serializable {

    private String email;

    private String password;
}
