package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaService;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping("")
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var consulta = this.consultaService.agendar(dadosAgendamentoConsulta);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancelar(
        @PathVariable Long id, @RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta
    ) {
        this.consultaService.cancelar(id, dadosCancelamentoConsulta);

        return ResponseEntity.noContent().build();
    }
}
