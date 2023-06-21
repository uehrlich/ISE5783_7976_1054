package primitives;

public class Material {

    private Double3 Kd = Double3.ZERO;
    private Double3 Ks = Double3.ZERO;
    private Double3 Kr = Double3.ZERO;
    private Double3 Kt = Double3.ZERO;

    private int nShininess = 1;

    /**
     * setter
     * @param kd
     * @return
     */
    public Material setKd(Double3 kd) {
        this.Kd = kd;
        return this;
    }

    /**
     * setter
     * @param ks
     * @return
     */
    public Material setKs(Double3 ks) {
        this.Ks = ks;
        return  this;
    }

    public Material setKt(Double3 kt) {
        this.Kt = kt;
        return  this;
    }
    public Material setKt(double kt) {
        this.Kt = new Double3(kt);
        return  this;
    }


    public Material setKr(Double3 kr) {
        this.Kr = kr;
        return  this;
    }

    public Material setKr(double kr) {
        this.Kr = new Double3(kr);
        return  this;
    }


    /**
     * setter
     * @param kd
     * @return
     */
    public Material setKd(double kd) {
        this.Kd = new Double3(kd);
        return this;
    }

    /**
     * setter
     * @param ks
     * @return
     */
    public Material setKs(double ks) {
        this.Ks = new Double3(ks);
        return  this;
    }

    /**
     * setter
     * @param nShininess
     * @return
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public Double3 getKd() {
        return Kd;
    }

    public Double3 getKs() {
        return Ks;
    }

    public Double3 getKr() {
        return Kr;
    }

    public Double3 getKt() {
        return Kt;
    }

    public int getShininess() {
        return nShininess;
    }


}
