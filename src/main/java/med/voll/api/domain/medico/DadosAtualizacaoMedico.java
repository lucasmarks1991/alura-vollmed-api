package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.DadosAtualizacaoEndereco;

public record DadosAtualizacaoMedico(
    String nome,

    String telefone,

    DadosAtualizacaoEndereco endereco
) {
}
