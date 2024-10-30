package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping("")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico) {
        this.medicoRepository.save(new Medico(dadosCadastroMedico));
    }

    @GetMapping("")
    public Page<DadosListagemMedico> listar(@PageableDefault(sort = "nome") Pageable pageable) {
        return this.medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    @PutMapping("")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        var medico = this.medicoRepository.getReferenceById(dadosAtualizacaoMedico.id());
        medico.atualizarInformacoes(dadosAtualizacaoMedico);

        this.medicoRepository.save(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inativar(@PathVariable Long id) {
        var medico = this.medicoRepository.getReferenceById(id);
        medico.inativar();

        this.medicoRepository.save(medico);
    }
}
