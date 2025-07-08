package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.PostRemove;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todas as missões", description = "Retorna uma lista com todas as missões cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de missões retornada com sucesso."),
            @ApiResponse(responseCode = "204", description = "Nenhuma missão encontrada.")
    })
    public ResponseEntity<?> listar() {
        List<MissoesDTO> lista = missoesService.lista();
        if (lista.isEmpty()) {
            return ResponseEntity.ok("Ainda nao existe essa Missao no sistema, Tente Novamente.");
        } else {
            return ResponseEntity.ok(lista);
        }
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Busca missão por ID", description = "Retorna uma missão específica com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    public ResponseEntity<?> listarID(@PathVariable long id) {
        MissoesDTO missoesDTO = missoesService.listaID(id);
        if (missoesDTO != null) {
            return ResponseEntity.ok(missoesDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nao existe Missao do Id " + id + ", tente novamente.");
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Adiciona nova missão", description = "Cria uma nova missão no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar a missão."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    public ResponseEntity<?> Adicionar(@RequestBody MissoesDTO missoesDTO) {
        missoesService.adicionarNinja(missoesDTO);
        return ResponseEntity.ok(missoesDTO.getNome() + " (" + missoesDTO.getId() + " ) - foi Adicionado.");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleta uma missão", description = "Remove uma missão do sistema com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    public ResponseEntity<?> Deletar(@PathVariable Long id) {
        MissoesDTO missoesDTO = missoesService.listaID(id);
        if (missoesDTO != null) {
            missoesService.remover(id);
            return ResponseEntity.ok("A missao " + missoesDTO.getNome() + " foi deletada.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nao existe Missao no id " + id);
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera missão existente", description = "Atualiza os dados de uma missão com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar a missão.")
    })
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody MissoesDTO missoesModel) {
        MissoesDTO missoesDTO = missoesService.listaID(id);
        if (missoesDTO != null) {
            missoesService.alterar(id, missoesModel);
            return ResponseEntity.ok("A missao no ID " + id + " foi alterado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nao existe Missao no id " + id);
        }
    }
}