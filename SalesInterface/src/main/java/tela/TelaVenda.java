/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tela;

import componente.MeuCampoTexto;
import componente.MeuComponente;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class TelaVenda extends JInternalFrame implements ActionListener{

    public List<MeuComponente> componentes = new ArrayList();
    public List<MeuCampoTexto> componentesTexto = new ArrayList();
    public JPanel jpBotoes = new JPanel();
    public JPanel jpComponentes = new JPanel();
    public JButton jbInserirCliente = new JButton("Inserir Cliente");
    public JButton jbInserirProduto = new JButton("Inserir Produto");
    public JButton jbCalcularPrecoFinal = new JButton ("Calcular Preco Final");
    public JButton jbRealizarVenda = new JButton("Realizar Venda");
    public JButton jbConsultarVenda = new JButton("Consultar Vendas");
    
    
    public final int PADRAO = 0;
    public final int ALTERAR = 1;
    public final int CONSULTAR = 2;
    public final int EXCLUIR = 3;
    public int estadoTela = PADRAO;
    public boolean temDadosNaTela = false;
    
    public int numeroVenda;
    
    public TelaVenda(String titulo){
        super(titulo, true, true, true, true);
        getContentPane().add(BorderLayout.PAGE_END, jpBotoes);
        getContentPane().add(BorderLayout.CENTER, jpComponentes);
        
        jpComponentes.setLayout(new GridBagLayout());
        jpBotoes.setLayout(new GridLayout(1, 4));
        jpBotoes.add(jbInserirCliente);
        jpBotoes.add(jbInserirProduto);
        jpBotoes.add(jbCalcularPrecoFinal);
        jpBotoes.add(jbRealizarVenda);
        jpBotoes.add(jbConsultarVenda);
        jbInserirCliente.addActionListener(this);
        jbInserirProduto.addActionListener(this);
        jbCalcularPrecoFinal.addActionListener(this);
        jbRealizarVenda.addActionListener(this);
        jbConsultarVenda.addActionListener(this);
        pack();
        setVisible(true);
        habilitaBotoes();
       
    }

     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbInserirCliente){
            inserirCliente();
        } else if (e.getSource() == jbInserirProduto){
            consultaProduto();
        } else if (e.getSource() == jbCalcularPrecoFinal){
            botaoCalculaPrecoFinal();
        } else if (e.getSource() == jbRealizarVenda){
            realizaVenda();
        } else if (e.getSource() == jbConsultarVenda){
            consultaVendas();
        }
        habilitaBotoes();
    }
    
    public void adicionaComponente(int linha, int coluna, int qtdLinhas, int qtdColunas, JComponent componente){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = linha;
        gbc.gridx = coluna;
        if (componente instanceof MeuComponente){
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            String texto = ((MeuComponente) componente).getDica() + ": ";
            if (((MeuComponente) componente).eObrigatorio()){
                texto = texto + " *";
            } else{
                texto = texto + " ";
            }
            JLabel jl = new JLabel (texto);
            gbc.anchor = GridBagConstraints.EAST;
            jpComponentes.add(jl, gbc);
            gbc.gridx++;
        }
        gbc.gridheight = qtdLinhas;
        gbc.gridwidth = qtdColunas;
        gbc.anchor = GridBagConstraints.WEST;
        jpComponentes.add(componente, gbc);
        if (componente instanceof MeuComponente){
            componentes.add((MeuComponente) componente);
        }
    }
   
    public void habilitaComponentes(boolean status){
        for (int i = 0; i < componentes.size(); i++){
            componentes.get(i).habilitar(status);
        }
    }
    
    public void limpaComponentes(){
        for (int i = 0; i < componentes.size(); i++){
            componentes.get(i).limpar();
        }
    }
    
    public void habilitaBotoes(){
        jbInserirCliente.setEnabled(estadoTela == PADRAO);
        jbInserirProduto.setEnabled(estadoTela == PADRAO);
        jbRealizarVenda.setEnabled(estadoTela == PADRAO);
        jbConsultarVenda.setEnabled(estadoTela == PADRAO);
    }
    
    public void preencherDados(int pk){
       estadoTela = PADRAO;
       temDadosNaTela = true;
       habilitaBotoes();
   }
   
   public boolean validaComponentes(){
      String erroObrigatorios = "";
        for (int i=0; i < componentes.size(); i++){
            if (componentes.get(i).eObrigatorio() && componentes.get(i).eVazio()){
                erroObrigatorios = erroObrigatorios + componentes.get(i).getDica() + "\n";
            }
        }
        if (erroObrigatorios.isEmpty()){
            return true;
        } else{
            JOptionPane.showMessageDialog(null, "Os campos abaixo são obrigatórios e não foram preenchidos:\n\n" + erroObrigatorios);
            return false;
        }
   }
    
   
    
   public void inserirCliente(){
       estadoTela = CONSULTAR;
   }
   
   public void inserirProduto(){
       estadoTela = CONSULTAR;
   }
   
   public void realizaVenda(){
       if (validaComponentes()){
           vendaBD();
       } else{
           return;
       }
       estadoTela = PADRAO;
       habilitaBotoes();
   }
   
   public void consultaProduto(){
       estadoTela = CONSULTAR;
       habilitaBotoes();
   }
   
   public void consultaVendas(){
       estadoTela = CONSULTAR;
       habilitaBotoes();
   }
 
   public void vendaBD(){
       limpaComponentes();
   }
    
   public void botaoCalculaPrecoFinal(){
        
   }
    

   
}
  
