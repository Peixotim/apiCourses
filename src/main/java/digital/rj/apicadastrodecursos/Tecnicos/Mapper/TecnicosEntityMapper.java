package digital.rj.apicadastrodecursos.Tecnicos.Mapper;

import digital.rj.apicadastrodecursos.Tecnicos.DTOs.Entity.TecnicosDTO;
import digital.rj.apicadastrodecursos.Tecnicos.Model.TecnicosModel;
import org.springframework.stereotype.Component;

@Component
public class TecnicosEntityMapper {

    public TecnicosModel map(TecnicosDTO dto){
        TecnicosModel model = new TecnicosModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setActive(dto.isActive());
        model.setCursos(dto.getCursos());
        model.setCnpj(dto.getCnpj());
        return model;
    }

    public TecnicosDTO map(TecnicosModel model){
        TecnicosDTO dto = new TecnicosDTO();
        dto.setName(dto.getName());
        dto.setId(dto.getId());
        dto.setActive(dto.isActive());
        dto.setCursos(model.getCursos());
        dto.setCnpj(model.getCnpj());
        return dto;
    }


}
