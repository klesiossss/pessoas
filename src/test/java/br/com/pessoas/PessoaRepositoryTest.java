package br.com.pessoas;

import br.com.pessoas.model.Genero;
import br.com.pessoas.model.Pessoa;
import br.com.pessoas.repository.PessoaRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistDate(){
        Genero genero = Genero.MASCULINO;
        LocalDate localDate = LocalDate.of(2010, 3, 7);
        Pessoa pessoa = new Pessoa(1,"Maria","04568423309","maria@gmail.com",genero,"Brasileiro","Humberto de Campos",localDate);
        this.pessoaRepository.save(pessoa);
        Assertions.assertThat(pessoa.getId()).isNotNull();
        Assertions.assertThat(pessoa.getNome()).isEqualTo("Maria");
        Assertions.assertThat(pessoa.getEmail()).isEqualTo("maria@gmail.com");
    }


    @Test
    public void deleteShouldRemoveData(){
        Genero genero = Genero.MASCULINO;
        LocalDate localDate = LocalDate.of(2010, 3, 7);
        Pessoa pessoa = new Pessoa(1,"Maria",
                "04568423309",
                "maria@gmail.com",
                genero,
                "Brasileiro",
                "Humberto de Campos",
                 localDate);
        this.pessoaRepository.save(pessoa);
        pessoaRepository.deleteById(pessoa.getId());
        Assertions.assertThat(pessoaRepository.findById(pessoa.getId())).isEmpty();

    }
    @Test
    public void upadateShouldChangeAndPersistData(){
        Genero genero = Genero.MASCULINO;
        LocalDate localDate = LocalDate.of(2010, 3, 7);
        Pessoa pessoa = new Pessoa(1,"Maria",
                "04568423309",
                "maria@gmail.com",
                genero,
                "Brasileiro",
                "Humberto de Campos",
                localDate);
        this.pessoaRepository.save(pessoa);
        pessoa.setNome("Maria Anastacia");
        pessoa.setEmail("mariaanastacia@gmail.com");
        pessoa = this.pessoaRepository.save(pessoa);
        Assertions.assertThat(pessoa.getNome()).isEqualTo("Maria Anastacia");
        Assertions.assertThat(pessoa.getEmail()).isEqualTo("mariaanastacia@gmail.com");
    }


}
