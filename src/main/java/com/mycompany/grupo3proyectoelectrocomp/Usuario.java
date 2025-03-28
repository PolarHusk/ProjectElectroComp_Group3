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
    private String tecnico;
    private String codigo;

    // Metodo Constructor
    public Usuario(String nombre, String clave, Rol rol, String usuario) {
        this.nombre = nombre;
        this.clave = clave;
        this.activo = true;
        this.rol = rol;
        this.tecnico = usuario;
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
                + "Usuario: " + tecnico + " ,"
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
        return tecnico;
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

    public void setClave() {

        while (true) {
            String claveNueva = JOptionPane.showInputDialog("Ingrese la clave de acceso:");

            if (claveNueva == null) {
                return;
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
                String confirmacionClave;
                boolean clavesCoinciden = false;

                do {
                    confirmacionClave = JOptionPane.showInputDialog("Confirme su clave:");

                    if (confirmacionClave == null) {
                        return;
                    }

                    if (claveNueva.equals(confirmacionClave)) {
                        JOptionPane.showMessageDialog(null, "Clave establecida con exito.");
                        this.clave = claveNueva;
                        JOptionPane.showMessageDialog(null, "Usuario: " + tecnico + " ha actualizado la clave de forma correcta");
                        return;
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

            } else {

                String opciones[] = {"Agregar Otra Clave", "Cancelar"};

                int opcion = JOptionPane.showOptionDialog(null, """
                                                                "La clave no cumple con los requisitos minimos:\n"
                                                                            + Entre 8 y 16 caracteres
                                                                            + Al menos un n\u00fameron
                                                                            + Al menos una letra
                                                                            Error de clave""", "Clave Invalida", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (opcion == 1) {
                    return;
                }

            }

        }

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        JOptionPane.showMessageDialog(null, "Usuario: " + tecnico + " se ha actualizado el nombre de forma correcta");

    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setUsuario(String nuevoUsuario) {
        this.tecnico = nuevoUsuario;
        JOptionPane.showMessageDialog(null, "Usuario: " + tecnico + " se ha actualizado el usuario de forma correcta.");

    }

}
