package com.restaurante.app.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Alimentos")
public class Alimento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="idMenu", referencedColumnName = "id")
    private Menu menu;

    @Lob()
    private byte[] imagen;

    private String nombre;

    private String descripcion;

    private float precio;

    private boolean disponibilidad;

    @Enumerated(EnumType.STRING)
    private TipoMenu tipo;

    @OneToMany(mappedBy = "alimentos")
    private Set<DetallePedido> DetallePedidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public TipoMenu getTipo() {
        return tipo;
    }

    public void setTipo(TipoMenu tipo) {
        this.tipo = tipo;
    }

    public Set<DetallePedido> getDetallePedidos() {
        return DetallePedidos;
    }

    public void setDetallePedidos(Set<DetallePedido> DetallePedidos) {
        this.DetallePedidos = DetallePedidos;
    }
}
