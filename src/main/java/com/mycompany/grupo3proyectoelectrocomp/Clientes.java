package com.mycompany.grupo3proyectoelectrocomp;

import javax.swing.JOptionPane;

public class Clientes {

    // Atributos
    private String ID;
    private String nombre;
    private String telefono;
    private String correo;
    private TipoCliente tipo;

    static Clientes[] clientes = new Clientes[30];

    static int numeroClientes = 0;

    // Metodo constructor
    public Clientes(String ID, String nombre, String telefono, String correo, TipoCliente tipo) {
        this.nombre = nombre;
        this.ID = ID;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;

        if (numeroClientes < clientes.length) {
            clientes[numeroClientes] = this;
            numeroClientes++;
        } else {
            System.out.println("Existe un limite de 30 clientes");
        }
    }

    public Clientes() {
    }

    // Comportamientos
    public static void mostrarClientes() {
        for (int i = 0; i < numeroClientes; i++) {
            Clientes cliente = clientes[i];
            System.out.println("ID: " + cliente.ID + "\nNombre completo: " + cliente.nombre + "\nTelefono: " + cliente.telefono + "\nCorreo: " + cliente.correo + "\nTipo Cliente: " + cliente.tipo + "#################\n");
        }
    }

    public void agregarCliente() {

        String nombreNuevo = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
        setNombre(nombreNuevo);

        String telefonoNuevo = JOptionPane.showInputDialog("Ingrese el Numero de Telefono: ");
        setTelefono(telefonoNuevo);

        String correoNuevo = JOptionPane.showInputDialog("Ingrese su correo: ");
        setCorreo(correoNuevo);

        setTipoCliente();

        Clientes nuevoCliente = new Clientes(this.ID, this.nombre, this.telefono, this.correo, this.tipo);

        JOptionPane.showMessageDialog(null,
                """
                Cliente Agregado
                ID: """ + nuevoCliente.ID + "\n"
                + "Nombre: " + nuevoCliente.nombre + "\n"
                + "Teléfono: " + nuevoCliente.telefono + "\n"
                + "Correo: " + nuevoCliente.correo + "\n"
                + "Tipo: " + nuevoCliente.tipo);

    }

    public Clientes buscarIDCliente(String ID) {
        for (int i = 0; i < numeroClientes; i++) {
            if (clientes[i].ID.equals(ID)) {
                return clientes[i];
            }
        }
        return null;
    }

    // Getter y Setter
    public String getID() {
        return ID;

    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public static int getNumeroClientes() {
        return numeroClientes;
    }

    public String getTelefono() {
        return telefono;
    }

    public TipoCliente getTipo() {
        return tipo;
    }
    
    

    public void setID() {
        String IDEntrada = JOptionPane.showInputDialog("Ingrese el ID del Cliente: ");
        if (buscarIDCliente(IDEntrada) != null) {
            JOptionPane.showMessageDialog(null, "El ID del Cliente ya se encuentra registrado");
            String[] opcionesAgregarCliente = {
                "Agregar Otro Cliente",
                "Cancelar"
            };
            int opcion = JOptionPane.showOptionDialog(null,
                    "Selecciona una opción",
                    "ID ya registrado",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcionesAgregarCliente,
                    opcionesAgregarCliente[0]);
            switch (opcion) {

                case 0:
                    setID();
                    break;

                case 1:
                    JOptionPane.showMessageDialog(null, "Cancelando...");
                    break;
            }
        } else {
            this.ID = IDEntrada;
            agregarCliente();
        }
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
        String[] opciones = {"premium", "platino", "oro"};
        String opcion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el tipo de cliente:",
                "Tipo de Cliente",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == null) {
            return null;
        }
        switch (opcion) {
            case "premium":
                return this.tipo = TipoCliente.Premium;
            case "platino":
                return this.tipo = TipoCliente.Platino;
            case "oro":
                return this.tipo = TipoCliente.Oro;
            default:
                return null;
        }
    }
}
