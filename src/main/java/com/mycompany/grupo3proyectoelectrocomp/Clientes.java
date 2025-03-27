package com.mycompany.grupo3proyectoelectrocomp;

import javax.swing.JOptionPane;

public class Clientes {

    // Atributos
    private String ID;
    private String nombre;
    private String telefono;
    private String correo;
    private TipoCliente tipo;
    private Ordenes ordenesCliente[] = new Ordenes[4];
    private int numeroOrdenesCliente = 0;

    // Metodo constructor
    public Clientes(String ID, String nombre, String telefono, String correo, TipoCliente tipo) {
        this.nombre = nombre;
        this.ID = ID;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;

    }

    public Clientes() {
    }

    // Comportamientos
    public boolean agregarOrden(Ordenes orden) {
        if (numeroOrdenesCliente < 4) {
            ordenesCliente[numeroOrdenesCliente] = orden;
            numeroOrdenesCliente++;
            return true;
        } else {
            return false;
        }
    }
    
    public String mostrarInfo() {
        return "ID: " + ID + "\nNombre completo: " + nombre + "\nTelefono: " + telefono + "\nCorreo: " + correo + "\nTipo Cliente: " + tipo + "\nNumero de Ordenes registradas: " + numeroOrdenesCliente + "\n#################";

    }

    // Getter y Setter
    public int getNumeroOrdenes() {
        return numeroOrdenesCliente;
    }

    public Ordenes[] getOrdenesCliente() {
        return ordenesCliente;
    }

    public String getID() {
        return ID;

    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        while (true) {
            if (telefono == null) {
                this.telefono = "Sin Datos";
            } else if (telefono.length() == 9 && telefono.charAt(4) == '-') {
                this.telefono = telefono;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Numero de telefono en un formato invalido. Ingrese nuevamente");
                telefono = JOptionPane.showInputDialog("Ingrese el telefono nuevamente formato 0000-0000: ");
            }
        }
    }

    public void setCorreo(String correo) {

        while (true) {
            if (correo != null && correo.contains("@")) {
                int posicionArroba = correo.indexOf("@");
                if (posicionArroba != -1 && correo.indexOf(".", posicionArroba) > posicionArroba) {
                    this.correo = correo;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Correo inválido. Asegúrate de que el correo contenga un punto después de '@'.");
                    correo = JOptionPane.showInputDialog("Ingrese el correo electrónico:");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Correo inválido. Asegúrate de que el correo contenga '@'.");
                correo = JOptionPane.showInputDialog("Ingrese el correo electrónico:");
            }
        }

    }

    public TipoCliente setTipoCliente() {
        String opciones[] = {"premium", "platino", "oro"};

        int opcion = JOptionPane.showOptionDialog(null, "Seleccione un tipo de suscripcion", "Tipo de suscripcion del cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);

        switch (opcion) {
            case 0:
                return this.tipo = TipoCliente.Premium;
            case 1:
                return this.tipo = TipoCliente.Platino;
            case 2:
                return this.tipo = TipoCliente.Oro;
            default:
                return null;
        }

    }

}
