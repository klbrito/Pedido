package javafxmvc.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Cardapio implements Serializable {

    private int id_cardapio;
    private LocalDate dt_cardapio;
    private List<CardapioItem> CardapioItem;

    public Cardapio(){
    }
    
    public Cardapio(int id_cardapio, LocalDate dt_cardapio) {
        this.id_cardapio = id_cardapio;
        this.dt_cardapio = dt_cardapio;
    }

    public int getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(int id_cardapio) {
        this.id_cardapio = id_cardapio;
    }

    public LocalDate getDt_cardapio() {
        return dt_cardapio;
    }

    public void setDt_cardapio(LocalDate dt_cardapio) {
        this.dt_cardapio = dt_cardapio;
    }

    public List<CardapioItem> getCardapioItem() {
        return CardapioItem;
    }

    public void setCardapioItem(List<CardapioItem> CardapioItem) {
        this.CardapioItem = CardapioItem;
    }
    
}