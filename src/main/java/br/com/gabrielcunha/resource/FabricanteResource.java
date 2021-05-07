package br.com.gabrielcunha.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gabrielcunha.entity.Fabricante;
import br.com.gabrielcunha.repository.FabricantesRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Endpoints para criar, consultar, atualizar e deletar um Recurso.", tags = {"Fabricante"})
@RestController
@RequestMapping("/fabricantes")
public class FabricanteResource {
	
	@Autowired
	private FabricantesRepository fabricantesDao;
	
	@ApiOperation(value="Endpoint responsável por listar todos recursos.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso.", response=List.class )  })
	@GetMapping
	public List<Fabricante> listarTodos () throws Exception{
		return fabricantesDao.findAll();
	}
	
	
	@ApiOperation(value="Endpoint responsável por criar um recurso.")	
	@ApiResponses(value = { 
	        @ApiResponse(code = 201, message = "Recurso criado."), 
	        @ApiResponse(code = 400, message = "Parâmetro inválido."), 
	        @ApiResponse(code = 409, message = "Recurso ja existe.") })		
	@PostMapping
	public ResponseEntity<Fabricante> criar(@RequestBody Fabricante fabricante) {
		
		Fabricante fabricanteBanco = fabricantesDao.findByNome(fabricante.getNome());
		
		if (!ObjectUtils.isEmpty(fabricanteBanco)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		Fabricante fabricanteSalvo = fabricantesDao.save(fabricante);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
											.path("/{id}")
											.buildAndExpand(fabricanteSalvo.getId()).toUri();
		
		 return ResponseEntity.created(uri).body(fabricanteSalvo);
	}
	
	
	@ApiOperation(value="Endpoint responsável por buscar um recurso por codigo.")	
    @ApiParam(name = "id",
			  value = "Id do recurso para busca. Não pode ser vazio..",
			  example = "1",
			  required = true)	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Sucesso.", response = Fabricante.class),
	        @ApiResponse(code = 404, message = "Recurso não encontrado.") 
	})	
	@GetMapping("/{id}")
	public ResponseEntity<Fabricante> buscarPorCodigo(@PathVariable Long id) {
		
		Optional<Fabricante> fabricanteOptional = fabricantesDao.findById(id);
		
		Fabricante fabricante = new Fabricante();
		
		if (fabricanteOptional.isPresent()) {
			
			fabricante = fabricanteOptional.get();
			
			return ResponseEntity.ok(fabricante);
			
		} else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@ApiOperation(value="Endpoint responsável por buscar um recurso fabricante por nome.")	
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
            @ApiResponse(code = 404, message = "Recurso não encontrado.")})	
	@GetMapping("/nome/{nome}")
	public Fabricante buscarPorNome(@PathVariable String nome) {
		return fabricantesDao.findByNome(nome);
	}
	
	
	@ApiOperation(value="Endpoint responsável por remover um recurso pelo id.")		
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado.") })	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable Long id) {
		fabricantesDao.deleteById(id);
	}
	
	
	@ApiOperation(value="Endpoint responsável por atualizar um recurso pelo id, enviando no body da requisição o json do recurso com os dados atualizados.")	
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso."),
            @ApiResponse(code = 400, message = "Id fornecido inválido."),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 405, message = "Exceção de validação") })	
	@PutMapping("/{id}")
	public ResponseEntity<Fabricante> atualizar(@PathVariable Long id, @RequestBody Fabricante fabricante) {
		
		Optional<Fabricante> fabricanteOptional = fabricantesDao.findById(id);
		
		if (!fabricanteOptional.isPresent()) {
			
			throw new EmptyResultDataAccessException(1);
			
		}
		
		Fabricante fabricanteBanco = fabricanteOptional.get();
		
		BeanUtils.copyProperties(fabricante, fabricanteBanco, "id");
		
		fabricantesDao.save(fabricanteBanco);
		
		return ResponseEntity.ok(fabricanteBanco);
	}
	
 
}
