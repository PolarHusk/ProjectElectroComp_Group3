package com.mycompany.grupo3proyectoelectrocomp;

import javax.swing.JOptionPane;

import java.time.LocalTime;

public class Ordenes {

    // Atributos
    private int numeroOrden;
    private Clientes cliente;
    private Usuario usuario;
    private TipoDispositivo dispositivo;
    private String marca;
    private String modelo;
    private String problema;
    private EstadoOrden estado;
    private LocalTime horaOrden;
    private static Ordenes[] ordenes = new Ordenes[120];
    private static int numeroOrdenes = 0;
    private static int contadorOrden = 3000;

    // Constructor
    public Ordenes(Clientes cliente, Usuario usuario, TipoDispositivo dispositivo, String marca, String modelo, String problema) {

        this.cliente = cliente;
        this.usuario = usuario;
        this.dispositivo = dispositivo;
        this.marca = marca;
        this.modelo = modelo;
        this.problema = problema;
        this.estado = EstadoOrden.Asignada;
        this.horaOrden = LocalTime.now();
        setNumeroOrden();

        if (numeroOrdenes < ordenes.length) {
            ordenes[numeroOrdenes] = this;
            numeroOrdenes++;
        } else {
            System.out.println("Existe un limite de 120 ordenes (4 por cliente)");
        }
    }

    public Ordenes() {

    }

    // Comportamientos
    public static void mostrarOrdenes() {
        for (int i = 0; i < numeroOrdenes; i++) {
            Ordenes orden = ordenes[i];
            System.out.println("Orden número: " + orden.numeroOrden + "\n"
                    + "Nombre del Cliente: " + orden.cliente.getNombre() + "\n"
                    + "Técnico asignado: " + orden.usuario.getNombre() + "\n"
                    + "Dispositivo: " + orden.dispositivo + "\n"
                    + "Marca: " + orden.marca + "\n"
                    + "Modelo: " + orden.modelo + "\n"
                    + "Problema: " + orden.problema + "\n"
                    + "Estado: " + orden.estado + "\n"
                    + "Hora de la orden: " + orden.horaOrden + "\n"
                    + "--------------------------------------------");
        }
    }

    public void crearOrden() {
        if (numeroOrdenes >= ordenes.length) {
            JOptionPane.showMessageDialog(null, "Límite de órdenes alcanzado (120 órdenes).");
            return;
        }

        String clienteID = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
        Clientes clienteSeleccionado = null;
        for (int i = 0; i < Clientes.numeroClientes; i++) {
            if (Clientes.clientes[i].getID().equals(clienteID)) {
                clienteSeleccionado = Clientes.clientes[i];
                break;
            }
        }

        if (clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }

        String usuarioID = JOptionPane.showInputDialog("Ingrese el código del técnico asignado:");
        Usuario usuarioAsignado = null;
        for (int i = 0; i < Usuario.numeroUsuarios; i++) {
            if (Usuario.usuarios[i].getCodigo().equals(usuarioID)) {
                usuarioAsignado = Usuario.usuarios[i];
                break;
            }
        }

        if (usuarioAsignado == null) {
            JOptionPane.showMessageDialog(null, "Técnico no encontrado.");
            return;
        }

        String[] dispositivos = {"Laptop", "PC", "Celular", "Tablet"};
        TipoDispositivo dispositivoSeleccionado = TipoDispositivo.valueOf((String) JOptionPane.showInputDialog(null, "Seleccione el tipo de dispositivo:", "Tipo de Dispositivo", JOptionPane.QUESTION_MESSAGE, null, dispositivos, dispositivos[0]));

        String marca = JOptionPane.showInputDialog("Ingrese la marca del dispositivo:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del dispositivo:");
        String problema = JOptionPane.showInputDialog("Describa el problema del dispositivo:");

        Ordenes nuevaOrden = new Ordenes(clienteSeleccionado, usuarioAsignado, dispositivoSeleccionado, marca, modelo, problema);

        JOptionPane.showMessageDialog(null, "Orden creada exitosamente:\n"
                + "Número de Orden: " + nuevaOrden.numeroOrden + "\n"
                + "Cliente: " + nuevaOrden.cliente.getNombre() + "\n"
                + "Técnico: " + nuevaOrden.usuario.getNombre() + "\n"
                + "Dispositivo: " + nuevaOrden.dispositivo + "\n"
                + "Marca: " + nuevaOrden.marca + "\n"
                + "Modelo: " + nuevaOrden.modelo + "\n"
                + "Problema: " + nuevaOrden.problema + "\n"
                + "Estado: " + nuevaOrden.estado);
    }

    // Getter y Setter
    public void setNumeroOrden() {
        if (contadorOrden <= 3120) {
            this.numeroOrden = contadorOrden;
            contadorOrden++;
        } else {
            System.out.println("Ya se ha alcanzado el límite de 120 órdenes.");
        }
    }

    public TipoDispositivo getDispositivo() {
        return dispositivo;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public int getNumeroOrden() {
        return numeroOrden;
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
        this.usuario = usuario;
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
        return usuario;
    }
    
    

}
