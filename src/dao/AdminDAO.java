package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AdminM;
import model.UsuarioM;

public class AdminDAO {
    public boolean difEmailU(String email){
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
    public boolean difEmailA(String email){
        String QUERY = "SELECT id FROM admin WHERE email = " + email;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            resultSet = preparedStatement.executeQuery(QUERY);
            int id = resultSet.getInt("id");
            if(id>0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            return false;
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
    public void cadastroADM(AdminM admin){
        String QUERY = "INSERT INTO admin (nome,email,senha,salario,dataNascimento) "
                + "VALUES (? ,? ,? ,? ,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(difEmailA(admin.getEmail())==false){
            return;
        }
        try{
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.setString(1,admin.getNome());
            preparedStatement.setString(2,admin.getEmail());
            preparedStatement.setString(3,admin.getSenha());
            preparedStatement.setInt(4,admin.getSalario());
            preparedStatement.setString(5,admin.getDataNascimento());
            
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
    public void cadastroUser(UsuarioM usuario){
        String QUERY = "INSERT INTO usuario (nome,email,senha,endereco,dataNascimento) "
                + "VALUES (? ,? ,? ,? ,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(difEmailU(usuario.getNome())==false){
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
    public List<AdminM> getADM(){
        ArrayList<AdminM> ADMs = new ArrayList<>();
        String QUERY = "SELECT * FROM admin";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                
                AdminM adm = new AdminM();
                
                adm.setId(resultSet.getInt("id"));
                adm.setNome(resultSet.getString("nome"));
                adm.setEmail(resultSet.getString("email"));
                adm.setSenha(resultSet.getString("senha"));
                adm.setSalario(resultSet.getInt("salario"));
                adm.setDataNascimento(resultSet.getString("dataNascimento"));
                
                ADMs.add(adm);
            }
            return ADMs;
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
    public List<UsuarioM> getUser(){
        ArrayList<UsuarioM> Users = new ArrayList<>();
        String QUERY = "SELECT * FROM usuario";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                
                UsuarioM user = new UsuarioM();
                
                user.setId(resultSet.getInt("id"));
                user.setNome(resultSet.getString("nome"));
                user.setEmail(resultSet.getString("email"));
                user.setSenha(resultSet.getString("senha"));
                user.setEndereco(resultSet.getString("endereco"));
                user.setDataNascimento(resultSet.getString("dataNascimento"));
                
                Users.add(user);
            }
            return Users;
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
        String QUERY = "SELECT senha FROM admin WHERE email= '" + email + "'";
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
    public int retornaId(String email){
        String QUERY = "SELECT id FROM admin WHERE email= '" + email+"'";
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
    public void alteraSenha(String senha, int id){
        String QUERY = "UPDATE admin "
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
    public void alterarUser(UsuarioM usuario, int ID){
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
    public void alterarADM(AdminM admin, int ID){
        String QUERY = "UPDATE admin "
                + " SET nome = ?, email = ?, salario = ?, dataNascimento = ? "
                + " WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;        
            
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.setString(1,admin.getNome());
            preparedStatement.setString(2,admin.getEmail());
            preparedStatement.setInt(3,admin.getSalario());
            preparedStatement.setString(4,admin.getDataNascimento());
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
    public void deletarADM(int ID){
        String QUERY = "DELETE FROM admin WHERE id=" + ID;
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
    public void deletarUser(int ID){
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
    public String retornaSenhaId(int id){
        String QUERY = "SELECT senha FROM admin WHERE id= " + id;
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
