package com.restaurante.app.persistence.models;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private Set<Mesas> mesas;
    @Column(name="nombre", length=100, nullable=false)
    private String nombre;

    @Column(name="cantMesas", length=100, nullable=false)
    private int cantMesas;
    private String propietario;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantMesas() {
        return cantMesas;
    }

    public void setCantMesas(int cantMesas) {
        this.cantMesas = cantMesas;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
