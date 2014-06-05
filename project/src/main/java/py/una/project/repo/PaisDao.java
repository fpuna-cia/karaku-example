/*-
 * Copyright (c)
 *
 * 		2012-2014, Facultad Politécnica, Universidad Nacional de Asunción.
 * 		2012-2014, Facultad de Ciencias Médicas, Universidad Nacional de Asunción.
 * 		2012-2013, Centro Nacional de Computación, Universidad Nacional de Asunción.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package py.una.project.repo;

import static py.una.pol.karaku.util.Checker.notNull;
import java.util.List;
import org.springframework.stereotype.Repository;
import py.una.pol.karaku.dao.restrictions.Where;
import py.una.pol.karaku.dao.search.SearchParam;
import py.una.pol.karaku.dao.where.Clauses;
import py.una.pol.karaku.math.Quantity;
import py.una.pol.karaku.repo.KarakuBaseDao;
import py.una.project.domain.Pais;

/**
 * 
 * @author Arturo Volpe
 * @since 1.0
 * @version 1.0 Jun 4, 2014
 * 
 */
@Repository
public class PaisDao extends KarakuBaseDao<Pais, Long> implements IPaisDao {

	@Override
	public List<Pais> getPaisesConPoblacionMayorA(Quantity qa) {

		notNull(qa, "La población no puede ser nula");
		Where<Pais> where = Where.get();
		where.addClause(Clauses.gt("habitantes", qa));
		return getAll(where, new SearchParam().addOrder("descripcion"));
	}
}
