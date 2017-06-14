package com.alchesoft.training.jboss;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;


@Stateful(name = "TheatreBookerEJB")
public class TheatreBookerBean {

    private final static Logger log = Logger.getLogger(TheatreBookerBean.class);

    private int account;

    @EJB
    private TheatreBox theatreBox;

    @PostConstruct
    public void createCustomer() {
        this.account = 100;
    }

    public String bookSeat(int seatId) {
        Seat seat = theatreBox.findById(seatId);
        if(seat.isBooked()) {
            return "Seat already booked!";
        }
        if (seat.getPrice() > account) {
            return "You're way too poor!";
        }
        theatreBox.buyTicket(seat);
        account -= seat.getPrice();
        String success = String.format("Seat %d booked!", seat.getId());
        log.info(success);
        return success;
    }
}
