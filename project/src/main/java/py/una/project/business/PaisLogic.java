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
package py.una.project.business;

import static py.una.pol.karaku.util.Checker.notNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.una.pol.karaku.business.KarakuBaseLogic;
import py.una.project.domain.Pais;
import py.una.project.repo.IPaisDao;

/**
 * 
 * @author Arturo Volpe
 * @since 1.0
 * @version 1.0 Jun 4, 2014
 * 
 */
@Service
public class PaisLogic extends KarakuBaseLogic<Pais, Long> implements
		IPaisLogic {

	@Autowired
	private IPaisDao dao;

	@Override
	public IPaisDao getDao() {

		return dao;
	}

	@Override
	public List<Pais> getPaisesConPoblacionMayorA(Pais otroPais) {

		notNull(otroPais, "pais null");
		notNull(otroPais.getId(), "pais con id null");
		Pais p = getById(otroPais.getId());

		return getDao().getPaisesConPoblacionMayorA(p.getHabitantes());
	}

}
