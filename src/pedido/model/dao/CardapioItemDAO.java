package pedido.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pedido.model.domain.CardapioItem;
import pedido.model.domain.Cardapio;

public class CardapioItemDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(CardapioItem cardapioItem) {
        String sql = "INSERT INTO cardapio_item(id_cardapio, tx_cardapio_item) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cardapioItem.getCardapio().getId_cardapio());
            stmt.setString(2, cardapioItem.getTx_cardapio_item());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CardapioItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(CardapioItem cardapioItem) {
        return true;
    }

    public boolean remover(CardapioItem cardapioItem) {
        String sql = "DELETE FROM cardapio_item WHERE cdItemDeVenda=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cardapioItem.getId_cardapio_item());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CardapioItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<CardapioItem> listar() {
        String sql = "SELECT * FROM cardapio_item";
        List<CardapioItem> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                CardapioItem cardapioItem = new CardapioItem();
                Cardapio cardapio = new Cardapio();
                cardapioItem.setId_cardapio_item(resultado.getInt("id_cardapio_item"));
                cardapioItem.setTx_cardapio_item(resultado.getString("tx_cardapio_item"));
                
                cardapio.setId_cardapio(resultado.getInt("id_cardapio"));
                                
                CardapioDAO cardapioDAO = new CardapioDAO();
                cardapioDAO.setConnection(connection);
                cardapio = cardapioDAO.buscar(cardapio);
                
                cardapioItem.setCardapio(cardapio);
                
                retorno.add(cardapioItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<CardapioItem> listarPorVenda(Cardapio cardapio) {
        String sql = "SELECT * FROM cardapio_item WHERE id_cardapio=?";
        List<CardapioItem> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cardapio.getId_cardapio());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                CardapioItem cardapioItem = new CardapioItem();
                Cardapio c = new Cardapio();
                cardapioItem.setId_cardapio_item(resultado.getInt("id_cardapio_item"));
                cardapioItem.setTx_cardapio_item(resultado.getString("tx_cardapio_item"));
                
                c.setId_cardapio(resultado.getInt("id_cardapio"));
                
                cardapioItem.setCardapio(c);
                
                retorno.add(cardapioItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardapioItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public CardapioItem buscar(CardapioItem cardapioItem) {
        String sql = "SELECT * FROM cardapio_item WHERE id_cardapio_item=?";
        CardapioItem retorno = new CardapioItem();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cardapioItem.getId_cardapio_item());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Cardapio cardapio = new Cardapio();
                cardapioItem.setId_cardapio_item(resultado.getInt("id_cardapio_item"));
                cardapioItem.setTx_cardapio_item(resultado.getString("tx_cardapio_item"));
                
                cardapio.setId_cardapio(resultado.getInt("id_cardapio"));
                
                CardapioDAO cardapioDAO = new CardapioDAO();
                cardapioDAO.setConnection(connection);
                cardapio = cardapioDAO.buscar(cardapio);
                
                cardapioItem.setCardapio(cardapio);
                
                retorno = cardapioItem;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
