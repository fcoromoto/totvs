package br.com.fcoromoto.desafios.totvs.security.controller;

import br.com.fcoromoto.desafios.totvs.security.dto.LoginUserDto;
import br.com.fcoromoto.desafios.totvs.security.dto.RegisterUserDto;
import br.com.fcoromoto.desafios.totvs.security.model.User;
import br.com.fcoromoto.desafios.totvs.security.responses.LoginResponse;
import br.com.fcoromoto.desafios.totvs.security.service.AuthenticationService;
import br.com.fcoromoto.desafios.totvs.security.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
