package modelo;

/**
 * Created by Mónica Francisco on 25/01/2016.
 */
public class Plano {
    private String peqAlmoco;
    private String meioManha;
    private String almoco;
    private String lanche;
    private String jantar;
    private String ceia;

    public Plano(String almoco, String ceia, String jantar, String lanche, String meioManha, String peqAlmoco) {
        this.almoco = almoco;
        this.ceia = ceia;
        this.jantar = jantar;
        this.lanche = lanche;
        this.meioManha = meioManha;
        this.peqAlmoco = peqAlmoco;
    }

    public String getAlmoco() {
        return almoco;
    }

    public void setAlmoco(String almoco) {
        this.almoco = almoco;
    }

    public String getCeia() {
        return ceia;
    }

    public void setCeia(String ceia) {
        this.ceia = ceia;
    }

    public String getJantar() {
        return jantar;
    }

    public void setJantar(String jantar) {
        this.jantar = jantar;
    }

    public String getLanche() {
        return lanche;
    }

    public void setLanche(String lanche) {
        this.lanche = lanche;
    }

    public String getMeioManha() {
        return meioManha;
    }

    public void setMeioManha(String meioManha) {
        this.meioManha = meioManha;
    }

    public String getPeqAlmoco() {
        return peqAlmoco;
    }

    public void setPeqAlmoco(String peqAlmoco) {
        this.peqAlmoco = peqAlmoco;
    }
}
