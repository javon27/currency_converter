/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin3
 */
public class Currency {
    public String unit;
    public String country;
    public Currency(String unit, String country) {
        this.unit = unit;
        this.country = country;
        System.out.println(unit + " " + country);
    }
}
