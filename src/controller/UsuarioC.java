package controller;

import conexao.ConectaBD;
import dao.UsuarioDAO;
import java.sql.*;
import model.UsuarioM;
import static view.view.*;

public class UsuarioC {
    public static void iniciarUser() throws SQLException{
        Connection connection = ConectaBD.createConnectionMySQL();
        if(connection !=null){
            System.out.println("Conexão realizada com sucesso");
            connection.close();
        }
        String senha;
        int escolhido;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioM usuario = new UsuarioM();
        escolhido = inicio();
        switch(escolhido){
            case(1):
                int idUser = loginUser(usuarioDAO);
                if(idUser==0){
                    return;
                }
                escolhido = escolhaUser();
                switch(escolhido){
                    case(1):
                       imprimeUserSolo(usuarioDAO,idUser);
                       break;
                    case(2):
                        usuario = infosAU(usuarioDAO,idUser);
                        usuarioDAO.alterar(usuario, idUser);
                        break;
                    case(3):
                        senha = usuarioDAO.retornaSenhaId(idUser);
                        senha = trocaSenha(senha);
                        if(senha != " "){
                            usuarioDAO.alteraSenha(senha,idUser);
                        }else{
                            System.out.println("Erro na alteracao da senha");
                        }
                        break;
                    case(4):
                        usuarioDAO.deletarDeVez(idUser);
                        break;
                    }
                break;
            case(2):
                usuario = infosCU();
                usuarioDAO.cadastroUser(usuario);
                break;
        }
        
    }
}
