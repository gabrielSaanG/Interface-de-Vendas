
package tela;

import bd.BancoDados;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaConsulta extends JInternalFrame implements MouseListener{
    private TelaCadastro telaChamadora;
    private TelaVenda telaChamadoraVenda;
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable tabela = new JTable(dtm){
            @Override
            public boolean isCellEditable(int linha, int coluna){
                return false;
            }
    };
    private JScrollPane jsp = new JScrollPane(tabela);
    
    public TelaConsulta(TelaCadastro telaChamadora, String titulo, String[] coluna, String sql) throws SQLException{
        super(titulo);
        this.telaChamadora = telaChamadora;
        tabela.getTableHeader().setReorderingAllowed(false);
        insereColunas(coluna);
        insereDados(sql);
        if (dtm.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Não existem dados cadastrados.");
            return;
        }
        getContentPane().add(jsp);
        pack();
        setVisible(true);
        TelaInicial.jdp.add(this);
        TelaInicial.jdp.moveToFront(this);
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    telaChamadora.preencherDados(Integer.parseInt((String) dtm.getValueAt(tabela.getSelectedRow(), 0)));
                    dispose();
                    removeTelaConsulta();
                }
            }
        });
    }
    
    public TelaConsulta(TelaVenda telaChamadoraVenda, String titulo, String[] coluna, String sql) throws SQLException{
        super(titulo);
        this.telaChamadoraVenda = telaChamadoraVenda;
        tabela.getTableHeader().setReorderingAllowed(false);
        insereColunas(coluna);
        insereDados(sql);
        if (dtm.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Não existem dados cadastrados.");
            return;
        }
        getContentPane().add(jsp);
        pack();
        setVisible(true);
        TelaInicial.jdp.add(this);
        TelaInicial.jdp.moveToFront(this);
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    telaChamadoraVenda.preencherDados(Integer.parseInt((String) dtm.getValueAt(tabela.getSelectedRow(), 0)));
                    dispose();
                    removeTelaConsulta();
                }
            }
        });
    }
    
    public void insereColunas(String[] colunas){
        for (int i =0; i< colunas.length; i++){
            dtm.addColumn(colunas[i]);
        }
    }
    
    public void insereDados(String sql) throws SQLException{
        List<String[]>dados = BancoDados.executaQuery(sql);
        for (int i = 0; i < dados.size(); i++){
            dtm.addRow(dados.get(i));
        }
    }
    
    public void removeTelaConsulta(){
        TelaInicial.jdp.remove(this);
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
