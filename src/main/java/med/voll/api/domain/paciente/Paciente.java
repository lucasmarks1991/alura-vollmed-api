package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        if (dadosAtualizacaoPaciente.nome() != null) {
            this.nome = dadosAtualizacaoPaciente.nome();
        }

        if (dadosAtualizacaoPaciente.telefone() != null) {
            this.telefone = dadosAtualizacaoPaciente.telefone();
        }

        if (dadosAtualizacaoPaciente.endereco() != null) {
            this.endereco.atualizarInformacoes(dadosAtualizacaoPaciente.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}