package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UsuarioM;

public class UsuarioDAO {
    public boolean difEmail(String email){
        String QUERY = "SELECT id FROM usuario WHERE email = " + email;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            resultSet = preparedStatement.executeQuery(QUERY);
            int id = resultSet.getInt("id");
            if(id>0){
                return false;
            }
            return true;
        }catch(SQLException ex){
            return true;
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void cadastroUser(UsuarioM usuario){
        String QUERY = "INSERT INTO usuario (nome,email,senha,endereco,dataNascimento) "
                + "VALUES (? ,? ,? ,? ,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(difEmail(usuario.getEmail())==false){
            return;
        }
        try{
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.setString(1,usuario.getNome());
            preparedStatement.setString(2,usuario.getEmail());
            preparedStatement.setString(3,usuario.getSenha());
            preparedStatement.setString(4,usuario.getEndereco());
            preparedStatement.setString(5,usuario.getDataNascimento());
            
            preparedStatement.execute();
            System.out.println("Cadastrado com sucesso");
            
        }catch(SQLException ex){
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public UsuarioM getUsuario(int ID){
        String QUERY = "SELECT * FROM usuario WHERE id = " + ID;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                UsuarioM usuario = new UsuarioM();
                
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setEndereco(resultSet.getString("endereco"));
                usuario.setDataNascimento(resultSet.getString("dataNascimento"));
                
                return usuario;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
        
    }
    public void alteraSenha(String senha, int id){
        String QUERY = "UPDATE usuario "
                + " SET senha = ? "
                + " WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;        
            
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.setString(1,senha);
            preparedStatement.setInt(2,id);
            
            preparedStatement.execute();
            System.out.println("Alterado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void alterar(UsuarioM usuario, int ID){
        String QUERY = "UPDATE usuario "
                + " SET nome = ?, email = ?, endereco = ?, dataNascimento = ? "
                + " WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;        
            
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.setString(1,usuario.getNome());
            preparedStatement.setString(2,usuario.getEmail());
            preparedStatement.setString(3,usuario.getEndereco());
            preparedStatement.setString(4,usuario.getDataNascimento());
            preparedStatement.setInt(5,ID);
            
            preparedStatement.execute();
            System.out.println("Alterado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            
    }
    public void deletarDeVez(int ID){
        String QUERY = "DELETE FROM usuario WHERE id=" + ID;
        Connection connection = null;
        PreparedStatement preparedStatement = null;        
            
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.execute();
            System.out.println("Deletado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            
    }
    public int retornaId(String email){
        String QUERY = "SELECT id FROM usuario WHERE email= '" + email+"'";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                int id;
                id = resultSet.getInt("id");
                return id;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    public String retornaSenhaId(int id){
        String QUERY = "SELECT senha FROM usuario WHERE id= " + id;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                String senha;
                senha = resultSet.getString("senha");
                return senha;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
        
    }
    public String retornaSenha(String email){
        String QUERY = "SELECT senha FROM usuario WHERE email= '" + email + "'";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                String senha;
                senha = resultSet.getString("senha");
                return senha;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
        
    }
}
