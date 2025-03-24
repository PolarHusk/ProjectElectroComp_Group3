package com.mycompany.grupo3proyectoelectrocomp;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class Gestion {

    private static Clientes[] clientes = new Clientes[30];
    private static int numeroClientes = 0;

    private static Usuario[] usuarios = new Usuario[20];
    private static int numeroUsuarios = 0;

    private static Ordenes[] ordenes = new Ordenes[120];
    private static int numeroOrdenes = 0;

    public static void datosIniciales() {
        usuarios[0] = new Usuario("Dylan", "admin123", Rol.Administrador, "admin");
        usuarios[1] = new Usuario("Samuel", "sam123", Rol.Tecnico, "samp");
        usuarios[2] = new Usuario("Juan", "juan123", Rol.Tecnico, "juanp");
        usuarios[3] = new Usuario("Angeles", "ang123", Rol.Tecnico, "angep");

        clientes[0] = new Clientes("123456", "Alexander", "8888-8888", "dcalderon@ufide.ac.cr", TipoCliente.Premium);
        clientes[1] = new Clientes("1234567", "Helena", "8888-8889", "hfide@ufide.ac.cr", TipoCliente.Oro);

        ordenes[0] = new Ordenes(clientes[0], usuarios[1], TipoDispositivo.Laptop, "HP", "Victus 16", "Fallo en Windows");
        ordenes[1] = new Ordenes(clientes[1], usuarios[2], TipoDispositivo.Tablet, "Samsung", "S23", "Fallo de Pantalla");

        numeroClientes = 2;
        numeroUsuarios = 4;
        numeroOrdenes = 2;
    }

    // OPCIONES DEL MENU PARA LA GESTION
    public static void mostrarClientes() {
        if (numeroClientes == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados");
            return;
        }

        System.out.println("------LISTA DE CLIENTES-------");
        for (int i = 0; i < numeroClientes; i++) {
            System.out.println(clientes[i].mostrarInfo());
        }

    }

    public static void mostrarUsuarios() {
        if (numeroUsuarios == 0) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        for (int i = 0; i < numeroUsuarios; i++) {
            System.out.println(usuarios[i].mostrarInfo());
        }
    }

    public static void mostrarOrdenes() {

        if (numeroOrdenes == 0) {
            JOptionPane.showMessageDialog(null, "No hay ordenes registradas");
        }
        System.out.println("\n---LISTA DE ORDENES---");
        for (int i = 0; i < numeroOrdenes; i++) {
            System.out.println(ordenes[i].mostrarInfo());
        }
    }

    public static void agregarCliente() {
        if (numeroClientes >= clientes.length) {
            System.out.println("Existe un limite de 30 clientes");

        }
        String IDEntrada = JOptionPane.showInputDialog("Ingrese el ID del Cliente: ");
        String ID;
        for (int i = 0; i < numeroClientes; i++) {
            if (clientes[i].getID().equals(IDEntrada)) {
                JOptionPane.showMessageDialog(null, "El ID del Cliente ya se encuentra registrado");
                String opcionesAgregarCliente[] = {
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
                        agregarCliente();
                        return;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Cancelando...");
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "ERROR");
                }
                break;
            }
        }
        ID = IDEntrada;

        String nombreNuevo = JOptionPane.showInputDialog("Ingrese el nombre completo del cliente: ");

        String telefono;
        while (true) {
            String telefonoNuevo = JOptionPane.showInputDialog("Ingrese el Numero de Telefono: (FORMATO 0000-0000) (No ingrese ningun valor si no posee numero de telefono)");

            if (telefonoNuevo == null || telefonoNuevo.length() == 0) {
                telefono = "Sin Datos";
                break;
            } else if (telefonoNuevo.length() == 9 && telefonoNuevo.charAt(4) == '-') {
                telefono = telefonoNuevo;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Numero de telefono en un formato invalido. Ingrese nuevamente");
            }

        }

        String correo;
        while (true) {
            String correoNuevo = JOptionPane.showInputDialog("Ingrese su correo electronico Formato(xxxxx@xxxx.com): ");
            if (correoNuevo != null && correoNuevo.contains("@")) {
                int posicionArroba = correoNuevo.indexOf("@");
                if (posicionArroba != -1 && correoNuevo.indexOf(".", posicionArroba) > posicionArroba) {
                    correo = correoNuevo;
                    break;
                }
            }

            String opcionesCorreo[] = {"Agregar otro correo", "Cancelar"};

            int opt = JOptionPane.showOptionDialog(null, "Correo Invalido", "Seleccione una opcion", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcionesCorreo, opcionesCorreo[0]);

            switch (opt) {
                case 0:
                    continue;
                case 1:
                    return;
            }
        }

        String opciones[] = {"premium", "platino", "oro"};

        int opcion = JOptionPane.showOptionDialog(null, "Seleccione un tipo de suscripcion", "Tipo de suscripcion del cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);

        TipoCliente tipo;

        switch (opcion) {
            case 0:
                tipo = TipoCliente.Premium;
                break;
            case 1:
                tipo = TipoCliente.Platino;
                break;
            case 2:
                tipo = TipoCliente.Oro;
                break;
            default:
                tipo = TipoCliente.Oro;
        }

        clientes[numeroClientes] = new Clientes(ID, nombreNuevo, telefono, correo, tipo);

        JOptionPane.showMessageDialog(null,
                """
                Cliente Agregado Existosamente!
                ID: """ + clientes[numeroClientes].getID() + "\n"
                + "Nombre: " + clientes[numeroClientes].getNombre() + "\n"
                + "Teléfono: " + clientes[numeroClientes].getTelefono() + "\n"
                + "Correo: " + clientes[numeroClientes].getCorreo() + "\n"
                + "Tipo: " + clientes[numeroClientes].getTipo());

        numeroClientes++;

    }

    public static void agregarUsuario() {
        if (numeroUsuarios >= usuarios.length) {
            JOptionPane.showMessageDialog(null, "Límite de usuarios alcanzado (20 usuarios).");
        }

        String usuarioNuevo = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        for (int i = 0; i < numeroUsuarios; i++) {
            if (usuarios[i].getUsuario().equals(usuarioNuevo)) {
                JOptionPane.showMessageDialog(null, "Usuario ya agregado al sistema");
                String opcionesAgregarUsuario[] = {"Agregar Otro Cliente", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(null, "Selecciona una opcion", "Usuario ya registrado", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcionesAgregarUsuario, opcionesAgregarUsuario[0]);
                switch (opcion) {
                    case 0:
                        agregarUsuario();
                    case 1:
                        JOptionPane.showMessageDialog(null, "Cancelando");
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "ERROR");
                }
                break;
            }
        }
        String usuario = usuarioNuevo;

        String nombreNuevo = JOptionPane.showInputDialog("Ingrese su nombre completo: ");

        String clave = solicitarClave();
        if (clave == null) {
            return;
        }
        String confirmacionClave;
        boolean clavesCoinciden = false;

        do {
            confirmacionClave = JOptionPane.showInputDialog("Confirme su clave:");

            if (confirmacionClave == null) {
                return;
            }

            if (clave.equals(confirmacionClave)) {
                JOptionPane.showMessageDialog(null, "Clave establecida con exito.");
                clavesCoinciden = true;
            } else {
                String[] opciones = {"Confirmar de nuevo la clave", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(null,
                        "Las claves no coinciden.",
                        "Error de Confirmacion",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null, opciones, opciones[0]);

                if (opcion == 1) {
                    return;
                }
            }
        } while (!clavesCoinciden);

        Rol rolNuevo;

        String opcionesRoles[] = {"Administrador", "Tecnico"};

        int numeroRol = JOptionPane.showOptionDialog(null, "Seleccione el rol del usuario", "Rol de usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesRoles, opcionesRoles[1]);

        switch (numeroRol) {
            case 0:
                rolNuevo = Rol.Administrador;
                break;
            case 1:
                rolNuevo = Rol.Tecnico;
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Seleccion invalida. Se asignara: Tecnico");
                rolNuevo = Rol.Tecnico;
        }

        usuarios[numeroUsuarios] = new Usuario(nombreNuevo, clave, rolNuevo, usuario);

        JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente:\n"
                + "Nombre: " + usuarios[numeroUsuarios].getNombre() + "\n"
                + "Usuario: " + usuarios[numeroUsuarios].getUsuario() + "\n"
                + "Rol: " + usuarios[numeroUsuarios].getRol() + "\n"
                + "Código: " + usuarios[numeroUsuarios].getCodigo());

        numeroUsuarios++;

    }

    public static void crearOrden() {
        if (numeroOrdenes >= ordenes.length) {
            JOptionPane.showMessageDialog(null, "Límite de órdenes alcanzado (120 órdenes).");
            return;
        }
        String clienteID;
        Clientes clienteSeleccionado = null;

        while (true) {
            clienteID = JOptionPane.showInputDialog("Ingrese el ID del cliente:");

            for (int i = 0; i < numeroClientes; i++) {
                if (clientes[i].getID().equals(clienteID)) {
                    clienteSeleccionado = clientes[i];
                    break;
                }
            }
            if (clienteSeleccionado == null) {
                int opcion = JOptionPane.showOptionDialog(null,
                        "Cliente no encontrado.",
                        "Escoja una opción",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        new String[]{"Ingresar otro ID", "Cancelar"},
                        "Ingresar otro ID");

                switch (opcion) {
                    case 0:
                        continue;
                    case 1:
                    case -1:
                        return;
                }
            } else if (clienteSeleccionado.getNumeroOrdenes() >= 4) {
                String opciones[] = {"Ingresar otro ID de Cliente", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(null,
                        "El cliente ya ha ingresado 4 órdenes.",
                        "Limite de ordenes por cliente alcanzado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);

                switch (opcion) {
                    case 0:
                        clienteSeleccionado = null;
                        continue;
                    case 1:
                    case -1:
                        return;
                }
            } else {
                break;
            }
        }

        String usuarioID;
        Usuario usuarioAsignado = null;

        while (true) {
            usuarioID = JOptionPane.showInputDialog("Ingrese el codigo del tecnico asignado:");

            for (int i = 0; i < numeroUsuarios; i++) {
                if (usuarios[i].getCodigo().equals(usuarioID)) {
                    usuarioAsignado = usuarios[i];
                    break;
                }
            }

            if (usuarioAsignado == null) {
                String opciones[] = {"Ingresar Otro Codigo", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(null,
                        "El codigo no pertenece a ningún tecnico.",
                        "Error al asignar un tecnico",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);

                switch (opcion) {
                    case 0:
                        continue;
                    case 1:
                    case -1:
                        return;
                }
            } else {
                break;
            }
        }

        String dispositivos[] = {"Laptop", "PC", "Celular", "Tablet"};

        int opcion = JOptionPane.showOptionDialog(
                null,
                "Seleccione el tipo de dispositivo:",
                "Tipo de Dispositivo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                dispositivos,
                dispositivos[0]
        );

        TipoDispositivo dispositivoSeleccionado;

        switch (opcion) {
            case 0:
                dispositivoSeleccionado = TipoDispositivo.Laptop;
                break;
            case 1:
                dispositivoSeleccionado = TipoDispositivo.PC;
                break;
            case 2:
                dispositivoSeleccionado = TipoDispositivo.Celular;
                break;
            case 3:
                dispositivoSeleccionado = TipoDispositivo.Tablet;
                break;
            default:
                return;
        }
        String marca = JOptionPane.showInputDialog("Ingrese la marca del dispositivo:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del dispositivo:");
        String problema = JOptionPane.showInputDialog("Describa el problema del dispositivo:");

        ordenes[numeroOrdenes] = new Ordenes(clienteSeleccionado, usuarioAsignado, dispositivoSeleccionado, marca, modelo, problema);

        if (clienteSeleccionado != null) {
            boolean agregarOrden = clienteSeleccionado.agregarOrden(ordenes[numeroOrdenes]);
            if (agregarOrden) {
                System.out.println("Orden para cliente: " + clienteSeleccionado + " creada satisfactoriamente");
                System.out.println(clienteSeleccionado.getNumeroOrdenes());
            } else {
                System.out.println("El cliente ya tiene 4 órdenes asignadas.");
            }
        }

        JOptionPane.showMessageDialog(null, "Orden creada exitosamente:\n"
                + "Número de Orden: " + ordenes[numeroOrdenes].getNumeroOrden() + "\n"
                + "Cliente: " + ordenes[numeroOrdenes].getCliente().getNombre() + "\n"
                + "Técnico: " + ordenes[numeroOrdenes].getUsuario().getNombre() + "\n"
                + "Dispositivo: " + ordenes[numeroOrdenes].getDispositivo() + "\n"
                + "Marca: " + ordenes[numeroOrdenes].getMarca() + "\n"
                + "Modelo: " + ordenes[numeroOrdenes].getModelo() + "\n"
                + "Problema: " + ordenes[numeroOrdenes].getProblema() + "\n"
                + "Estado: " + ordenes[numeroOrdenes].getEstado() + "\n"
                + "Hora Ingresada: " + ordenes[numeroOrdenes].getHoraOrden());

        numeroOrdenes++;

    }

    public static boolean mostrarMenuAdministrador() {
        int opcion;
        do {
            String opcionesAdministrador[] = {
                "Mostrar clientes",
                "Mostrar usuarios",
                "Mostrar ordenes de servicio",
                "Agregar nuevo cliente",
                "Agregar nuevo usuario",
                "Crear orden de servicio",
                "Cerrar Sesion",
                "Salir del Sistema",};
            opcion = JOptionPane.showOptionDialog(null, "Menú de Administrador", "Menú",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesAdministrador, opcionesAdministrador[0]);
            switch (opcion) {

                case 0:
                    JOptionPane.showMessageDialog(null, "Mostrando Clientes....");
                    mostrarClientes();
                    break;

                case 1:
                    JOptionPane.showMessageDialog(null, "Mostrando usuarios....");
                    mostrarUsuarios();
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Mostrando ordenes....");
                    mostrarOrdenes();
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Agregando nuevo cliente....");
                    agregarCliente();
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Agregando nuevo usuario....");
                    agregarUsuario();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Creando nueva orden....");
                    crearOrden();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Cerrando sesion....");
                    return true;

                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema....");
                    return false;

            }

        } while (true);

    }

    private static String solicitarClave() {

        while (true) {

            String claveNueva = JOptionPane.showInputDialog("Ingrese la clave de acceso:");

            if (claveNueva == null) {
                return null;
            }

            boolean tieneNumero = false;
            boolean tieneLetra = false;

            for (int i = 0; i < claveNueva.length() && !(tieneNumero && tieneLetra); i++) {
                char c = claveNueva.charAt(i);

                if (c >= '0' && c <= '9') {
                    tieneNumero = true;
                }
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    tieneLetra = true;
                }

            }
            if (claveNueva.length() >= 8 && claveNueva.length() <= 16 && tieneNumero && tieneLetra) {
                return claveNueva;
            } else {

                String opciones[] = {"Agregar Otra Clave", "Cancelar"};

                int opcion = JOptionPane.showOptionDialog(null, "\"La clave no cumple con los requisitos mínimos:\\n\"\n"
                        + "            + \"- Entre 8 y 16 caracteres\\n\"\n"
                        + "            + \"- Al menos un número\\n\"\n"
                        + "            + \"- Al menos una letra\",\n"
                        + "            \"Error de clave\"", "Clave Invalida", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                switch (opcion) {
                    case 0:
                        continue;
                    case 1:
                        return null;
                    default:
                        return null;
                }

            }

        }

    }

    public static Usuario[] getUsuarios() {
        return usuarios;
    }

    public static Clientes[] getClientes() {
        return clientes;
    }

    public static Ordenes[] getOrdenes() {
        return ordenes;
    }

    public static int getNumeroUsuarios() {
        return numeroUsuarios;
    }

}
