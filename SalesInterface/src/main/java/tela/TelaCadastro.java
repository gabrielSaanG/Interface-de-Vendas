
package tela;

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


public class TelaCadastro extends JInternalFrame implements ActionListener{
    public List<MeuComponente> componentes = new ArrayList();
    public JPanel jpBotoes = new JPanel();
    public JPanel jpComponentes = new JPanel();
    public JButton jbIncluir = new JButton("Incluir");
    public JButton jbAlterar = new JButton("Alterar");
    public JButton jbExcluir = new JButton("Excluir");
    public JButton jbConsultar = new JButton("Consultar");
    public JButton jbConfirmar = new JButton("Confirmar");
    public JButton jbCancelar = new JButton("Cancelar");
    
    public final int PADRAO = 0;
    public final int INCLUINDO = 1;
    public final int ALTERANDO = 2;
    public final int EXCLUINDO = 3;
    public final int CONSULTANDO = 4;
    public int estadoTela = PADRAO;
    public boolean temDadosNaTela = false;
    
    public TelaCadastro(String titulo){
        super(titulo, true, true, true, true);
        getContentPane().add(BorderLayout.PAGE_END, jpBotoes);
        getContentPane().add(BorderLayout.CENTER, jpComponentes);
        jpComponentes.setLayout(new GridBagLayout());
        jpBotoes.setLayout(new GridLayout(1, 6));
        jpBotoes.add(jbIncluir);
        jpBotoes.add(jbAlterar);
        jpBotoes.add(jbExcluir);
        jpBotoes.add(jbConsultar);
        jpBotoes.add(jbConfirmar);
        jpBotoes.add(jbCancelar);
        jbIncluir.addActionListener(this);
        jbAlterar.addActionListener(this);
        jbExcluir.addActionListener(this);
        jbConsultar.addActionListener(this);
        jbConfirmar.addActionListener(this);
        jbCancelar.addActionListener(this);
        
        pack();
        setVisible(true);
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
                texto = texto + " * ";
            } else{
                texto = texto + " ";
            }
            JLabel jl = new JLabel(texto);
            gbc.anchor = GridBagConstraints.EAST;
            jpComponentes.add(jl, gbc);
            gbc.gridx++;
        }
        gbc.gridheight = qtdLinhas;
        gbc.gridwidth = qtdColunas; 
        gbc.anchor = GridBagConstraints.WEST;
        jpComponentes.add(componente, gbc);
        if (componente instanceof MeuComponente){
            componentes.add((MeuComponente)componente);
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
        jbIncluir.setEnabled(estadoTela == PADRAO);
        jbAlterar.setEnabled(estadoTela == PADRAO && temDadosNaTela);
        jbExcluir.setEnabled(estadoTela == PADRAO && temDadosNaTela);
        jbConsultar.setEnabled(estadoTela == PADRAO);
        jbConfirmar.setEnabled(estadoTela != PADRAO);
        jbCancelar.setEnabled(estadoTela != PADRAO);
        
    }
    
    @Override   
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == jbIncluir){
            incluir();
        } else if (e.getSource() == jbAlterar){
            alterar();
        } else if (e.getSource() == jbExcluir){
            excluir();
        }else if (e.getSource() == jbConsultar){
            consultar();
        } else if (e.getSource() == jbConfirmar){
            confirmar();
        } else if (e.getSource() == jbCancelar){
            cancelar();
        }
        habilitaBotoes();
}
    public void incluir(){
        estadoTela = INCLUINDO;
        limpaComponentes();
        habilitaComponentes(true); 
    }
    
    public void alterar(){
        estadoTela = ALTERANDO;
        habilitaComponentes(true);
        
    }
    
    public void excluir(){
        estadoTela = EXCLUINDO;
    }
    
    public void consultar(){
        estadoTela = CONSULTANDO;
        habilitaBotoes();
    }

    public void confirmar(){
        if (estadoTela == INCLUINDO){
            if (validaComponentes()){
                incluirBD();
            } else{
                return;
            }
        } else if (estadoTela == ALTERANDO){
            if (validaComponentes()){
                alterarBD();
            } else{
                return;
            }
        } else if (estadoTela == EXCLUINDO){
            excluirBD();
        }
        estadoTela = PADRAO;
        habilitaBotoes();
    }
    
    public void cancelar(){
        estadoTela = PADRAO;
        limpaComponentes();
        habilitaComponentes(false);
    }
    
    public void incluirBD(){
        
    }
    
    public void alterarBD(){
    
    }
    
    public void excluirBD(){
       limpaComponentes(); 
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
}
