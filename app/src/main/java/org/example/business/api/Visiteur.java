package org.example.business.api;

import org.example.business.impl.Difference;
import org.example.business.impl.Population;
import org.example.business.impl.Selection;
import org.example.business.impl.Union;

public interface Visiteur {
    void visiter(Population p);
    void visiter(Selection s);
    void visiter(Difference d);
    void visiter(Union u);
    int getCout();
}
