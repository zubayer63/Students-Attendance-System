package com.example.zubayer.strudentsattendance;

/**
 * Created by Zubayer on 29-Mar-19.
 */

public class Attandanc {
    String NM,DMBS,CN,SAD,TOC;

    public String getNM() {
        return NM;
    }

    public String getDMBS() {
        return DMBS;
    }

    public String getCN() {
        return CN;
    }

    public String getSAD() {
        return SAD;
    }

    public String getTOC() {
        return TOC;
    }

    public Attandanc(String NM, String DMBS, String CN, String SAD, String TOC) {

        this.NM = NM;
        this.DMBS = DMBS;
        this.CN = CN;
        this.SAD = SAD;
        this.TOC = TOC;
    }
}
