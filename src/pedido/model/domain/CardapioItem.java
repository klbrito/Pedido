package pedido.model.domain;

public class CardapioItem {

    private int id_cardapio_item;
    private int id_cardapio;
    private String tx_cardapio_item;
    private Cardapio cardapio;
    
    public CardapioItem() {
    }

    public int getId_cardapio_item() {
        return id_cardapio_item;
    }

    public void setId_cardapio_item(int id_cardapio_item) {
        this.id_cardapio_item = id_cardapio_item;
    }

    public int getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(int id_cardapio) {
        this.id_cardapio = id_cardapio;
    }

    public String getTx_cardapio_item() {
        return tx_cardapio_item;
    }

    public void setTx_cardapio_item(String tx_cardapio_item) {
        this.tx_cardapio_item = tx_cardapio_item;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }
    
     
}
