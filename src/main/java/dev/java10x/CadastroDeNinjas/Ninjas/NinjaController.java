package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController{

    NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    //CRUDzadaa
// add ninja
    @PostMapping("/add")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaDTO.getNome() + "(" + ninjaDTO.getId() + ") Foi criado com sucesso");

    }
    // procurar ninja id
    @GetMapping("/listarId/{id}")
    public ResponseEntity<?> listarNinjaId(@PathVariable long id) {
        NinjaDTO ninja = ninjaService.listarNinjaId(id);
        if(ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja no id " + id + " nao existe no nossos registros, tente novamente.");
        }
    }
    // listar ninjas
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> list =  ninjaService.listarNinja();
        return ResponseEntity.ok(list);
    }
    //alterar dados
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinja(@PathVariable long id,@RequestBody NinjaDTO ninjaDTO) {;
        NinjaDTO ninjaAntigo = ninjaService.listarNinjaId(id);
        if (ninjaAntigo != null) {
            ninjaService.atualizar(id,ninjaDTO);
            NinjaDTO ninjaAtualizado  = ninjaService.listarNinjaId(id);
            return ResponseEntity.ok(ninjaAtualizado) ;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Esse ninja nao existe no nosso sistema, tente novamente");
        }
    }
    //deletar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable long id) {
        NinjaDTO ninjaDTO = ninjaService.listarNinjaId(id);
        if(ninjaService.listarNinjaId(id) != null) {
            ninjaService.deletando(id);
            return ResponseEntity.ok(ninjaDTO.getNome() + " Foi deletado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja nao foi encontrado, por favor tente novamente.");
        }
    }

}

