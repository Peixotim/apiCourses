package digital.rj.apicadastrodecursos.Cursos.Mapper;

import digital.rj.apicadastrodecursos.Cursos.DTOs.CursosDTO;
import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import org.springframework.stereotype.Component;

@Component
public class MapperToEntity {

    public CursosModel map(CursosDTO dto){
        CursosModel model = new CursosModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCategory(dto.getCategory());
        model.setType(dto.getType());
        return model;
    }

    public CursosDTO map(CursosModel model){
        CursosDTO dto = new CursosDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCategory(model.getCategory());
        dto.setType(model.getType());
        return dto;
    }


}
