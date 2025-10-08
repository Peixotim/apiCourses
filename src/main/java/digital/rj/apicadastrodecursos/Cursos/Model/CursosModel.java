package digital.rj.apicadastrodecursos.Cursos.Model;

import digital.rj.apicadastrodecursos.Tecnicos.Model.TecnicosModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cursos")
public class CursosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "category",nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "Type",nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToMany(mappedBy = "cursos")
    private List<TecnicosModel> tecnicos;
}
