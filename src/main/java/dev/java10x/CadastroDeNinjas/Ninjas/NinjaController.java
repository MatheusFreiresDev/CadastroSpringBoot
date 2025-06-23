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
    public String criarNinja() {
    return "Ninja Criado";
}
// procurar ninja id
@GetMapping("/id")
public String listarNinjaId() {
    return "Ninja Criado";
}
// listar ninjas
@GetMapping("/todos")
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
