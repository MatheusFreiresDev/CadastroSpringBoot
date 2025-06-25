package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {
    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    //listar todos ninjas
    public List<NinjaModel> listarNinja(){
    return ninjaRepository.findAll();

    };
    public NinjaModel listarNinjaId(Long id) {
        Optional<NinjaModel> ninjaModel = ninjaRepository.findById(id);
        return ninjaModel.orElse(null);
    }
    public NinjaModel criarNinja(NinjaModel ninja){
        return ninjaRepository.save(ninja);
}

    public  void deletando(Long id) {
        Optional<NinjaModel> ninjaModel = ninjaRepository.findById(id);
        ninjaRepository.delete(ninjaModel.orElse(null));
    }

    public NinjaModel atualizar(Long id, NinjaModel ninja) {
    if(ninjaRepository.existsById(id)) {
        ninja.setId(id);
        ninjaRepository.save(ninja);
        return ninja;
    }
        return null;
    }

}

