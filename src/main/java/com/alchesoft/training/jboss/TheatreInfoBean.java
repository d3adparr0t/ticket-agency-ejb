package com.alchesoft.training.jboss;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;


@Stateless
@Remote(TheatreInfo.class)
public class TheatreInfoBean implements TheatreInfo {

    @EJB
    private TheatreBox theatreBox;

    public String printSeats() {
        return theatreBox.printSeats();
    }
}
