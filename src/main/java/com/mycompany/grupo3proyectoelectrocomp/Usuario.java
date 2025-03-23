package com.mycompany.grupo3proyectoelectrocomp;

import javax.swing.JOptionPane;

public class Usuario {

    // Atributos
    private String nombre;
    private String clave;
    private boolean activo;
    private Rol rol;
    private String usuario;
    private String codigo;
    static Usuario[] usuarios = new Usuario[20];
    static int numeroUsuarios = 0;

    // Metodo Constructor
    public Usuario(String nombre, String clave, boolean activo, Rol rol, String usuario, String codigo) {
        this.nombre = nombre;
        this.clave = clave;
        this.activo = activo;
        this.rol = rol;
        this.usuario = usuario;
        this.codigo = codigo;

        if (numeroUsuarios < usuarios.length) {
            usuarios[numeroUsuarios] = this;
            numeroUsuarios++;
        } else {
            System.out.println("Existe un limite de 20 usuarios");
        }
    }

    public Usuario() {
    }

    // Comportamientos
    public Usuario inicioSesion() {

        String usuarioIngresado = JOptionPane.showInputDialog("Ingrese su usuario:");

        Usuario usuarioActual = null;

        for (int i = 0; i < numeroUsuarios; i++) {
            if (usuarios[i].getUsuario().equals(usuarioIngresado)) {
                usuarioActual = usuarios[i];
                break;
            }
        }

        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(null, "El usuario no existe, Contacte al administrador");
            return null;
        }

        if (usuarioActual.getActivo()) {

            int intentos = 0;

            while (intentos < 3) {
                String claveUsuario = JOptionPane.showInputDialog("Ingrese su clave de ingreso: ");
                if (claveUsuario.equals(usuarioActual.getClave())) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesion exitoso con el usuario: " + usuarioActual.getNombre() + "\nRol: " + usuarioActual.getRol());
                    return usuarioActual;
                } else {
                    intentos++;
                    JOptionPane.showMessageDialog(null, "Clave incorrecta, Intentos: " + intentos + "de 3");
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "El usuario se encuentra inactivo. Contacte al administrador");
            return null;
        }
        usuarioActual.activo = false;
        JOptionPane.showMessageDialog(null, "Se han agotado los intentos. Su usuario se encuentra desactivado. \n Contacte al administrador");
        return null;
    }

    public boolean mostrarMenu() {

        if (getRol() == Rol.Administrador) {
            int opcion;
            do {
                String[] opcionesAdministrador = {
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
                        Clientes.mostrarClientes();
                        break;

                    case 1:
                        JOptionPane.showMessageDialog(null, "Mostrando usuarios....");
                        mostrarUsuarios();
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null, "Mostrando ordenes....");
                        Ordenes.mostrarOrdenes();
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Agregando nuevo cliente....");
                        Clientes cliente = new Clientes();
                        cliente.setID();
                        break;

                    case 4:
                        JOptionPane.showMessageDialog(null, "Agregando nuevo usuario....");
                        Usuario usuario = new Usuario();
                        usuario.agregarUsuario();
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Creando nueva orden....");
                        Ordenes orden = new Ordenes();
                        orden.crearOrden();
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Cerrando sesion....");
                        return true;

                    case 7:
                        JOptionPane.showMessageDialog(null, "Saliendo del sistema....");
                        return false;

                }

            } while (true);

        } else if (getRol() == Rol.Tecnico) {

            JOptionPane.showMessageDialog(null, "El menu del tecnico se encuentra en construcccion.");
            return false;
        } else {

            JOptionPane.showMessageDialog(null, "El rol del usuario no tiene un menu asignado. Se cerrara el sistema");
            return false;
        }

    }

    public void mostrarUsuarios() {
        for (int i = 0; i < numeroUsuarios; i++) {
            Usuario u = usuarios[i];
            System.out.println("Nombre: " + u.nombre + ", Usuario: " + u.usuario + ", Rol: " + u.rol);
        }
    }

    public void agregarUsuario() {
        if (numeroUsuarios >= usuarios.length) {
            JOptionPane.showMessageDialog(null, "Límite de usuarios alcanzado (20 usuarios).");
            return;
        }

        String nombreNuevo = JOptionPane.showInputDialog("Ingrese su nombre completo: ");
        String usuarioNuevo = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String claveNueva = JOptionPane.showInputDialog("Ingrese la clave de acceso:");
        boolean activoNuevo = JOptionPane.showConfirmDialog(null, "¿El usuario está activo?", "Estado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

//        String[] roles = {"Administrador", "Tecnico"};
//        String rolNuevo = (String) JOptionPane.showInputDialog(null, "Seleccione el rol del usuario:", "Rol de Usuario", JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);
        String opcionesRoles[] = {"Administrador","Tecnico"};

        int numeroRol = JOptionPane.showOptionDialog(null, "Seleccione el rol del usuario", "Rol de usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesRoles, opcionesRoles[1]);

        switch (numeroRol) {
            case 0:
                rol = Rol.Administrador;
                break;
            case 1:
                rol = Rol.Tecnico;
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Seleccion invalida. Se asignara: Asistente");
                rol = Rol.Tecnico;
        }

        String codigoNuevo = JOptionPane.showInputDialog("Ingrese el código de usuario (Ej. A-101 o T-101):");

        Usuario nuevoUsuario = new Usuario(nombreNuevo, claveNueva, activoNuevo, rol, usuarioNuevo, codigoNuevo);

        JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente:\n"
                + "Nombre: " + nuevoUsuario.getNombre() + "\n"
                + "Usuario: " + nuevoUsuario.getUsuario() + "\n"
                + "Rol: " + nuevoUsuario.getRol() + "\n"
                + "Código: " + nuevoUsuario.getCodigo());
    }

// Getter y setter
    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public boolean getActivo() {
        return activo;
    }

    public Rol getRol() {
        return rol;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static void setNumeroUsuarios(int numeroUsuarios) {
        Usuario.numeroUsuarios = numeroUsuarios;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
