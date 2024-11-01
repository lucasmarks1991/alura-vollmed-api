package med.voll.api.paciente;

import med.voll.api.endereco.DadosAtualizacaoEndereco;

public record DadosAtualizacaoPaciente(
    String nome,

    String telefone,

    DadosAtualizacaoEndereco endereco
) {
}
