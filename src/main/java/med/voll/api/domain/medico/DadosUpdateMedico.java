package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosUpdateMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
