package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Autorizacao;
import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl {

	@Autowired
	private UsuarioRepository usuarioRespository;

	public Boolean novo(String nome, String senha, List<Autorizacao> autorizacoes) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setAutorizacoes(autorizacoes);
		try {
			usuarioRespository.save(usuario);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Collection<Usuario> todos(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (Usuario usuario : usuarioRespository.findAll()) {
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	public Boolean deleta(String nome){
		try{
			Usuario usuario = usuarioRespository.findByNome(nome);
			usuarioRespository.delete(usuario);
		}catch(Exception e){
			return false;
		}
		return true;
		
	}
	public UsuarioRepository getUsuarioRespository() {
		return usuarioRespository;
	}

	public void setUsuarioRespository(UsuarioRepository usuarioRespository) {
		this.usuarioRespository = usuarioRespository;
	}

}
