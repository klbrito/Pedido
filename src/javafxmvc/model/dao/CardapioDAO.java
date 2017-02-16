package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.CardapioItem;
import javafxmvc.model.domain.Cardapio;

public class CardapioDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Cardapio cardapio) {
        String sql = "INSERT INTO cardapio(dt_cardapio) VALUES(?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(cardapio.getDt_cardapio()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Cardapio cardapio) {
        String sql = "UPDATE cardapio SET dt_cardapio WHERE id_cardapio=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(cardapio.getDt_cardapio()));
            stmt.setDouble(2, cardapio.getId_cardapio());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Cardapio cardapio) {
        String sql = "DELETE FROM cardapio WHERE id_cardapio=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cardapio.getId_cardapio());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cardapio> listar() {
        String sql = "SELECT * FROM cardapio";
        List<Cardapio> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cardapio cardapio = new Cardapio();
                List<CardapioItem> cardapioItem = new ArrayList();

                cardapio.setId_cardapio(resultado.getInt("id_cardapio"));
                cardapio.setDt_cardapio(resultado.getDate("dt_cardapio").toLocalDate());

                //Obtendo os dados completos dos Itens de Venda associados à Venda
                CardapioItemDAO cardapioItemDAO = new CardapioItemDAO();
                cardapioItemDAO.setConnection(connection);
                cardapioItem = cardapioItemDAO.listarPorVenda(cardapio);

                cardapio.setCardapioItem(cardapioItem);
                retorno.add(cardapio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Cardapio buscar(Cardapio cardapio) {
        String sql = "SELECT * FROM cardapio WHERE id_cardapio=?";
        Cardapio retorno = new Cardapio();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cardapio.getId_cardapio());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                cardapio.setId_cardapio(resultado.getInt("id_cardapio"));
                cardapio.setDt_cardapio(resultado.getDate("dt_cardapio").toLocalDate());
                retorno = cardapio;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }


    public Cardapio buscarUltimaCardapio() {

        String sql = "SELECT max(cdVenda) FROM vendas";
        Cardapio retorno = new Cardapio();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setId_cardapio(resultado.getInt("max"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Map<Integer, ArrayList> listarQuantidadeVendasPorMes() {
        String sql = "select count(cdVenda), extract(year from data) as ano, extract(month from data) as mes from vendas group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("ano")))
                {
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("count"));
                    retorno.put(resultado.getInt("ano"), linha);
                }else{
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("count"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
