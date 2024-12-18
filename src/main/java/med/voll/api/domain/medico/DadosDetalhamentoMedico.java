package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.DadosDetalhamentoEndereco;

public record DadosDetalhamentoMedico(
    Long id,
    String nome,
    String email,
    String telefone,
    String crm,
    Especialidade especialidade,
    DadosDetalhamentoEndereco endereco
) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(
            medico.getId(),
            medico.getNome(),
            medico.getEmail(),
            medico.getTelefone(),
            medico.getCrm(),
            medico.getEspecialidade(),
            new DadosDetalhamentoEndereco(medico.getEndereco())
        );
    }
}
