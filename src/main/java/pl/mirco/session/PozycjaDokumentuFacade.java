/*
 * Copyright (C) 2014 mradwanski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pl.mirco.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.mirco.jpa.PozycjaDokumentu;

/**
 *
 * @author mradwanski
 */
@Stateless
public class PozycjaDokumentuFacade extends AbstractFacade<PozycjaDokumentu> {
    @PersistenceContext(unitName = "pl.mirco_fakturkaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PozycjaDokumentuFacade() {
        super(PozycjaDokumentu.class);
    }
    
}
