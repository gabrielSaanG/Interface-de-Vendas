/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bd.BancoDados;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import pojo.Venda;
/**
 *
 * @author Administrator
 */
public class VendasDao {
    public static final String SQL_VENDAS_INCLUIR = "INSERT INTO Vendas VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_VENDAS_PESQUISAR = "SELECT * FROM Vendas";
    public static final String SQL_VENDAS_CONSULTA = "SELECT * FROM Vendas WHERE numeroVenda = ?";
    private final Venda venda;
    
    
    public VendasDao(Venda venda){
        this.venda = venda;
    }
    
    public boolean incluirVenda(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_VENDAS_INCLUIR);
            ps.setInt(1, venda.getNumeroVenda());
            ps.setInt(2, venda.getQuantVendida());
            ps.setDouble(3, venda.getPrecoVendaUnitario());
            ps.setDouble(4, venda.getDescontoTotal());
            ps.setDouble(5, venda.getValorTotal());
            ps.setString(6, venda.getNomeCliente());
            ps.setString(7, venda.getNomeProduto());
            ps.setDate(8, venda.getData());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda bem-sucedida!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível incluir a venda.");
            return false;
        }
        
    }
    
     public boolean consultarVenda(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_VENDAS_CONSULTA);
            ps.setInt(1, venda.getNumeroVenda());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda bem-sucedida!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível incluir a venda.");
            return false;
        }
        
    }
    

}
