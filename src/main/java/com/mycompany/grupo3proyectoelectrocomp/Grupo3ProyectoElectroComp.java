

package com.mycompany.grupo3proyectoelectrocomp;

import javax.swing.JOptionPane;

public class Grupo3ProyectoElectroComp {

    public static void main(String[] args) {
        Usuario usuario1 = new Usuario("Dylan","admin123", true, "Administrador", "admin", "A-101");
        Usuario usuario2 = new Usuario("Samuel", "sam123", true, "Tecnico", "samp", "T-101");
        Usuario usuario3 = new Usuario("Juan", "juan123", true, "Tecnico", "juanp", "T-102");
        Usuario usuario4 = new Usuario("Angeles", "ang123", false, "Tecnico", "angep", "T-103");
        
        Clientes cliente1 = new Clientes("123456", "Alexander", "8888-8888", "dcalderon@ufide.ac.cr", TipoCliente.Premium);
        Clientes cliente2 = new Clientes("1234567", "Helena", "8888-8889", "hfide@ufide.ac.cr", TipoCliente.Oro);
        
        Ordenes orden1 = new Ordenes(cliente1,usuario2, TipoDispositivo.Laptop, "HP", "Victus 16", "Fallo en Windows");
        Ordenes orden2 = new Ordenes(cliente2,usuario3, TipoDispositivo.Tablet, "Samsung", "S23", "Fallo de Pantalla");
        
 
        
    boolean continuarSistema = true;

    while (continuarSistema) {
        Usuario usuarioAutenticado = new Usuario().inicioSesion();
        
        if (usuarioAutenticado != null) {
            continuarSistema = usuarioAutenticado.mostrarMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Sistema cerrado");
            continuarSistema = false; 
        }
        

        }
    }
    
}

