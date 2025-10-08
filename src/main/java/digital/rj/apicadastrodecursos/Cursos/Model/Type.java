package digital.rj.apicadastrodecursos.Cursos.Model;

public enum Type {
    TECNICO("Técnico"),
    POS_GRADUACAO("Pós-Graduação");

    private final String descricao;

    Type(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
