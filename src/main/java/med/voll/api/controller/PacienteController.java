package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping("")
    @Transactional
    public ResponseEntity cadastrar(
        @RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente, UriComponentsBuilder uriComponentsBuilder
    ) {
        var paciente = this.pacienteRepository.save(new Paciente(dadosCadastroPaciente));
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping("")
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(this.pacienteRepository.findAllByAtivoTrue(pageable).map(DadosListagemPaciente::new));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable Long id) {
        var paciente = this.pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(
        @PathVariable Long id, @RequestBody @Valid DadosAtualizacaoPaciente dadosAtualizacaoPaciente
    ) {
        var paciente = this.pacienteRepository.getReferenceById(id);
        paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

        this.pacienteRepository.save(paciente);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativar(@PathVariable Long id) {
        var paciente = this.pacienteRepository.getReferenceById(id);
        paciente.inativar();

        this.pacienteRepository.save(paciente);

        return ResponseEntity.noContent().build();
    }
}
