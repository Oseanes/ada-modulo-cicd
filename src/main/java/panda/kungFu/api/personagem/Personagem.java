package panda.kungFu.api.personagem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "personagens")
@Entity(name = "Personagens")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Personagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String nome;
    private String idade;
    private String moral;
    private String classificacoes;
    private String habilidades;
    private String vigor;


    public Personagem(DadosCadastroPersonagem dados) {
        this.titulo = dados.titulo();
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.moral = dados.moral();
        this.classificacoes = dados.classificacoes();
        this.habilidades = dados.habilidades();
        this.vigor = dados.vigor();
    }

    public void atualizarInformacao(DadosAtualizacaoPersonagem dados) {

        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.moral() != null){
            this.moral = dados.moral();
        }
        if (dados.habilidades() != null){
            this.habilidades = dados.habilidades();
        }
        if (dados.classificacoes() != null){
            this.habilidades = dados.classificacoes();
        }

    }
}
