package by.bsuir.shigalo7.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Massage {

    private static Massage instance = null;
    private String str = "";

    public static Massage getInstance() {
        if(instance == null) { instance = new Massage(); }
        return instance;
    }

    private Massage() { }
}
