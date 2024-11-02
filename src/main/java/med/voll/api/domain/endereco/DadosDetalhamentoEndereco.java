package med.voll.api.domain.endereco;

public record DadosDetalhamentoEndereco(
    String logradouro,
    String bairro,
    String cep,
    String cidade,
    String uf,
    String numero,
    String complemento
) {
    public DadosDetalhamentoEndereco(Endereco endereco) {
        this(
            endereco.getLogradouro(),
            endereco.getBairro(),
            endereco.getCep(),
            endereco.getCidade(),
            endereco.getUf(),
            endereco.getNumero(),
            endereco.getComplemento()
        );
    }
}