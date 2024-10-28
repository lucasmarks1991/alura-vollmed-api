package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;

    private String bairro;

    private String cep;

    private String numero;

    private String complemento;

    private String cidade;

    private String uf;

    public Endereco(DadosCadastroEndereco dadosCadastroEndereco) {
        this.logradouro = dadosCadastroEndereco.logradouro();
        this.bairro = dadosCadastroEndereco.bairro();
        this.cep = dadosCadastroEndereco.cep();
        this.uf = dadosCadastroEndereco.uf();
        this.cidade = dadosCadastroEndereco.cidade();
        this.numero = dadosCadastroEndereco.numero();
        this.complemento = dadosCadastroEndereco.complemento();
    }
}
