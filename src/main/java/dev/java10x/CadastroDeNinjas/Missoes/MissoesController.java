package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaService;
import jakarta.persistence.PostRemove;
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
    public List<MissoesModel> listar(){
        return missoesService.lista();
    }

    @GetMapping("/listar/{id}")
    public MissoesModel listarID(@PathVariable long id) {
        return  missoesService.listaID(id);
    }
    @PostMapping("/add")
    public MissoesModel Adicionar(@RequestBody MissoesModel missoesModel) {
        missoesService.adicionarNinja(missoesModel);
        return missoesModel;
    }
    @DeleteMapping("/delete/{id}")
    public void Deletar(@PathVariable Long id) {
        missoesService.remover(id);
    }

    @PutMapping("/alterar/{id}")
    public MissoesModel alterar(@PathVariable Long id,@RequestBody MissoesModel missoesModel) {
        return  missoesService.alterar(id,missoesModel);
    }
}
