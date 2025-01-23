package med.voll.api.domain.consulta;

import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Consulta agendar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        if (
            dadosAgendamentoConsulta.idMedico() != null &&
            !this.medicoRepository.existsById(dadosAgendamentoConsulta.idMedico())
        ) {
            throw new ValidacaoException("ID do médico informado não existe!");
        }

        if (!this.pacienteRepository.existsById(dadosAgendamentoConsulta.idPaciente())) {
            throw new ValidacaoException("ID do paciente informado não existe!");
        }

        var medico = this.escolherMedico(dadosAgendamentoConsulta);
        var paciente = this.pacienteRepository.findById(dadosAgendamentoConsulta.idPaciente()).get();

        return this.consultaRepository.save(
            new Consulta(null, medico, paciente, dadosAgendamentoConsulta.data(), null)
        );
    }

    public Consulta cancelar(Long id, DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        if (!this.consultaRepository.existsById(id)) {
            throw new ValidacaoException("ID da consulta informado não existe!");
        }

        var consulta = this.consultaRepository.getReferenceById(id);
        consulta.cancelar(dadosCancelamentoConsulta.motivo());

        return this.consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        if (dadosAgendamentoConsulta.idMedico() != null) {
            return this.medicoRepository.findById(dadosAgendamentoConsulta.idMedico()).get();
        }

        if (dadosAgendamentoConsulta.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        var medicoAleatorio = this.medicoRepository.escolherMedicoAleatorioLivreNaData(
            dadosAgendamentoConsulta.especialidade(), dadosAgendamentoConsulta.data()
        );

        if (medicoAleatorio == null) {
            throw new ValidacaoException("Nenhum médico disponível encontrado!");
        }

        return medicoAleatorio;
    }
}
