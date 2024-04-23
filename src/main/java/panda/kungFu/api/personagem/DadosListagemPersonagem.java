package panda.kungFu.api.personagem;

public record DadosListagemPersonagem(Long id, String titulo, String nome, String idade, String moral, String classificacoes, String habilidades, String vigor) {

    public DadosListagemPersonagem(Personagem personagem){
        this(personagem.getId(), personagem.getTitulo(), personagem.getNome(), personagem.getIdade(), personagem.getMoral(), personagem.getClassificacoes(), personagem.getHabilidades(), personagem.getVigor());
    }

}
