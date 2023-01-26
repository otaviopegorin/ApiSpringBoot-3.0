package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPacientes> listar(Pageable pageable){
        return pacienteRepository.findAllByAtivoTrue(pageable).map(DadosListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosUpdatePaciente dados){
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);
        pacienteRepository.save(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inativar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
        pacienteRepository.save(paciente);
    }

}
