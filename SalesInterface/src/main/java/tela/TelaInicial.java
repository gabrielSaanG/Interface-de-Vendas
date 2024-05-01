package tela;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import tela.TelaVenda;

public class TelaInicial extends JFrame implements ActionListener{
    public static JDesktopPane jdp = new JDesktopPane();
    public JMenuBar jmb = new JMenuBar();
    public JMenu jmCadastro = new JMenu("Cadastro");
    public JMenuItem jmiCadastroCliente = new JMenuItem("Cliente");
    public JMenuItem jmiCadastroProduto = new JMenuItem("Produtos");
    
    public JMenu jmVenda = new JMenu("Vendas");
    public JMenuItem jmiVenda = new JMenuItem("Venda");
    
    
    public TelaInicial(){
        getContentPane().add(jdp);
        setTitle("Sistema de Vendas");
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(jmb);
        jmb.add(jmCadastro);
        jmb.add(jmVenda);
        jmCadastro.add(jmiCadastroCliente);
        jmCadastro.add(jmiCadastroProduto);
        jmVenda.add(jmiVenda);
        
        jmiCadastroCliente.addActionListener(this);
        jmiCadastroProduto.addActionListener(this);
        jmiVenda.addActionListener(this);
        
        setVisible(true);
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == jmiCadastroCliente){
           TelaCadastroCliente telaCadCli = new TelaCadastroCliente();
           jdp.add(telaCadCli);
       } else if (e.getSource() == jmiCadastroProduto){
           TelaCadastroProduto telaCadProd = new TelaCadastroProduto();
           jdp.add(telaCadProd);
       } else if (e.getSource() == jmiVenda){
           TelaRealizarVenda telaV = new TelaRealizarVenda();
           jdp.add(telaV);
       }
    }
}
