package com.mycompany.grupo3proyectoelectrocomp;

import javax.swing.JOptionPane;

public class Usuario {

    // Atributos
    private static int contadorAdmin = 200;
    private static int contadorTecnico = 100;
    private String nombre;
    private String clave;
    private boolean activo;
    private Rol rol;
    private String usuario;
    private String codigo;

    // Metodo Constructor
    public Usuario(String nombre, String clave, Rol rol, String usuario) {
        this.nombre = nombre;
        this.clave = clave;
        this.activo = true;
        this.rol = rol;
        this.usuario = usuario;
        this.codigo = generarCodigo(rol);
    }

    public Usuario() {
    }

    // Comportamientos
    public Usuario inicioSesion(Usuario usuarios[], int numeroUsuarios) {

        String usuarioIngresado = JOptionPane.showInputDialog("Ingrese su usuario:");

        Usuario usuarioActual = null;

        for (int i = 0; i < numeroUsuarios; i++) {
            if (usuarios[i].getUsuario().equals(usuarioIngresado)) {
                usuarioActual = usuarios[i];
                break;
            }
        }

        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(null, "El usuario no existe, Intente Nuevamente");
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
            usuarioActual.activo = false;
            JOptionPane.showMessageDialog(null, "Se han agotado los intentos. Su usuario se encuentra desactivado. \n Contacte al administrador");

        } else {
            JOptionPane.showMessageDialog(null, "El usuario se encuentra inactivo. Contacte al administrador");
        }
        return null;
    }

    public String mostrarInfo() {
        String claveOculta = "";
        for (int i = 0; i < clave.length(); i++) {
            claveOculta += "*";
        }
        return "Codigo: # " + codigo + " ,"
                + "Nombre: " + nombre + " ,"
                + "Usuario: " + usuario + " ,"
                + "Rol: " + rol + " ,"
                + "Clave: " + claveOculta + " .";

    }

    private static String generarCodigo(Rol rol) {
        if (rol == Rol.Administrador) {
            return "A-" + (contadorAdmin++);
        } else {
            return "T-" + (contadorTecnico++);
        }

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
