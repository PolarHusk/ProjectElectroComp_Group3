package com.mycompany.grupo3proyectoelectrocomp;

import javax.swing.JOptionPane;

public class Grupo3ProyectoElectroComp {
    
// Universidad Fidelitas
// Group 3 FinalProject
// SC-202
// Github: https://github.com/PolarHusk/ProjectElectroComp_Group3

    public static void main(String[] args) {

        Gestion.datosIniciales();
        boolean continuarSistema = true;

        while (continuarSistema) {
            Usuario usuarioAutenticado = new Usuario().inicioSesion(Gestion.getUsuarios(), Gestion.getNumeroUsuarios());
            if (usuarioAutenticado != null && usuarioAutenticado.getRol() == Rol.Administrador) {
                continuarSistema = Gestion.mostrarMenuAdministrador();
            } else if (usuarioAutenticado != null && usuarioAutenticado.getRol() == Rol.Tecnico) {
                continuarSistema = Gestion.mostrarMenuTecnico(usuarioAutenticado);
            } else if (usuarioAutenticado == null) {
                continuarSistema = true;
            } else {
                JOptionPane.showMessageDialog(null, "Cerrar el sistema");
                continuarSistema = false;
            }

        }
    }
}


