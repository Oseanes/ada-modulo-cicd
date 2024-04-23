package panda.kungFu.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import panda.kungFu.api.personagem.*;


@RestController
@RequestMapping("/personagens")
public class PersonagemController {
    @Autowired // Spring vai instanciar o objeto repository
    private PersonagemRepository repository;

    @Transactional
    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroPersonagem dados){
    repository.save((new Personagem(dados)));
    }

    @GetMapping
    public Page<DadosListagemPersonagem> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPersonagem:: new);
    }

    @Transactional
    @PutMapping
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPersonagem dados){
        var personagem = repository.getReferenceById(dados.id());
        personagem.atualizarInformacao(dados);
        repository.save(personagem);
    }
}
