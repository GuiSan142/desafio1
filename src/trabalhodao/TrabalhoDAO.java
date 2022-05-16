/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhodao;

import static controller.AdminC.iniciarADM;
import static controller.UsuarioC.*;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author 7728735
 */
public class TrabalhoDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        int a;
        System.out.println("Informe se voce e admin(Digite 1) ou usuario(Digite 2)");
        a = teclado.nextInt();
        switch(a){
            case(1):
                iniciarADM();
                break;
            case(2):
                iniciarUser();
                break;
        }

    }
    
}
