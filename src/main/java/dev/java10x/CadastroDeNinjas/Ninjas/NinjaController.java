package dev.java10x.CadastroDeNinjas.Ninjas;

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
public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja) {

    return ninjaService.criarNinja(ninja);
}
// procurar ninja id
@GetMapping("/listarId/{id}")
public NinjaDTO listarNinjaId(@PathVariable long id) {
    NinjaDTO ninja = ninjaService.listarNinjaId(id);
    return ninja;
    }
// listar ninjas
@GetMapping("/listar")
public List<NinjaDTO> listarNinjas() {
    return ninjaService.listarNinja();
}
//alterar dados
@PutMapping("/alterar/{id}")
public NinjaDTO alterarNinja(@PathVariable long id,@RequestBody NinjaDTO ninjaDTO) {;
    return  ninjaService.atualizar(id,ninjaDTO);
    }
//deletar
@DeleteMapping("/deletar/{id}")
    public void deletarNinja(@PathVariable long id) {
        ninjaService.deletando(id);
    }

}
