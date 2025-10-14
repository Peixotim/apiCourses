package digital.rj.apicadastrodecursos.Tecnicos.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import digital.rj.apicadastrodecursos.Cursos.Model.CursosModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_tecnicos")
public class TecnicosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name" , unique = true,nullable = false)
    private String name;

    @Column(name = "cnpj",nullable = false,unique = true)
    private String cnpj;

    @Column(name = "isActive",nullable = false)
    private boolean isActive = true;

    @ManyToMany
    @JoinTable(
            name = "tb_tecnico_curso",
            joinColumns = @JoinColumn(name = "tecnico_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    @JsonIgnore
    private List<CursosModel> cursos;

}
