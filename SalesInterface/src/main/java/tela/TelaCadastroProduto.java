
package tela;

import componente.MeuCampoTexto;
import dao.ProdutosDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Produto;

public class TelaCadastroProduto extends TelaCadastro{
    
    public Produto produto = new Produto();
    public ProdutosDao produtoDao = new ProdutosDao(produto);
    
    public MeuCampoTexto jtfCodigo = new MeuCampoTexto(10, "Código", true);
    public MeuCampoTexto jtfNomeProduto = new MeuCampoTexto(50, "Nome", true);
    public MeuCampoTexto jtfQuantidade = new MeuCampoTexto(5, "Quantidade", true);
    public MeuCampoTexto jtfPrecoCompra = new MeuCampoTexto(10, "Preco de Compra", false);
    public MeuCampoTexto jtfPrecoVenda = new MeuCampoTexto(10, "Preco de Venda", true);
    
    public TelaCadastroProduto(){
        super("Cadastro de Produtos");
        adicionaComponente(1, 1, 1, 1, jtfCodigo);
        adicionaComponente(2, 1, 1, 2, jtfNomeProduto);
        adicionaComponente(3, 1, 1, 1, jtfQuantidade);
        adicionaComponente(4, 1, 1, 1, jtfPrecoCompra);
        adicionaComponente(5, 1, 1, 1, jtfPrecoVenda);
        pack();
        habilitaComponentes(false);
    }
  
    @Override
    public void consultar(){
        super.consultar();
        try {
            new TelaConsulta(this,
                    "Consulta de Produtos",
                    new String[] {"Codigo", "Nome", "Quantidade", "Preco de Compra", "Preco de Venda"},
                    ProdutosDao.SQL_PESQUISAR);
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public void setPersistencia(){
        produto.setCodigo(Integer.parseInt(jtfCodigo.getText()));
        produto.setNome(jtfNomeProduto.getText());
        produto.setQuantidade(Integer.parseInt(jtfQuantidade.getText()));
        produto.setPrecoCompra(Double.parseDouble(jtfPrecoCompra.getText()));
        produto.setPrecoVenda(Double.parseDouble(jtfPrecoVenda.getText()));
    }
    
    @Override
    public void incluirBD(){
        setPersistencia();
        produtoDao.inserir();
    }
    
    @Override
    public void alterarBD(){
        setPersistencia();
        produtoDao.alterar();
    }
    
    @Override
    public void excluirBD(){
        produtoDao.excluir();
        super.excluirBD();
    }
    
    @Override
    public void preencherDados(int pk){
        produto.setCodigo(pk);
        produtoDao.consultar();
        jtfCodigo.setText("" + produto.getCodigo());
        jtfNomeProduto.setText("" + produto.getNome());
        jtfQuantidade.setText("" + produto.getQuantidade());
        jtfPrecoCompra.setText("" + produto.getPrecoCompra());
        jtfPrecoVenda.setText("" + produto.getPrecoVenda());
        super.preencherDados(pk);
    }
}

