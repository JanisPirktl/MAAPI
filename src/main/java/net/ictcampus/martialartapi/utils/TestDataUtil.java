package net.ictcampus.martialartapi.utils;

import net.ictcampus.martialartapi.model.models.Origin;
import net.ictcampus.martialartapi.model.models.Martialart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestDataUtil {
    public static List<Martialart> getTestMartialarts(){
        List<Martialart> martialarts = new ArrayList<>();
        HashSet<Origin> origins = new HashSet<>();

        Origin origin = new Origin();

        origin.setName("Fantasia");
        origins.add(origin);

        for (int i = 1; i <= 3; i++) {
            Martialart martialart = new Martialart();
            martialart.setName("Martial Art "+i);

            origin.getMartialarts().add(martialart);
            martialart.setOrigin(origin);
            martialarts.add(martialart);
        }

        return martialarts;
    }
}
