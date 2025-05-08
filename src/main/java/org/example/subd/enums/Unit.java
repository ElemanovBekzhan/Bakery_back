package org.example.subd.enums;

public enum Unit{
    kg("Киллограм"),
    g("Грамм"),
    gr("Миллиграмм"),
    µg("Микрограмм"),
    l("Литр"),
    ml("Миллилитр"),
    µl("Микролитр"),
    pcs("Штука");
    private final String name;

    Unit(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
