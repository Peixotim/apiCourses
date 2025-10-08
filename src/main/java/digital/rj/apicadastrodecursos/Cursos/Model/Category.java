package digital.rj.apicadastrodecursos.Cursos.Model;

public enum Category {

    // --- ÁREA DA SAÚDE ---
    ENFERMAGEM("Área da Saúde"),
    RADIOLOGIA("Radiologia e Diagnóstico por Imagem"),
    ESTETICA("Estética e Cosmética"),
    ANALISES_CLINICAS("Análises Clínicas e Laboratoriais"),
    FARMACIA("Farmácia e Biotecnologia"),
    ODONTOLOGIA("Odontologia e Saúde Bucal"),
    FISIOTERAPIA("Fisioterapia e Reabilitação"),

    // --- GESTÃO, ADMINISTRAÇÃO E NEGÓCIOS ---
    ADMINISTRACAO("Administração e Gestão Empresarial"),
    CONTABILIDADE("Contabilidade e Finanças"),
    RECURSOS_HUMANOS("Recursos Humanos e Liderança"),
    LOGISTICA("Logística e Supply Chain"),
    MARKETING("Marketing, Vendas e Comunicação"),
    COMERCIO("Comércio e Empreendedorismo"),
    GESTAO_PUBLICA("Gestão Pública e Processos Governamentais"),

    // --- TECNOLOGIA E INOVAÇÃO ---
    INFORMATICA("Tecnologia da Informação e Informática"),
    REDES_COMPUTADORES("Redes de Computadores e Cibersegurança"),
    DESENVOLVIMENTO_SOFTWARE("Desenvolvimento de Software e Programação"),
    ANALISE_SISTEMAS("Análise de Sistemas e Banco de Dados"),
    INTELIGENCIA_ARTIFICIAL("Inteligência Artificial e Ciência de Dados"),

    // --- ENGENHARIA, INDÚSTRIA E MANUTENÇÃO ---
    ELETROTECNICA("Eletrotécnica e Energia"),
    MECANICA("Mecânica e Manutenção Industrial"),
    AUTOMACAO("Automação Industrial e Robótica"),
    ENGENHARIA_CIVIL("Engenharia Civil e Construção"),
    SEGURANCA_TRABALHO("Segurança do Trabalho"),
    MEIO_AMBIENTE("Meio Ambiente e Sustentabilidade"),

    // --- EDUCAÇÃO E HUMANAS ---
    EDUCACAO("Educação e Docência"),
    PSICOPEDAGOGIA("Psicopedagogia e Desenvolvimento Humano"),
    GESTAO_EDUCACIONAL("Gestão Educacional e Coordenação Pedagógica"),
    INCLUSAO("Educação Inclusiva e Libras"),
    NEUROEDUCACAO("Neuroeducação e Aprendizagem"),

    // --- DIREITO E SERVIÇOS SOCIAIS ---
    DIREITO("Direito e Legislação"),
    SERVICO_SOCIAL("Serviço Social e Cidadania"),
    MEDIACAO_CONFLITOS("Mediação e Resolução de Conflitos"),
    GESTAO_PUBLICA_JURIDICA("Gestão Pública e Jurídica"),

    // --- DESIGN, COMUNICAÇÃO E ARTES ---
    DESIGN("Design e Comunicação Visual"),
    PUBLICIDADE("Publicidade e Marketing Digital"),
    AUDIOVISUAL("Produção Audiovisual e Multimídia"),
    FOTOGRAFIA("Fotografia e Imagem"),

    // --- ENERGIA E INFRAESTRUTURA ---
    ENERGIAS_RENOVAVEIS("Energias Renováveis e Sustentáveis"),
    CONSTRUCAO_CIVIL("Construção Civil e Infraestrutura"),
    SANEAMENTO("Saneamento e Engenharia Ambiental"),

    // --- AGRONEGÓCIOS E MEIO RURAL ---
    AGRONEGOCIOS("Agronegócios e Gestão Rural"),
    ZOOTECNIA("Zootecnia e Produção Animal"),
    AGRICULTURA("Agricultura e Meio Ambiente"),

    // --- PÓS-GRADUAÇÃO E ESPECIALIZAÇÕES ---
    MBA("MBA e Gestão Executiva"),
    POS_GESTAO_SAUDE("Pós-Graduação em Gestão em Saúde"),
    POS_EDUCACAO("Pós-Graduação em Educação e Docência"),
    POS_TECNOLOGIA("Pós-Graduação em Tecnologia e Inovação"),
    POS_ENGENHARIA("Pós-Graduação em Engenharia e Sustentabilidade"),
    POS_RH("Pós-Graduação em Recursos Humanos e Coaching"),
    POS_PSICOPEDAGOGIA("Pós-Graduação em Psicopedagogia");

    private final String descricao;

    Category(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
