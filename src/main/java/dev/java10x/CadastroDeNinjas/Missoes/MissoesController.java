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
    public List<MissoesDTO> listar(){
        return missoesService.lista();
    }

    @GetMapping("/listar/{id}")
    public MissoesDTO listarID(@PathVariable long id) {
        return  missoesService.listaID(id);
    }
    @PostMapping("/add")
    public MissoesDTO Adicionar(@RequestBody MissoesDTO missoesDTO) {
        return missoesService.adicionarNinja(missoesDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void Deletar(@PathVariable Long id) {
        missoesService.remover(id);
    }

    @PutMapping("/alterar/{id}")
    public MissoesDTO alterar(@PathVariable Long id,@RequestBody MissoesDTO missoesModel) {
        return  missoesService.alterar(id,missoesModel);
    }
}
