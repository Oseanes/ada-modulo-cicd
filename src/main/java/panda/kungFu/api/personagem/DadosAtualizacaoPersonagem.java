package panda.kungFu.api.personagem;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPersonagem(
        @NotNull
        Long id,
        String nome,
        String idade,
        String moral,
        String habilidades,
        String classificacoes
) {

}
