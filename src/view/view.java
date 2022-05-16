package view;

import dao.AdminDAO;
import dao.UsuarioDAO;
import model.UsuarioM;
import java.util.Scanner;
import model.AdminM;

public class view {
    public static int inicio(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Já é cadastrado? Digite 1. Caso contrario, Digite 2");
        int a= teclado.nextInt();
        return a;
    }
    public static int loginUser(UsuarioDAO usuarioDAO){
        Scanner teclado = new Scanner(System.in);
        int id = 0;
        System.out.println("Digite seu email para login");
        String email = teclado.nextLine();
        String senha = usuarioDAO.retornaSenha(email);
        System.out.println("Informe sua senha");
        if(senha.equals(teclado.nextLine())){
            System.out.println("Logado com sucesso");
            id = usuarioDAO.retornaId(email);
            if(id==0){
                System.out.println("Houve um erro");
            }
            return id;
        }else{
            System.out.println("Senha errada");
        }
        return 0;
    }
    public static int loginADM(AdminDAO adminDAO){
        Scanner teclado = new Scanner(System.in);
        int id = 0;
        System.out.println("Digite seu email para login");
        String email = teclado.nextLine();
        String senha = adminDAO.retornaSenha(email);
        System.out.println("Informe sua senha");
        if(senha.equals(teclado.nextLine())){
            System.out.println("Logado com sucesso");
            id = adminDAO.retornaId(email);
            if(id==0){
                System.out.println("Houve um erro");
            }
            return id;
        }else{
            System.out.println("Senha errada");
        }
        return 0;
    }
    public static int escolhaUser(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Informe oque deseja fazer: 1- exibir informacoes sobre seu usuario, "
                + "2-alterar informacoes sobre seu usuario, 3-trocar a senha, 4-deletar seu usuario");
        int a = teclado.nextInt();
        return a;
    }
    public static int escolhaADM(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Informe oque deseja fazer: 1- exibir informacoes sobre todos administradores, 2- exibir informacoes sobre todos usuarios,"
                + "3-alterar informacoes sobre um usuario, 4-alterar informacoes sobre um administrador, 5-trocar a senha, 6-cadastrar um usuario novo,"
                + "7-cadastrar um administrador novo, 8-deletar um usuario, 9-deletar um administrador");
        int a = teclado.nextInt();
        return a;
    }
    public static void imprimeListADM(AdminDAO adminDAO){
        for (model.AdminM A : adminDAO.getADM()) {
            System.out.println("Nome: " + A.getNome() + "email: " + A.getEmail() + "salario: " + A.getSalario() + "dataNascimento: " + A.getDataNascimento());
        }
    }
    public static void imprimeListUser(AdminDAO adminDAO){
        for (UsuarioM A : adminDAO.getUser()) {
            System.out.println("Nome: " + A.getNome() + "email: " + A.getEmail() + "endereco: " + A.getEndereco() + "dataNascimento: " + A.getDataNascimento());
        }
    }
    public static void imprimeUserSolo(UsuarioDAO usuarioDAO, int a){
        UsuarioM usuario = usuarioDAO.getUsuario(a);
        System.out.println("nome: " + usuario.getNome() + " email: " 
                + usuario.getEmail() + " endereco: " + usuario.getEndereco() 
                + " data de nascimento: " + usuario.getDataNascimento());

    }
    public static int decideUser(){
        Scanner teclado = new Scanner(System.in);
        int a = 0;
        System.out.println("Informe o id do usuario que voce deseja alterar");
         a= teclado.nextInt();
        return a;
    }
    public static int decideAdm(){
        Scanner teclado = new Scanner(System.in);
        int a = 0;
        System.out.println("Informe o id do administrador que voce deseja alterar");
         a= teclado.nextInt();
        return a;
    }
    public static UsuarioM infosCU(){
        Scanner teclado = new Scanner(System.in);
        String nome, email, senha, endereco, dataN;
        System.out.println("Informe suas informaçoes. Qual o nome?");
        nome = teclado.nextLine();
        System.out.println("Qual o email?");
        email = teclado.nextLine();
        System.out.println("Qual a senha?");
        senha = teclado.nextLine();
        System.out.println("Qual o endereco?");
        endereco = teclado.nextLine();
        System.out.println("Qual a data de nascimento?(Informe no modelo **/**/****)");
        dataN = teclado.nextLine();
        UsuarioM usuario = new UsuarioM(nome,email,senha,endereco,dataN);     
        return usuario;
    }
    public static AdminM infosCA(){
        Scanner teclado = new Scanner(System.in);
        String nome, email, senha, dataN;
        int salario;
        System.out.println("Informe suas informaçoes. Qual o nome?");
        nome = teclado.nextLine();
        System.out.println("Qual o email?");
        email = teclado.nextLine();
        System.out.println("Qual a senha?");
        senha = teclado.nextLine();
        System.out.println("Qual o salario?");
        salario = teclado.nextInt();
        System.out.println("Qual a data de nascimento?(Informe no modelo **/**/****)");
        dataN = teclado.nextLine();
        AdminM admin = new AdminM(nome,email,senha,salario,dataN);     
        return admin;
    }
    public static UsuarioM infosAU(UsuarioDAO usuarioDAO, int id){
        Scanner teclado = new Scanner(System.in);
        String nome, email, senha, endereco, dataN;
        System.out.println("Informe as informaçoes. Qual o nome?");
        nome = teclado.nextLine();
        System.out.println("Qual o email?");
        email = teclado.nextLine();
        System.out.println("Qual o endereco?");
        endereco = teclado.nextLine();
        senha = usuarioDAO.retornaSenhaId(id);
        System.out.println("Qual a data de nascimento?(Informe no modelo **/**/****)");
        dataN = teclado.nextLine();
        UsuarioM usuario = new UsuarioM(nome,email, senha, endereco,dataN);    
        return usuario;
    }
    public static AdminM infosAD(AdminDAO adminDAO, int id){
        Scanner teclado = new Scanner(System.in);
        String nome, email, senha, dataN;
        int salario;
        System.out.println("Informe as informaçoes. Qual o nome?");
        nome = teclado.nextLine();
        System.out.println("Qual o email?");
        email = teclado.nextLine();
        System.out.println("Qual o salario?");
        salario = teclado.nextInt();
        senha = adminDAO.retornaSenhaId(id);
        System.out.println("Qual a data de nascimento?(Informe no modelo **/**/****)");
        dataN = teclado.nextLine();
        AdminM admin = new AdminM(nome,email, senha, salario,dataN);    
        return admin;
    }
    public static String trocaSenha(String senhaA){
        Scanner teclado = new Scanner(System.in);
        String senhaN = " ", senhaT;
        System.out.println("Informe sua senha antiga");
        senhaT = teclado.nextLine();
        if(senhaA.equals(senhaT)){
            System.out.println("Informe sua senha nova");
            senhaN = teclado.nextLine();
        }else{
            System.out.println("A senha está incorreta");
        }
        return senhaN;
    }
}
