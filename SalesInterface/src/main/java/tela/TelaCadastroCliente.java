/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tela;

import componente.MeuCampoTexto;
import dao.ClientesDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Cliente;

/**
 *
 * @author Administrator
 */
public class TelaCadastroCliente extends TelaCadastro{
    
    public Cliente cliente = new Cliente();
    public ClientesDao clienteDao = new ClientesDao(cliente);
    
    public MeuCampoTexto jtfCodigo = new MeuCampoTexto(10, "Código", true);
    public MeuCampoTexto jtfNome = new MeuCampoTexto(30, "Nome", true);
    public MeuCampoTexto jtfCpf = new MeuCampoTexto(15, "CPF", true);
    public MeuCampoTexto jtfRg = new MeuCampoTexto(15, "RG", true);
    public MeuCampoTexto jtfLogradouro = new MeuCampoTexto(30, "Logradouro", true);
    public MeuCampoTexto jtfNumero = new MeuCampoTexto(10, "Numero", true);
    public MeuCampoTexto jtfCidade = new MeuCampoTexto(30, "Cidade", true);
    public MeuCampoTexto jtfBairro = new MeuCampoTexto(30, "Bairro", true);
    public MeuCampoTexto jtfEstado = new MeuCampoTexto(30, "Estado", true);
    public MeuCampoTexto jtfComplemento = new MeuCampoTexto(50, "Complemento", true);
    
    
    public TelaCadastroCliente(){
        super("Cadastro de Clientes");
        adicionaComponente(1, 2, 1, 1, jtfCodigo);
        adicionaComponente(1, 1, 1, 1, jtfNome);
        adicionaComponente(3, 1, 1, 1, jtfCpf);
        adicionaComponente(3, 2, 1, 1, jtfRg);
        adicionaComponente(5, 1, 1, 1, jtfLogradouro);
        adicionaComponente(5, 2, 1, 1, jtfNumero);
        adicionaComponente(7, 1, 1, 1, jtfCidade);
        adicionaComponente(8, 1, 1, 1, jtfBairro);
        adicionaComponente(9, 1, 1, 1, jtfEstado);
        adicionaComponente(10, 1, 1, 1, jtfComplemento);
        pack();
        habilitaComponentes(false);
        
    }
    
    @Override
    public void consultar(){
        super.consultar();
        try{
            new TelaConsulta(this,
                        "Consulta de Clientes",
                        new String[] {"Codigo", "Nome", "CPF", "RG", "Logradouro", "Numero", "Cidade", "Bairro", "Estado", "Complemento"},
                        ClientesDao.SQL_PESQUISAR);
        } catch (SQLException ex){
            Logger.getLogger(TelaCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPersistencia(){
            cliente.setCodigo(Integer.parseInt(jtfCodigo.getText()));
            cliente.setNome(jtfNome.getText());
            cliente.setCpf(jtfCpf.getText());
            cliente.setRg(jtfRg.getText());
            cliente.setLogradouro(jtfLogradouro.getText());
            cliente.setNumero(jtfNumero.getText());
            cliente.setCidade(jtfCidade.getText());
            cliente.setBairro(jtfBairro.getText());
            cliente.setEstado(jtfEstado.getText());
            cliente.setComplemento(jtfComplemento.getText());
    }
    
    @Override
    public void incluirBD(){
        setPersistencia();
        clienteDao.inserir();
    }
    
    @Override
    public void alterarBD(){
        setPersistencia();
        clienteDao.alterar();
    }
    
    @Override
    public void excluirBD(){
        clienteDao.excluir();
        super.excluirBD();
    }
    
    @Override
    public void preencherDados(int pk){
        cliente.setCodigo(pk);
        clienteDao.consultar();
        jtfCodigo.setText("" + cliente.getCodigo());
        jtfNome.setText("" + cliente.getNome());
        jtfCpf.setText("" + cliente.getCpf());
        jtfRg.setText("" + cliente.getRg());
        jtfLogradouro.setText("" + cliente.getLogradouro());
        jtfNumero.setText("" + cliente.getNumero());
        jtfCidade.setText("" + cliente.getCidade());
        jtfBairro.setText("" + cliente.getBairro());
        jtfEstado.setText("" + cliente.getEstado());
        jtfComplemento.setText("" + cliente.getComplemento());
        super.preencherDados(pk);
    }
}
