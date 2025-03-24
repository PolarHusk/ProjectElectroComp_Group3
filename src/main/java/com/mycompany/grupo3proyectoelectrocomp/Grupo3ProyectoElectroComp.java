package com.mycompany.grupo3proyectoelectrocomp;

import javax.swing.JOptionPane;

public class Grupo3ProyectoElectroComp {

    public static void main(String[] args) {

        Gestion.datosIniciales();
        boolean continuarSistema = true;

        while (continuarSistema) {
            Usuario usuarioAutenticado = new Usuario().inicioSesion(Gestion.getUsuarios(), Gestion.getNumeroUsuarios());
            if (usuarioAutenticado != null && usuarioAutenticado.getRol() == Rol.Administrador) {
                continuarSistema = Gestion.mostrarMenuAdministrador();
            } else if (usuarioAutenticado != null && usuarioAutenticado.getRol() == Rol.Tecnico) {
                JOptionPane.showMessageDialog(null, "El rol del usuario no tiene un menu asignado aun. Se cerrara el sistema");
                continuarSistema = false;
            } else if (usuarioAutenticado == null) {
                continuarSistema = true;
            } else {
                JOptionPane.showMessageDialog(null, "Cerrar el sistema");
                continuarSistema = false;
            }

        }
    }
}


