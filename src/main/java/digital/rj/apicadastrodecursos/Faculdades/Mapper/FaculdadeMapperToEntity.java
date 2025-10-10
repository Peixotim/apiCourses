package digital.rj.apicadastrodecursos.Faculdades.Mapper;

import digital.rj.apicadastrodecursos.Faculdades.DTOs.FaculdadeDTO;
import digital.rj.apicadastrodecursos.Faculdades.Model.FaculdadeModel;
import org.springframework.stereotype.Component;

@Component
public class MapperToEntity {


    public FaculdadeModel map(FaculdadeDTO dto){
        FaculdadeModel model = new FaculdadeModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCnpj(dto.getCnpj());
        model.setCursos(dto.getCursos());
        model.setActive(dto.isActive());
        return model;
    }

    public FaculdadeDTO map(FaculdadeModel model){
        FaculdadeDTO dto = new FaculdadeDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCnpj(model.getCnpj());
        dto.setCursos(model.getCursos());
        model.setActive(model.isActive());
        return dto;
    }
}
