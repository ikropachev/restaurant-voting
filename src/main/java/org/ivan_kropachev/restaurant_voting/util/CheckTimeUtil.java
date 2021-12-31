package org.ivan_kropachev.restaurant_voting.util;

import org.ivan_kropachev.restaurant_voting.util.exception.LateVoteException;

import java.time.LocalTime;

public class CheckTimeUtil {

    public static void checkTime() {
        if (LocalTime.now().isAfter(LocalTime.of(11, 00))) {
            throw new LateVoteException("Too late for voting");
        }
    }
}
