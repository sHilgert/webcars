package br.gov.sp.fatec.web.controller;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.UsuarioRepository;
import br.gov.sp.fatec.service.AutorizacaoService;
import br.gov.sp.fatec.service.UsuarioServiceImpl;
import br.gov.sp.fatec.web.request.UsuarioRequest;
@CrossOrigin
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	@Qualifier("autorizacaoService")
	private AutorizacaoService autorizacaoService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager auth;

	public void setAuth(AuthenticationManager auth) {
		this.auth = auth;
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Usuario> getUser(@RequestBody UsuarioRequest usuarioRequest) {
		ResponseEntity<Usuario> response = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(usuarioRequest.getPassword().getBytes());

			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}

			usuarioRequest.setPassword(new String(hexString));

			boolean result = usuarioService.novo(usuarioRequest.getUsername(), usuarioRequest.getPassword(),
					autorizacaoService.buscar("ROLE_USUARIO"));
			if (result) {
				response = new ResponseEntity<Usuario>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Collection<Usuario>> getAllUsers() {
		ResponseEntity<Collection<Usuario>> response = null;
		try {
			List<Usuario> result = new ArrayList<Usuario>(usuarioService.todos());
			if (result != null && !result.isEmpty()) {
				response = new ResponseEntity<Collection<Usuario>>(result, HttpStatus.OK);
			} else {
				response = new ResponseEntity<Collection<Usuario>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<Collection<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Usuario> deleteUser(@RequestBody String nome) {
		ResponseEntity<Usuario> response = null;
		try {

			boolean result = usuarioService.deleta(nome);
			if (result) {
				response = new ResponseEntity<Usuario>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	public UsuarioServiceImpl getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioServiceImpl usuarioService) {
		this.usuarioService = usuarioService;
	}

	public AutorizacaoService getAutorizacaoService() {
		return autorizacaoService;
	}

	public void setAutorizacaoService(AutorizacaoService autorizacaoService) {
		this.autorizacaoService = autorizacaoService;
	}

	public AuthenticationManager getAuth() {
		return auth;
	}
}
