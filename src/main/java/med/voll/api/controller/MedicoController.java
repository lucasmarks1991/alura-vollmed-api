package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping("")
    @Transactional
    public ResponseEntity cadastrar(
        @RequestBody @Valid DadosCadastroMedico dadosCadastroMedico,
        UriComponentsBuilder uriComponentsBuilder
    ) {
        var medico = this.medicoRepository.save(new Medico(dadosCadastroMedico));
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("")
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(this.medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = this.medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(
        @PathVariable Long id, @RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico
    ) {
        var medico = this.medicoRepository.getReferenceById(id);
        medico.atualizarInformacoes(dadosAtualizacaoMedico);

        this.medicoRepository.save(medico);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativar(@PathVariable Long id) {
        var medico = this.medicoRepository.getReferenceById(id);
        medico.inativar();

        this.medicoRepository.save(medico);

        return ResponseEntity.noContent().build();
    }
}
