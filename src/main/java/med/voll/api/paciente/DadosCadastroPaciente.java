package med.voll.api.paciente;

import med.voll.api.endereco.DadosCadastroEndereco;

public record DadosCadastroPaciente(
    String nome,
    String email,
    String telefone,
    String cpf,
    DadosCadastroEndereco endereco
) {
}
