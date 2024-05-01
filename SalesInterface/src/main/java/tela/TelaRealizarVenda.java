/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tela;

import componente.MeuCampoTexto;
import dao.ClientesDao;
import dao.ProdutosDao;
import dao.VendasDao;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pojo.Cliente;
import pojo.Produto;
import pojo.Venda;

/**
 *
 * @author Administrator
 */
public class TelaRealizarVenda extends TelaVenda{
    
    public Produto produto = new Produto();
    public ProdutosDao produtosDao = new ProdutosDao(produto);
    
    public Cliente cliente = new Cliente();
    public ClientesDao clientesDao = new ClientesDao(cliente);
    
    public Venda venda = new Venda();
    public VendasDao vendasDao = new VendasDao(venda);  
    
    public MeuCampoTexto jtfAdicionaCliente = new MeuCampoTexto(50, "Cliente", true);
    public MeuCampoTexto jtfAdicionaProduto = new MeuCampoTexto(50, "Produto", true);
    public MeuCampoTexto jtfInsereQuantidade = new MeuCampoTexto(10, "Quantidade", true);
    public MeuCampoTexto jtfQuantidadeVendida = new MeuCampoTexto(10, "Quantidade de venda", true);
    public MeuCampoTexto jtfPrecoCompra = new MeuCampoTexto(10, "Preco de compra", false);
    public MeuCampoTexto jtfPrecoVenda = new MeuCampoTexto(10, "Preco de venda unitário", true);
    public MeuCampoTexto jtfDescontoTotal = new MeuCampoTexto(10, "Desconto total", true);
    public MeuCampoTexto jtfPrecoFinal = new MeuCampoTexto(10, "Preco final", true);
    public MeuCampoTexto jtfDataAtual = new MeuCampoTexto(10, "Data atual", true);
    public MeuCampoTexto jtfNumeroVenda = new MeuCampoTexto(10, "Numero da Venda", true);
    
    
    LocalDate dataAtual = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String data = dataAtual.format(formatter);
    Date dbDate = Date.valueOf(dataAtual);
    
    public final int ORIGEM_CLIENTE = 0;
    public final int ORIGEM_PRODUTO = 1;
    public final int ORIGEM_VENDA = 2;
    
    public int estadoDaOrigem;
    
    public TelaRealizarVenda() {
        super("Tela de Vendas");
        adicionaComponente(1, 1, 1, 3, jtfAdicionaCliente);
        adicionaComponente(2, 1, 1, 3, jtfAdicionaProduto);
        adicionaComponente(3, 1, 1, 1, jtfInsereQuantidade);
        adicionaComponente(4, 1, 1, 1, jtfQuantidadeVendida);
        adicionaComponente(3, 3, 1, 1, jtfPrecoCompra);
        adicionaComponente(4, 3, 1, 1, jtfPrecoVenda);
        adicionaComponente(5, 1, 1, 3, jtfDescontoTotal);
        adicionaComponente(5, 3, 1, 1, jtfPrecoFinal);
        adicionaComponente(6, 1, 1, 1, jtfDataAtual);
        adicionaComponente(6, 3, 1, 1, jtfNumeroVenda);
        pack();
        habilitaComponentes(false);   
    }
    
      
    public void setPersistencia(){
        produto.setNome(jtfAdicionaProduto.getText());
        produto.setQuantidade(Integer.parseInt(jtfInsereQuantidade.getText()));
        produto.setPrecoCompra(Double.parseDouble(jtfPrecoCompra.getText()));
        cliente.setNome(jtfAdicionaCliente.getText());
        venda.setNomeCliente(cliente.getNome());
        venda.setNomeProduto(produto.getNome());
        venda.setData(dbDate);
        venda.setNumeroVenda(Integer.parseInt(jtfNumeroVenda.getText()));
        venda.setQuantVendida(Integer.parseInt(jtfQuantidadeVendida.getText()));
        System.out.println(venda.getData());
        venda.setQuantVendida(Integer.parseInt(jtfQuantidadeVendida.getText()));
        venda.setPrecoVendaUnitario(Double.parseDouble(jtfPrecoVenda.getText()));
        venda.setDescontoTotal(Integer.parseInt(jtfDescontoTotal.getText()));
        venda.setValorTotal(Double.parseDouble(jtfPrecoFinal.getText()));
        cliente.setNome(jtfAdicionaCliente.getText());
    }

     
    public double descontoTotal(){
        return Double.parseDouble(jtfDescontoTotal.getText())/100;
    }
    
    public void calculaPrecoFinal(){
        double precoVenda = Double.parseDouble(jtfPrecoVenda.getText());
        double desconto = descontoTotal();
        int quantidade = Integer.parseInt(jtfQuantidadeVendida.getText());
        double precoFinal = precoVenda * quantidade;
        
        precoFinal = precoFinal - (precoFinal * desconto);
        
        
        String valorString = String.valueOf(precoFinal);
        
        jtfPrecoFinal.setText(valorString);
    }
    
    public boolean validaQuantidade(){
        int quantidadeBanco = Integer.parseInt(jtfInsereQuantidade.getText());
        int quantidadeVendida = Integer.parseInt(jtfQuantidadeVendida.getText());
        
        if (jtfInsereQuantidade.getText() == null){
            return false;
        } else if (quantidadeBanco == 0){
            JOptionPane.showMessageDialog(null, "Este produto não está no estoque.");
            return false;
        } else if (quantidadeBanco < quantidadeVendida){
            JOptionPane.showMessageDialog(null, "Quantidade vendida é maior que quantidade em estoque.");
            return false;
        } else if (quantidadeVendida == 0){
            JOptionPane.showMessageDialog(null, "A Quantidade vendida deve ser maior que zero.");
            return false;
        }
        return true;
    }
    
    public boolean alteraQuantidade(){
        int quantidadeAtual = Integer.parseInt(jtfInsereQuantidade.getText());
        int quantidadeVendida = Integer.parseInt(jtfQuantidadeVendida.getText());
        produto.setQuantidade(quantidadeAtual-quantidadeVendida);
        produtosDao.alterar();
        return true;
    }
    
    @Override
    public void vendaBD(){
        setPersistencia();
//        vendasDao.insereQuantidade();
        if (validaQuantidade()){
            alteraQuantidade();
            vendasDao.incluirVenda();
        };
        super.vendaBD();
    }
    
    
//    @Override
//    public void consultaVenda(){
//        super.consultaVenda();
//        try{
//            new TelaConsulta(this,
//                    "Consulta de Produtos",
//                    new String[] {"Codigo", "Nome", "Quantidade", "Preco de Compra", "Preco de Venda"},
//                    ProdutosDao.SQL_PESQUISAR);
//        } catch (SQLException ex){
//            Logger.getLogger(TelaCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @Override
    public void inserirCliente(){
        estadoDaOrigem = ORIGEM_CLIENTE;
        System.out.println(jtfDataAtual.getText());
        jtfDataAtual.setText(data);
        jtfNumeroVenda.habilitar(true);
        super.inserirCliente();
        try{
            new TelaConsulta(this,
                    "Consulta de Clientes",
                    new String[] {"Codigo", "Nome", "CPF", "RG", "Logradouro", "Numero", "Cidade", "Bairro", "Estado", "Complemento"},
                    ClientesDao.SQL_PESQUISAR);
                    
            
        } catch (SQLException ex){
            Logger.getLogger(TelaCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
  
    @Override
    public void preencherDados(int pk){
        cliente.setCodigo(pk);
        switch (estadoDaOrigem){
            case ORIGEM_CLIENTE:
                clientesDao.consultar();
                jtfAdicionaCliente.setText("" + cliente.getNome());
                super.preencherDados(pk);
                break;
            case ORIGEM_PRODUTO:
                produto.setCodigo(pk);
                produtosDao.consultar();
                jtfAdicionaProduto.setText("" + produto.getNome());
                jtfInsereQuantidade.setText("" + produto.getQuantidade());
                jtfPrecoCompra.setText("" + produto.getPrecoCompra());
                super.preencherDados(pk);
                break;
            case ORIGEM_VENDA:
                venda.setNumeroVenda(pk);
                super.preencherDados(pk);
                break;
        }
        
    }
   
    @Override
    public void consultaProduto(){
        estadoDaOrigem = ORIGEM_PRODUTO;
        super.consultaProduto();
        try{
            new TelaConsulta(this,
                    "Consulta de Produtos",
                    new String[] {"Codigo", "Nome", "Quantidade", "Preco de Compra", "Preco de Venda"},
                    ProdutosDao.SQL_PESQUISAR);
            jtfQuantidadeVendida.habilitar(true);
            jtfPrecoVenda.habilitar(true);
            jtfDescontoTotal.habilitar(true);
        } catch (SQLException ex){
            Logger.getLogger(TelaCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void consultaVendas(){
       // validaQuantidade();
        super.consultaVendas();
        try{
            new TelaConsulta(this,
                    "Consulta de Produtos",
                    new String[] {"Numero da Venda", "Quantidade", "Preço de venda unitario", "Desconto total", "Valor total", "Nome do cliente", "Nome do produto", "Data"},
                    VendasDao.SQL_VENDAS_PESQUISAR);
        } catch (SQLException e){
            Logger.getLogger(TelaCadastroProduto.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void botaoCalculaPrecoFinal(){
        try{
            if (jtfDescontoTotal.getText() != null){
            calculaPrecoFinal();
            }
            
        } catch (NumberFormatException e){
            e.getStackTrace();
        }
        
    }
   
}
