package digital.rj.apicadastrodecursos.Faculdades.Model;


import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;
import java.util.List;
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_faculdades")
public class FaculdadeModel {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "isActive",nullable = false)
    private boolean isActive = true;

    @Column(name="cnpj",unique = true,nullable = false)
    private String cnpj;


    @ManyToMany
    private List<CursosModel> cursos;
}
