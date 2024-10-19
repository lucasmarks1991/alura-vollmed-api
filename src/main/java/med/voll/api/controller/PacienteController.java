package med.voll.api.controller;

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
    public void cadastrar(@RequestBody DadosCadastroPaciente dadosCadastroPaciente) {
        this.pacienteRepository.save(new Paciente(dadosCadastroPaciente));
    }

    @GetMapping("")
    public Page<DadosListagemPaciente> listar(@PageableDefault(sort = "nome") Pageable pageable) {
        return this.pacienteRepository.findAll(pageable).map(DadosListagemPaciente::new);
    }
}
