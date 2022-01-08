package org.ikropachev.voting.util;

import org.ikropachev.voting.util.exception.LateVoteException;

import java.time.LocalTime;

public class CheckTimeUtil {

    public static void checkTime() {
        if (LocalTime.now().isAfter(LocalTime.of(11, 00))) {
            throw new LateVoteException("Too late for change vote.");
        }
    }
}
