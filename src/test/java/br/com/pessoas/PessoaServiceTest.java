package br.com.pessoas;



import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.pessoas.exceptions.BusinessException;
import br.com.pessoas.exceptions.DuplicatedResourceException;
import br.com.pessoas.exceptions.ResourceNotFoundException;
import br.com.pessoas.model.dto.PessoaDTO;
import br.com.pessoas.service.PessoaService;



@SpringBootTest
class PessoaServiceTest {
	
	@Mock
	private PessoaService pessoaRepository;
	
	@InjectMocks
	private PessoaService pessoaService;
	
	private static PessoaDTO pessoa;
	
	
	
	@BeforeAll
	static void beforeAll() {
		pessoa = new PessoaDTO();
		pessoa.setId(1L);
		pessoa.setCpf("04568423309");
		pessoa.setNome("Lorem Ipsum");
		pessoa.setDataNascimento(LocalDate.of(2000, 10, 01));
		pessoa.setNacionalidade("Brasileiro");
		pessoa.setNaturalidade("São Luis");
		pessoa.setEmail("sdals@gmail.com");
		
	}

	@Test
	@DisplayName("Deve salvar os dados de uma pessoa")
	void testSalvarPessoa() {
		when(pessoaService.obterPorCpf("04568423309")).thenReturn(pessoa);
		when(pessoaService.salvar(any(PessoaDTO.class))).thenReturn(pessoa);
		
		var novaPessoa = new PessoaDTO();
		novaPessoa.setCpf("04070716017");
		novaPessoa.setNome("Lorem Ipsum");
		novaPessoa.setDataNascimento(LocalDate.of(2000, 10, 01));
		novaPessoa.setEmail("mario@gmail.com");
		
		
		
		var pessoaSalva = pessoaService.salvar(novaPessoa);
		
		assertAll(() -> {
			assertEquals(1L, pessoaSalva.getId());
			assertEquals(novaPessoa.getCpf(), pessoaSalva.getCpf());
			assertEquals(novaPessoa.getDataNascimento(), pessoaSalva.getDataNascimento());
			assertEquals(novaPessoa.getNome(), pessoaSalva.getNome());
		});
	}

	@Test
	@DisplayName("Não deve salvar")
	void testNaoSalvarPessoa() {
		when(pessoaRepository.obterPorCpf("04568423309")).thenReturn(pessoa);
		
		var novaPessoa = new PessoaDTO();
		novaPessoa.setCpf("0468423309");
		novaPessoa.setNome("Lorem Ipsum");
		novaPessoa.setDataNascimento(LocalDate.of(2000, 10, 01));
		novaPessoa.setEmail("dasdfe@gmail.com");
		
		assertThrows(DuplicatedResourceException.class, () -> pessoaService.salvar(novaPessoa));
	}
	
	@Test
	@DisplayName("Deve editar os dados de uma pessoa")
	void testEditarAluno() {
		when(pessoaRepository.obterPorCpf("04568423309")).thenReturn(pessoa);
		when(pessoaRepository.salvar(any(PessoaDTO.class))).thenReturn(pessoa);
		
		var novoAluno = new PessoaDTO();
		novoAluno.setId(1L);
		novoAluno.setCpf("57374805004");
		novoAluno.setNome("Amaro");
		novoAluno.setDataNascimento(LocalDate.of(2000, 10, 01));
		novoAluno.setEmail("klsdfas@gmail.com");
		
		
		var pessoaSalva = pessoaService.editar(novoAluno);
		
		assertAll(() -> {
			assertEquals(1L, pessoaSalva.getId());
			assertEquals(novoAluno.getCpf(), pessoaSalva.getCpf());
			assertEquals(novoAluno.getDataNascimento(), pessoaSalva.getDataNascimento());
			assertEquals(novoAluno.getNome(), pessoaSalva.getNome());
			assertEquals(novoAluno.getCpf(),pessoaSalva.getNome());
		});
	}
	
	@Test
	@DisplayName("Não deve editar os dados ")
	void testNaoEditarAluno() {
		when(pessoaRepository.obterPorCpf("0456453309")).thenReturn(pessoa);
		
		var novaPessoa = new PessoaDTO();
		novaPessoa.setId(1L);
		novaPessoa.setCpf("17283664025");
		novaPessoa.setNome("Lorem Ipsum");
		novaPessoa.setDataNascimento(LocalDate.of(2000, 10, 01));
		novaPessoa.setEmail("ssefs@gmail.com");	
		assertThrows(BusinessException.class, () -> pessoaService.editar(novaPessoa));
	}

	@Test
	@DisplayName("Deve remover os dados de agluem")
	void testRemover() {
		when(pessoaRepository.obterPorId(1L)).thenReturn(pessoa);
		doNothing().when(pessoaRepository).remover(pessoa);
		
		assertDoesNotThrow(() -> pessoaService.remover(pessoa));
	}
	
	@Test
	@DisplayName("Não deve remover os dados de uma pessoa caso não seja encontrado pelo id")
	void testNaoRemover() {
		when(pessoaRepository.obterPorId(1L)).thenReturn(pessoa);		
		assertThrows(ResourceNotFoundException.class, () -> pessoaService.remover(pessoa));
	}

}