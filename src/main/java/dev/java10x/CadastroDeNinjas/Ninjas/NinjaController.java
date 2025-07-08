package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // ADD NINJA
    @PostMapping("/add")
    @Operation(summary = "Adiciona um novo Ninja", description = "Essa rota adiciona um ninja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado."),
            @ApiResponse(responseCode = "400", description = "Erro na criação."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaDTO.getNome() + "(" + ninjaDTO.getId() + ") Foi criado com sucesso");
    }

    // PROCURAR NINJA POR ID
    @GetMapping("/listarId/{id}")
    @Operation(summary = "Busca ninja por ID", description = "Retorna um ninja específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<?> listarNinjaId(@PathVariable long id) {
        NinjaDTO ninja = ninjaService.listarNinjaId(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja no id " + id + " nao existe no nossos registros, tente novamente.");
        }
    }

    // LISTAR TODOS
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Retorna todos os ninjas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ninjas retornada com sucesso."),
            @ApiResponse(responseCode = "204", description = "Nenhum ninja encontrado.")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> list = ninjaService.listarNinja();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    // ALTERAR
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera um ninja existente", description = "Atualiza os dados de um ninja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização.")
    })
    public ResponseEntity<?> alterarNinja( @Parameter(description = "Usuario manda id") @PathVariable long id, @RequestBody  @Parameter(description = "Usuario manda o escopo de ninja")NinjaDTO ninjaDTO) {
        NinjaDTO ninjaAntigo = ninjaService.listarNinjaId(id);
        if (ninjaAntigo != null) {
            ninjaService.atualizar(id, ninjaDTO);
            NinjaDTO ninjaAtualizado = ninjaService.listarNinjaId(id);
            return ResponseEntity.ok(ninjaAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Esse ninja nao existe no nosso sistema, tente novamente");
        }
    }

    // DELETAR
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta um ninja", description = "Remove um ninja do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<String> deletarNinja(@PathVariable long id) {
        NinjaDTO ninjaDTO = ninjaService.listarNinjaId(id);
        if (ninjaDTO != null) {
            ninjaService.deletando(id);
            return ResponseEntity.ok(ninjaDTO.getNome() + " Foi deletado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja nao foi encontrado, por favor tente novamente.");
        }
    }
}