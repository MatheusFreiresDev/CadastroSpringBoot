package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //listar todos ninjas
    public List<NinjaDTO> listarNinja(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas .stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());

    };
    public NinjaDTO listarNinjaId(Long id) {
        Optional<NinjaModel> ninjaModel = ninjaRepository.findById(id);
        return ninjaModel.map(ninjaMapper::map).orElse(null);
    }
    public NinjaDTO criarNinja(NinjaDTO ninjadto){
        NinjaModel ninja = ninjaMapper.map(ninjadto);
        ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    public  void deletando(Long id) {
      ninjaRepository.deleteById(id);
    }

    public NinjaDTO atualizar(Long id, NinjaDTO ninja) {
        Optional<NinjaModel> ninjaModel = ninjaRepository.findById(id);
    if(ninjaModel.isPresent()){
        NinjaModel ninjaAtual = ninjaMapper.map(ninja);
        ninjaRepository.save(ninjaAtual);
        return ninjaMapper.map(ninjaAtual);
    }
    return null;
    }}


