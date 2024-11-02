package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.DadosAtualizacaoEndereco;

public record DadosAtualizacaoPaciente(
    String nome,

    String telefone,

    DadosAtualizacaoEndereco endereco
) {
}
