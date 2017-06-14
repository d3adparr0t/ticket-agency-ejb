package com.alchesoft.training.jboss;

import static java.util.stream.Collectors.*;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by alex on 13.06.17.
 */
@Singleton
@Startup
public class TheatreBox {
    private static final Logger log = Logger.getLogger(TheatreBox.class);

    private List<Seat> seats;

    @PostConstruct
    public  void setup() {
        seats = new ArrayList<>(15);
        List<Seat> stalls = IntStream.range(0, 5).mapToObj(index -> new Seat(index, "Stalls", 40)).collect(toList());
        seats.addAll(stalls);
        List<Seat> circles = IntStream.range(5, 10).mapToObj(index -> new Seat(index, "Circle", 20)).collect(toList());
        seats.addAll(circles);
        List<Seat> balconies = IntStream.range(10, 15).mapToObj(index -> new Seat(index, "Balcony", 10)).collect(toList());
        seats.addAll(balconies);

        log.info("Seat Map constructed.");
    }

    @Lock(LockType.READ)
    public List<Seat> getSeats() {
        return this.seats;
    }

    @Lock(LockType.READ)
    public int getSeatPrice(int id) {
        return this.seats.stream()
                .filter(seat -> seat.getId() == id)
                .findFirst().get()
                .getPrice();
    }

    @Lock(LockType.READ)
    public void buyTicket(Seat seat) {
        seat.setBooked(true);
    }
}
