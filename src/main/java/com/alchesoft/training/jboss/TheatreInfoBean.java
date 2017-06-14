package com.alchesoft.training.jboss;

import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless(name = "TheatreInfoEJB")
public class TheatreInfoBean {

    @EJB
    private TheatreBox theatreBox;

    public String printSeats() {
        return theatreBox.printSeats();
    }
}
