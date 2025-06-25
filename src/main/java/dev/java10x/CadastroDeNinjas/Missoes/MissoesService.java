package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> lista(){
        return missoesRepository.findAll();
    }
    public MissoesModel listaID(Long id){
        Optional<MissoesModel> a = missoesRepository.findById(id);
        return a.orElse(null);
    }
    public MissoesModel adicionarNinja(MissoesModel missoesModel) {
        missoesRepository.save(missoesModel);
        return missoesModel;
    }

    public void remover(Long id) {
        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        missoesRepository.delete(missoesModel.orElse(null));
    }
}
