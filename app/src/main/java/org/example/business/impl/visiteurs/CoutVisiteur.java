package org.example.business.impl.visiteurs;

import org.example.business.api.Visiteur;
import org.example.business.api.Requete;
import org.example.business.impl.Difference;
import org.example.business.impl.Population;
import org.example.business.impl.Selection;
import org.example.business.impl.Union;


public class CoutVisiteur implements Visiteur {
    private int cout = 0;

    @Override
    public void visiter(Population p) {
        cout += 1;
    }

    @Override
    public void visiter(Selection s) {
        cout += 1;
        if (s.getSousRequetes().isPresent()) {
            for (Requete req : s.getSousRequetes().get()) {
                req.accepter(this);
            }
        }
    }

    @Override
    public void visiter(Difference d) {
        cout += 2;
        if (d.getSousRequetes().isPresent()) {
            for (Requete req : d.getSousRequetes().get()) {
                req.accepter(this);
            }
        }
    }

    @Override
    public void visiter(Union u) {
        cout += u.getSousRequetes().isPresent() ? u.getSousRequetes().get().size() : 0;
        if (u.getSousRequetes().isPresent()) {
            for (Requete req : u.getSousRequetes().get()) {
                req.accepter(this);
            }
        }
    }

    @Override
    public int getCout() {
        return cout;
    }
    
}
