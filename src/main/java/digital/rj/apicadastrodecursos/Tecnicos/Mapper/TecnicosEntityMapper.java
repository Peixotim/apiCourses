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
        return model;
    }

    public TecnicosDTO map(TecnicosModel model){
        TecnicosDTO dto = new TecnicosDTO();
        model.setName(dto.getName());
        model.setId(dto.getId());
        model.setActive(dto.isActive());
        return dto;
    }


}
