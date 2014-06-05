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
package py.una.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import py.una.pol.karaku.configuration.KarakuBaseConfiguration;
import py.una.pol.karaku.controller.KarakuAdvancedController;
import py.una.pol.karaku.dao.restrictions.Where;
import py.una.pol.karaku.dao.where.Clauses;
import py.una.pol.karaku.math.Quantity;
import py.una.pol.karaku.util.I18nHelper;
import py.una.project.business.IPaisLogic;
import py.una.project.domain.Pais;

/**
 * 
 * @author Arturo Volpe
 * @since 1.0
 * @version 1.0 Jun 4, 2014
 * 
 */
@Controller
@Scope(value = KarakuBaseConfiguration.SCOPE_CONVERSATION)
public class PaisController extends KarakuAdvancedController<Pais, Long> {

	@Autowired
	private IPaisLogic paisLogic;

	@Autowired
	private I18nHelper helper;

	@Override
	public IPaisLogic getBaseLogic() {

		return paisLogic;
	}

	@Override
	public List<String> getBaseSearchItems() {

		List<String> searchItems = super.getBaseSearchItems();
		searchItems.add(0, helper.getString("pais.busqueda.mayorA"));
		return searchItems;
	}

	@Override
	public Where<Pais> getBaseWhere() {

		if (isSimpleSearch()) {
			if (helper.compare("pais.busqueda.mayorA", getFilterOption())) {
				Where<Pais> where = Where.get();
				return where.addClause(Clauses.gt("habitantes", new Quantity(
						getFilterValue())));

			}
		}
		return super.getBaseWhere();
	}
}
