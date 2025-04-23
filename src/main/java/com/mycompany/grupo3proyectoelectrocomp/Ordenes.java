package com.mycompany.grupo3proyectoelectrocomp;

import java.time.LocalTime;

public class Ordenes {

    // Atributos
    private int numeroOrden;
    private Clientes cliente;
    private Usuario tecnico;
    private TipoDispositivo dispositivo;
    private String marca;
    private String modelo;
    private String problema;
    private EstadoOrden estado;
    private LocalTime horaOrden;
    private static int contadorOrden = 3000;
    private String solucion;
    private double costo;
    private double precio;

    // Constructor
    public Ordenes(Clientes cliente, Usuario usuario, TipoDispositivo dispositivo, String marca, String modelo, String problema) {

        this.cliente = cliente;
        this.tecnico = usuario;
        this.dispositivo = dispositivo;
        this.marca = marca;
        this.modelo = modelo;
        this.problema = problema;
        this.estado = EstadoOrden.Asignada;
        this.horaOrden = LocalTime.now();
        this.numeroOrden = generarNumeroOrden();

    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Comportamientos
    public String mostrarInfo() {
        return "Orden número: " + numeroOrden + "\n"
                + "Nombre del Cliente: " + cliente.getNombre() + "\n"
                + "Técnico asignado: " + tecnico.getNombre() + "\n"
                + "Dispositivo: " + dispositivo + "\n"
                + "Marca: " + marca + "\n"
                + "Modelo: " + modelo + "\n"
                + "Problema: " + problema + "\n"
                + "Estado: " + estado + "\n"
                + "Hora de la orden: " + horaOrden + "\n"
                + "--------------------------------------------";
    }

    public String mostrarInfoOrdenCerrada() {
        return "Orden número: " + numeroOrden + "\n"
                + "Nombre del Cliente: " + cliente.getNombre() + "\n"
                + "Técnico asignado: " + tecnico.getNombre() + "\n"
                + "Dispositivo: " + dispositivo + "\n"
                + "Marca: " + marca + "\n"
                + "Modelo: " + modelo + "\n"
                + "Problema: " + problema + "\n"
                + "Estado: " + estado + "\n"
                + "Hora de la orden: " + horaOrden + "\n"
                + "Solucion: " + solucion + "\n"
                + "Costo de la orden(Sin Descuento): " + costo + "\n"
                + "Precio total con descuento: " + precio + "\n"
                + "--------------------------------------------";
    }

    private static int generarNumeroOrden() {
        return contadorOrden++;
    }

    // Getter y Setter
    public TipoDispositivo getDispositivo() {
        return dispositivo;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public LocalTime getHoraOrden() {
        return horaOrden;
    }

    public void setDispositivo(TipoDispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public void setUsuario(Usuario usuario) {
        this.tecnico = usuario;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public String getProblema() {
        return problema;
    }

    public Usuario getUsuario() {
        return tecnico;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

}
