package controller;

import conexao.ConectaBD;
import dao.AdminDAO;
import dao.UsuarioDAO;
import java.sql.*;
import model.AdminM;
import model.UsuarioM;
import static view.view.*;

public class AdminC {
    public static void iniciarADM() throws SQLException{
        Connection connection = ConectaBD.createConnectionMySQL();
        if(connection !=null){
            System.out.println("Conexão realizada com sucesso");
            connection.close();
        }
        String senha;
        int escolhido,idUser,idAdmP,idAdmD;
        AdminDAO adminDAO = new AdminDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AdminM admin = new AdminM();
        UsuarioM usuario = new UsuarioM();
        idAdmP = loginADM(adminDAO);
        if(idAdmP==0){
            return;
        }
        escolhido = escolhaADM();
        switch(escolhido){
            case(1):
                imprimeListADM(adminDAO);
               break;
            case(2):
                imprimeListUser(adminDAO);
                break;
            case(3):
                idUser = decideUser();
                usuario = infosAU(usuarioDAO,idUser);
                adminDAO.alterarUser(usuario,idUser);
                break;
            case(4):
                idAdmD = decideAdm();
                admin = infosAD(adminDAO,idAdmD);
                adminDAO.alterarADM(admin,idAdmD);
                break;
            case(5):
                senha = adminDAO.retornaSenhaId(idAdmP);
                senha = trocaSenha(senha);
                if(senha != " "){
                    adminDAO.alteraSenha(senha,idAdmP);
                }else{
                    System.out.println("Erro na alteracao da senha");
                }
                break;
            case(6):
                usuario = infosCU();
                adminDAO.cadastroUser(usuario);
                break;
            case(7):
                admin = infosCA();
                adminDAO.cadastroADM(admin);
                break;
            case(8):
                idAdmD = decideAdm();
                adminDAO.deletarADM(idAdmD);
                break;
            case(9):
                idUser = decideUser();
                adminDAO.deletarUser(idUser);
                break;
               
        }
    }
}
