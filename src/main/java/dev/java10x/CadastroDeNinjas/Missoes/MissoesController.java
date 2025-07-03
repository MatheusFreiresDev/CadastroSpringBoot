package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaService;
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
    public ResponseEntity<?> listar(){
        List<MissoesDTO> lista =  missoesService.lista();
        if (lista.isEmpty()){
            return ResponseEntity.ok("Ainda nao existe essa Missao no sistema, Tente Novamente.");
        } else {
            return ResponseEntity.ok(lista);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarID(@PathVariable long id) {
        MissoesDTO missoesDTO = missoesService.listaID(id);
        if (missoesDTO != null) {
            return ResponseEntity.ok(missoesDTO);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nao existe Missao do Id "  + id + ", tente novamente.");
        }

    }
    @PostMapping("/add")
    public ResponseEntity<?> Adicionar(@RequestBody MissoesDTO missoesDTO) {
        missoesService.adicionarNinja(missoesDTO);
        return ResponseEntity.ok(missoesDTO.getNome() + " (" + missoesDTO.getId() + " ) - foi Adicionado.");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> Deletar(@PathVariable Long id) {
        MissoesDTO missoesDTO = missoesService.listaID(id);
        if (missoesDTO != null ) {
            missoesService.remover(id);
            return ResponseEntity.ok("A missao " + missoesDTO.getNome() + " foi deletada.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nao existe Missao no id " + id );
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id,@RequestBody MissoesDTO missoesModel) {
        MissoesDTO missoesDTO = missoesService.listaID(id);
        if (missoesDTO != null) {
            missoesService.alterar(id,missoesModel);
            return ResponseEntity.ok("A missao no ID " + id + " foi alterado." );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nao existe Missao no id " + id);
        }

    }
}
