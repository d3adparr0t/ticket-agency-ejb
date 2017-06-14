package com.alchesoft.training.jboss;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by alex on 14.06.17.
 */
@Data
@RequiredArgsConstructor
public class Seat {

    private final int id;
    private final String seat;
    private final int price;

    private boolean booked;
}
