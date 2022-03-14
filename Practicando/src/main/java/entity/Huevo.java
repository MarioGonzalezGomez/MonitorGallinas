package entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Huevo {
    private String id;
    private LocalDateTime creacion;
    private String nombreGallina;
    private int yemas;

    public Huevo() {
        this.creacion = LocalDateTime.now();
        this.yemas = numYemas();
    }

    public Huevo(String id, String nombreGallina) {
        this.id = id;
        this.creacion = LocalDateTime.now();
        this.nombreGallina = nombreGallina;
        this.yemas = numYemas();
    }

    private int numYemas() {
        int random = (int) Math.floor(Math.random() * 101);
        if (random <= 33) {
            random = 2;
        } else {
            random = 1;
        }
        return random;
    }
}
