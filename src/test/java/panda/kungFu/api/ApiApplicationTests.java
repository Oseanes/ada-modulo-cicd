package panda.kungFu.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import panda.kungFu.api.personagem.DadosAtualizacaoPersonagem;
import panda.kungFu.api.personagem.DadosCadastroPersonagem;
import panda.kungFu.api.personagem.Personagem;
import panda.kungFu.api.personagem.PersonagemRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonagemRepository repository;

    @Test
    public void testCadastrar() throws Exception {
        DadosCadastroPersonagem dados = new DadosCadastroPersonagem(
                "Mestre Tigresa",
                "Tigresa",
                "20 anos",
                "Membro leal e amiga do Po, o Guerreiro Dragão",
                "Membro dos Cinco Furiosos e estudante do Mestre Shifu",
                "Mestre do estilo de kung fu do Tigre",
                "Conhecida por sua força e agilidade excepcionais"
                );
        Personagem personagem = new Personagem(dados);

        when(repository.save(any(Personagem.class))).thenReturn(personagem);

		ObjectMapper objectMapper = new ObjectMapper();
		String dadosJson = objectMapper.writeValueAsString(dados);

		mockMvc.perform(MockMvcRequestBuilders.post("/personagens")
						.contentType("application/json")
						.content(dadosJson))
				.andExpect(MockMvcResultMatchers.status().isOk());

        verify(repository, times(1)).save(any(Personagem.class));
    }

    @Test
    public void listarTest() throws Exception {
        List<Personagem> personagensList = new ArrayList<>();

        DadosCadastroPersonagem dados = new DadosCadastroPersonagem(
                "Mestre Tigresa",
                "Tigresa",
                "20 anos",
                "Bom",
                "Membro dos Cinco Furiosos e estudante do Mestre Shifu",
                "Mestre do estilo de kung fu do Tigre",
                "Conhecida por sua força e agilidade excepcionais"
        );
        Personagem tigresa = new Personagem(dados);

        dados = new DadosCadastroPersonagem(
                "Panda Gordo",
                "Po Ping",
                "20 anos",
                "Bom",
                "Panda, Dragão Guerreiro, Mestre do Chi",
                "Mestre do estilo de kung fu do Tigre",
                "Alto"
        );
        Personagem po = new Personagem(dados);

        personagensList.add(tigresa);
        personagensList.add(po);

        Page<Personagem> page = new PageImpl<>(personagensList);

        when(repository.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/personagens"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists());

        verify(repository, times(1)).findAll(any(Pageable.class));
    }


    @Test
    public void atualizarTest() throws Exception {
        DadosAtualizacaoPersonagem dadosAtualizacao = new DadosAtualizacaoPersonagem(
                1L,
                "Tigresa",
                "21 anos",
                "Membro leal e amiga do Po, o Guerreiro Dragão",
                "Mestre do estilo de kung fu do Tigre",
                "Conhecida por sua força e agilidade excepcionais"
        );

        DadosCadastroPersonagem dadosCadastro = new DadosCadastroPersonagem(
                "Mestre Tigresa",
                "Tigresa",
                "20 anos",
                "Membro leal e amiga do Po, o Guerreiro Dragão",
                "Membro dos Cinco Furiosos e estudante do Mestre Shifu",
                "Mestre do estilo de kung fu do Tigre",
                "Conhecida por sua força e agilidade excepcionais"
        );

        Personagem personagem = new Personagem(dadosCadastro);

        when(repository.getReferenceById(any(Long.class))).thenReturn(personagem);
        when(repository.save(any(Personagem.class))).thenReturn(personagem);

        ObjectMapper objectMapper = new ObjectMapper();
        String dadosJson = objectMapper.writeValueAsString(dadosAtualizacao);

        mockMvc.perform(MockMvcRequestBuilders.put("/personagens")
                        .contentType("application/json")
                        .content(dadosJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(repository, times(1)).getReferenceById(any(Long.class));
        verify(repository, times(1)).save(any(Personagem.class));
    }


}
