package med.voll.api.medico;

import med.voll.api.endereco.DadosAtualizacaoEndereco;

public record DadosAtualizacaoMedico(
    String nome,

    String telefone,

    DadosAtualizacaoEndereco endereco
) {
}
