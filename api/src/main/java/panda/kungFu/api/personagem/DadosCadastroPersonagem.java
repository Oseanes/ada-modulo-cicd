package panda.kungFu.api.personagem;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPersonagem(
        @NotBlank
        String titulo,
        @NotBlank
        String nome,
        @NotBlank
        String idade,
        @NotBlank
        String moral,
        @NotBlank
        String classificacoes,
        @NotBlank
        String habilidades,
        @NotBlank
        String vigor
) {

}
