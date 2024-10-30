package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping("")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente) {
        this.pacienteRepository.save(new Paciente(dadosCadastroPaciente));
    }

    @GetMapping("")
    public Page<DadosListagemPaciente> listar(@PageableDefault(sort = "nome") Pageable pageable) {
        return this.pacienteRepository.findAllByAtivoTrue(pageable).map(DadosListagemPaciente::new);
    }

    @PutMapping("")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        var paciente = this.pacienteRepository.getReferenceById(dadosAtualizacaoPaciente.id());
        paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

        this.pacienteRepository.save(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inativar(@PathVariable Long id) {
        var medico = this.pacienteRepository.getReferenceById(id);
        medico.inativar();

        this.pacienteRepository.save(medico);
    }
}
