package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class Paquete {
    private int id;
    private LocalDate fx_creacion;
    private List<Huevo> huevos;

    public Paquete() {
        this.fx_creacion = LocalDate.now();
    }

    public Paquete(int id, List<Huevo> huevos) {
        this.id = id;
        this.fx_creacion = LocalDate.now();
        this.huevos = huevos;
    }
}
