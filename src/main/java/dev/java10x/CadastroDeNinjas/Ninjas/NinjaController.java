package dev.java10x.CadastroDeNinjas.Ninjas;

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
public NinjaModel criarNinja(@RequestBody NinjaModel ninja) {

    return ninjaService.criarNinja(ninja);
}
// procurar ninja id
@GetMapping("/listarId/{id}")
public NinjaModel listarNinjaId(@PathVariable long id) {
    NinjaModel ninja = ninjaService.listarNinjaId(id);
    return ninja;
    }
// listar ninjas
@GetMapping("/listar")
public List<NinjaModel> listarNinjas() {
    return ninjaService.listarNinja();
}
//alterar dados
@PutMapping("/alterar")
public String alterarNinja() {
    return "Ninja Criado";
}
//deletar
@DeleteMapping("/deletar")
    public String deletarNinja() {
        return "Ninja Criado";
    }

}
