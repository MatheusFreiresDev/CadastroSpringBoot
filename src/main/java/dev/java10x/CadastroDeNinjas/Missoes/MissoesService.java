package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {
    MissoesMapper missoesMapper;
    MissoesRepository missoesRepository;

    public MissoesService(MissoesMapper missoesMapper, MissoesRepository missoesRepository) {
        this.missoesMapper = missoesMapper;
        this.missoesRepository = missoesRepository;
    }

    // Listar TODOS
    public List<MissoesDTO> lista(){
        List<MissoesModel> listaModel = missoesRepository.findAll();
        return listaModel.stream().map(missoesModel -> missoesMapper.map(missoesModel)).collect(Collectors.toList());
    }

    //por id

    public MissoesDTO listaID(Long id){
        Optional<MissoesModel> a = missoesRepository.findById(id);
       if(a.isPresent()) {
          MissoesModel b = a.get();
          return missoesMapper.map(b);
       }
       return null;
    }


    public MissoesDTO adicionarNinja(MissoesDTO missoesDTO) {
        missoesRepository.save(missoesMapper.map(missoesDTO));
        return missoesDTO;
    }


    public void remover(Long id) {
        missoesRepository.deleteById(id);
    }
    public MissoesDTO alterar(Long id, MissoesDTO missoesDTO) {
        if(missoesRepository.existsById(id)){
            MissoesModel missoesModel = missoesMapper.map(missoesDTO);
            missoesModel.setId(id);
            missoesRepository.save(missoesModel);
            return missoesMapper.map(missoesModel);
        }
        return null;

    }
}
