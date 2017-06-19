package br.gov.sp.fatec.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.security.JwtUtils;
import br.gov.sp.fatec.security.Login;
@CrossOrigin
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager auth;

    public void setAuth(AuthenticationManager auth) {
        this.auth = auth;
    }
    
    
    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario login(@RequestBody Login login, HttpServletResponse response) throws JsonProcessingException {
        Authentication credentials = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Usuario usuario = (Usuario) auth.authenticate(credentials).getPrincipal();
        usuario.setSenha(null);
        response.setHeader("Access-Control-Expose-Headers", "Token");
        response.setHeader("Token", JwtUtils.generateToken(usuario));
        return usuario;
    }

}
