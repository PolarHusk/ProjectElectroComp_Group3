package com.mycompany.grupo3proyectoelectrocomp;

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

        for (int i = 0; i < numeroOrdenes; i++) {
            for (int j = 0; j < numeroClientes; j++) {
                if (clientes[j].getID().equals(ordenes[i].getCliente().getID())) {
                    clientes[i].agregarOrden(ordenes[i]);
                    break;
                }
            }
        }

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
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE USUARIOS ---");
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

        if (nombreNuevo == null) {
            return;
        }

        String telefono;
        while (true) {
            String telefonoNuevo = JOptionPane.showInputDialog("Ingrese el Numero de Telefono: (FORMATO 0000-0000)");
            if (telefonoNuevo == null) {
                return;
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
            if (correoNuevo == null) {
                return;
            }
            if (correoNuevo.contains("@")) {
                int posicionArroba = correoNuevo.indexOf("@");
                if (posicionArroba != -1 && correoNuevo.indexOf(".", posicionArroba) > posicionArroba) {
                    correo = correoNuevo;
                    break;
                }
            } else {
                String opcionesCorreo[] = {"Agregar otro correo", "Cancelar"};

                int opt = JOptionPane.showOptionDialog(null, "Correo Invalido", "Seleccione una opcion", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcionesCorreo, opcionesCorreo[0]);

                switch (opt) {
                    case 0:
                        continue;
                    case 1:
                        return;
                }
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
                String opcionesAgregarUsuario[] = {"Agregar Otro Usuario", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(null, "Selecciona una opcion", "Usuario ya registrado", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcionesAgregarUsuario, opcionesAgregarUsuario[0]);
                switch (opcion) {
                    case 0:
                        agregarUsuario();
                        return;
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

        if (nombreNuevo == null) {
            return;
        }

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
                if (usuarios[i].getCodigo().equals(usuarioID) && usuarios[i].getRol() == Rol.Tecnico) {
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
                System.out.println("Orden para cliente: " + clienteSeleccionado.getNombre() + " creada satisfactoriamente");
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

    public static void buscarCliente() {

        String clienteBusqueda;

        Clientes clienteEncontrado = null;

        while (true) {

            clienteBusqueda = JOptionPane.showInputDialog(
                    "Ingrese el ID del cliente a buscar:");

            for (int i = 0; i < numeroClientes; i++) {
                if (clienteBusqueda.equals(clientes[i].getID())) {
                    clienteEncontrado = clientes[i];
                    JOptionPane.showMessageDialog(null,
                            "Cliente encontrado:\n" + clientes[i].mostrarInfo());

                    break;
                }
            }

            if (clienteEncontrado == null) {
                String opcionesBusquedaCliente[] = {"Ingresar otro ID", "Cancelar"};

                int opcion = JOptionPane.showOptionDialog(
                        null,
                        "El cliente con el ID: " + clienteBusqueda + " no se encuentra registrado en el sistema",
                        "Cliente no encontrado",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        opcionesBusquedaCliente,
                        opcionesBusquedaCliente[0]);
                switch (opcion) {
                    case 0:
                        continue;
                    case 1:
                    case -1:
                        return;
                }

            } else {
                Ordenes ordenesCliente[] = clienteEncontrado.getOrdenesCliente();

                int totalOrdenesCliente = clienteEncontrado.getNumeroOrdenes();

                if (totalOrdenesCliente == 0) {
                    String opciones[] = {"Cancelar"};
                    int seleccion = JOptionPane.showOptionDialog(null, "Volver al menu?", "Confirmacion", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
                    if (seleccion == 0) {
                        break;
                    }
                } else {

                    String opciones[] = new String[clienteEncontrado.getNumeroOrdenes() + 1];

                    for (int i = 0; i < opciones.length - 1; i++) {
                        opciones[i] = clienteEncontrado.getOrdenesCliente()[i].getNumeroOrden() + "";
                    }

                    opciones[opciones.length - 1] = "Cancelar";

                    int ordenSeleccion = JOptionPane.showOptionDialog(null, "Seleccione una orden del cliente para ver detalles", "Ordenes de:" + clienteEncontrado.getNombre(), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[opciones.length - 1]);

                    if (ordenSeleccion == opciones.length - 1) {
                        break;
                    }

                    Ordenes ordenSeleccionada = ordenesCliente[ordenSeleccion];

                    JOptionPane.showMessageDialog(null, "Detalles de la orden:\n" + ordenSeleccionada.mostrarInfo());

                }

            }
        }
    }

    public static void buscarOrdenServicio() {

        while (true) {
            int numeroOrdenBusqueda = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de orden a buscar:"));

            for (int i = 0; i < numeroOrdenes; i++) {
                if (numeroOrdenBusqueda == ordenes[i].getNumeroOrden()) {
                    JOptionPane.showMessageDialog(null,
                            "Orden encontrada: \n" + ordenes[i].mostrarInfo());
                    return;
                }
            }

            JOptionPane.showMessageDialog(null, "La orden de servicio con el numero: " + numeroOrdenBusqueda
                    + " no se encuentra registrado en el sistema.");

            String opcionesBuscarOrden[] = {"Ingresar otro numero", "Cancelar"};
            int opcion = JOptionPane.showOptionDialog(null, "Orden de Servicio No encontrada", "Escoja una opcion", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcionesBuscarOrden, opcionesBuscarOrden[0]);

            switch (opcion) {
                case 0:
                    continue;
                case 1:
                    return;
                case -1:
                    break;
            }

        }
    }

    public static void buscarUsuario() {
        String opcionesBusqueda[] = {"Buscar por Usuario", "Buscar por Codigo"};

        int numeroBusqueda = JOptionPane.showOptionDialog(
                null,
                "Seleccione el metodo de busqueda:",
                "Buscar por: ",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesBusqueda,
                opcionesBusqueda[0]);

        switch (numeroBusqueda) {
            case 0:
                String nombreBusquedaUsuario = JOptionPane.showInputDialog(
                        "Ingrese el nombre de usuario a buscar:");

                Usuario usuarioEncontrado = null;
                for (int i = 0; i < numeroUsuarios; i++) {
                    if (nombreBusquedaUsuario.equals(usuarios[i].getUsuario())) {
                        JOptionPane.showMessageDialog(null,
                                "Usuario encontrado:\n" + usuarios[i].mostrarInfo());
                        usuarioEncontrado = usuarios[i];
                        break;
                    }
                }

                if (usuarioEncontrado != null) {
                    actualizacionUsuario(usuarioEncontrado);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el usuario registrado");
                    buscarUsuario();
                }
                break;

            case 1:
                String codigoBusquedaUsuario = JOptionPane.showInputDialog("Ingrese el codigo del usuario: ");
                usuarioEncontrado = null;
                for (int i = 0; i < numeroUsuarios; i++) {
                    if (codigoBusquedaUsuario.equals(usuarios[i].getCodigo())) {
                        JOptionPane.showMessageDialog(null,
                                "Usuario encontrado:\n" + usuarios[i].mostrarInfo());
                        usuarioEncontrado = usuarios[i];
                        break;
                    }
                }
                if (usuarioEncontrado != null) {
                    actualizacionUsuario(usuarioEncontrado);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el usuario registrado con el codigo: " + codigoBusquedaUsuario);
                    buscarUsuario();
                }
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    public static void generarReportes() {
        JOptionPane.showMessageDialog(null, "Generacion de reportes se encuentra en construccion");
    }

    public static boolean mostrarMenuAdministrador() {
        int opcion;
        do {

            opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                  BIENVENIDO A LA TIENDA DE ELECTRONICOS ElectroComp
                                                                                 1- Mostrar clientes
                                                                                 2- Mostrar usuarios
                                                                                 3- Mostrar ordenes de servicio
                                                                                 4- Agregar nuevo cliente
                                                                                 5- Agregar nuevo usuario
                                                                                 6- Crear orden de servicio
                                                                                 7- Buscar un Cliente
                                                                                 8- Buscar un Usuario
                                                                                 9- Buscar una Orden de Servicio
                                                                                 10- Generar reportes
                                                                                 11- Cerrar Sesion
                                                                                 12- Salir del Sistema                          
                                                                  """));

            switch (opcion) {

                case 1:
                    JOptionPane.showMessageDialog(null, "Mostrando Clientes....");
                    mostrarClientes();
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Mostrando usuarios....");
                    mostrarUsuarios();
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Mostrando ordenes....");
                    mostrarOrdenes();
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Agregando nuevo cliente....");
                    agregarCliente();
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, "Agregando nuevo usuario....");
                    agregarUsuario();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Creando nueva orden....");
                    crearOrden();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Buscar un Cliente....");
                    buscarCliente();
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Buscar un Usuario....");
                    buscarUsuario();
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Buscar una Orden de Servicio....");
                    buscarOrdenServicio();
                    break;
                case 10:
                    JOptionPane.showMessageDialog(null, "Generar Reportes....");
                    generarReportes();
                    break;
                case 11:
                    JOptionPane.showMessageDialog(null, "Cerrando sesion....");
                    return true;

                case 12:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema....");
                    return false;
                default:
                    return false;
            }

        } while (true);

    }

    public static boolean mostrarMenuTecnico() {
        
        JOptionPane.showMessageDialog(null, "Menu del tecnico en Construccion. Volviendo al inicio de sesion");
        return true;
        
//        int opcion;
//        do {
//
//            opcion = Integer.parseInt(JOptionPane.showInputDialog("""
//                                                                  BIENVENIDO A LA TIENDA DE ELECTRONICOS ElectroComp
//                                                                                 1- Mostrar ordenes de servicio
//                                                                                 2- Buscar Orden de Servicio
//                                                                                 3- Cerrar sesion
//                                                                                 4- Salir del Sistema                         
//                                                                  """));
//
//            switch (opcion) {
//
//                case 0:
//                    JOptionPane.showMessageDialog(null, "Mostrando Ordenes.... ");
//                    break;
//
//                case 1:
//                    JOptionPane.showMessageDialog(null, "Buscar orden de servicio....");
//                    mostrarUsuarios();
//                    break;
//
//                case 2:
//                    JOptionPane.showMessageDialog(null, "Cerrar Sesion....");
//                    mostrarOrdenes();
//                    break;
//                case 3:
//                    JOptionPane.showMessageDialog(null, "Salir del sistema...");
//
//            }
//        } while (true);
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

    private static void actualizacionUsuario(Usuario usuarioEncontrado) {

        while (true) {

            String opciones[] = {"Actualizar", "Activar/Desactivar", "Cancelar"};
            int opt = JOptionPane.showOptionDialog(null,
                    "Selecciones una accion para el usuario", "Opciones de Usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[2]);

            switch (opt) {
                case 0:
                    String opcionesActualizacion[] = {"Nombre Completo", "Usuario", "Clave"};

                    int seleccion = JOptionPane.showOptionDialog(null, "Seleeccione el dato a actualizar", "Actualizacion de datos del usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesActualizacion, opcionesActualizacion[0]);

                    switch (seleccion) {
                        case 0:
                            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nombre completo del usuario:");
                            usuarioEncontrado.setNombre(nuevoNombre);
                            break;
                        case 1:
                            boolean usuarioValido = false;
                            String nuevoUsuario;
                            while (!usuarioValido) {
                                nuevoUsuario = JOptionPane.showInputDialog("Ingrese el nuevo nombre de usuario: ");
                                boolean usuarioEnArreglo = false;
                                for (int i = 0; i < numeroUsuarios; i++) {
                                    if (usuarios[i].getUsuario().equals(nuevoUsuario)) {
                                        usuarioEnArreglo = true;
                                        break;
                                    }
                                }
                                if (usuarioEnArreglo) {
                                    JOptionPane.showMessageDialog(null, "Usuario ya agregado al sistema");
                                    String opcionesAgregarUsuario[] = {"Agregar Otro Usuario", "Cancelar"};
                                    int opcion = JOptionPane.showOptionDialog(null, "Selecciona una opcion", "Usuario ya registrado", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcionesAgregarUsuario, opcionesAgregarUsuario[0]);
                                    switch (opcion) {
                                        case 0:
                                            continue;
                                        case 1:
                                            JOptionPane.showMessageDialog(null, "Cancelando");
                                            return;
                                        default:
                                            JOptionPane.showMessageDialog(null, "ERROR");
                                    }
                                } else {
                                    usuarioEncontrado.setUsuario(nuevoUsuario);
                                    usuarioValido = true;
                                }
                            }
                            break;
                        case 2:
                            usuarioEncontrado.setClave();
                            break;
                        default:
                            return;
                    }
                    break;
                case 1:
                    if (usuarioEncontrado.getActivo()) {
                        String desactivarBoton[] = {"Desactivar"};

                        int botonDesactivar = JOptionPane.showOptionDialog(null,
                                "Desea desactivar el usuario?", "Desactivar usuario", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE, null, desactivarBoton, null);

                        if (botonDesactivar == 0) {
                            usuarioEncontrado.setActivo(false);
                            JOptionPane.showMessageDialog(null, "Usuario: " + usuarioEncontrado.getUsuario() + " se ha desactivado de forma correcta.");
                        }
                    } else {
                        String activarBoton[] = {"Activar"};
                        int botonActivar = JOptionPane.showOptionDialog(null, "Desea activar el usuario?", "Activar usuario", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, activarBoton, null);
                        if (botonActivar == 0) {
                            usuarioEncontrado.setActivo(true);
                            JOptionPane.showMessageDialog(null, "Usuario: " + usuarioEncontrado.getUsuario() + " se ha activado de forma correcta.");
                        }
                    }
                    break;

                case 2:
                    return;

                default:
                    return;
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
