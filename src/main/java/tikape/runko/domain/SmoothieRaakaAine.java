package tikape.runko.domain;

public class SmoothieRaakaAine {

    private Integer smoothieId;
    private Integer raakaAineId;
    private Integer jarjestys;
    private String maara;
    private String ohje;

    public SmoothieRaakaAine(Integer smoothieId, Integer raakaAineId, Integer jarjestys, String maara, String ohje) {
        this.smoothieId = smoothieId;
        this.raakaAineId = raakaAineId;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }

    public Integer getRaakaAineId() {
        return raakaAineId;
    }

    public Integer getJarjestys() {
        return jarjestys;
    }

    public Integer getSmoothieId() {
        return smoothieId;
    }

    public String getMaara() {
        return maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setJarjestys(Integer jarjestys) {
        this.jarjestys = jarjestys;
    }

    public void setMaara(String maara) {
        this.maara = maara;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    public void setRaakaAineId(Integer raakaAineId) {
        this.raakaAineId = raakaAineId;
    }

    public void setSmoothieId(Integer smoothieId) {
        this.smoothieId = smoothieId;
    }

    

}
