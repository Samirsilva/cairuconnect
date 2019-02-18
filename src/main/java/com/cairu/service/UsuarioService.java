package com.cairu.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cairu.model.Usuario;
import com.cairu.model.enums.TipoUsuario;
import com.cairu.repository.UsuarioRepository;
import com.cairu.request.NovoUsuarioRequest;
import com.cairu.request.UsuarioRequest;
import com.cairu.security.UserSS;
import com.cairu.service.exception.AuthorizationException;
import com.cairu.service.exception.DataIntegrityException;
import com.cairu.service.exception.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;
		
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Usuario find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasHole(TipoUsuario.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Usuario> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	@Transactional
	public Usuario insert(Usuario obj) {

		obj.setId(null);
		obj = repo.save(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

	public Usuario update(Usuario obj) {
		Usuario newUser = find(obj.getId());
		updateData(newUser, obj);
		return repo.save(newUser);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possível excluir por que há entidades relacionadas");
		}
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Page<Usuario> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasHole(TipoUsuario.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}
	
	public Usuario fromDTO(UsuarioRequest userReq) {
		return new Usuario(userReq.getId(), userReq.getNome(), userReq.getEmail(), userReq.getCpfCnpj(),
				pe.encode(userReq.getSenha()));
	}

	public Usuario fromDTO(NovoUsuarioRequest userReq) {
		return new Usuario(null, userReq.getNome(), userReq.getEmail(), userReq.getCpfOuCnpj(),
				pe.encode(userReq.getSenha()));
	}

	private void updateData(Usuario newUser, Usuario user) {
		newUser.setNome(user.getNome());
		newUser.setEmail(user.getEmail());
		newUser.setCpfCnpj(user.getCpfCnpj());
		newUser.setSenha(pe.encode(user.getSenha()));
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
				
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	
}
