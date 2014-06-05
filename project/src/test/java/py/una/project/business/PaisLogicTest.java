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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import py.una.pol.karaku.test.base.BaseTestWithDatabase;
import py.una.pol.karaku.test.configuration.TransactionTestConfiguration;
import py.una.pol.karaku.test.util.TestUtils;
import py.una.project.business.IPaisLogic;
import py.una.project.business.PaisLogic;
import py.una.project.domain.Pais;
import py.una.project.repo.IPaisDao;
import py.una.project.repo.PaisDao;

/**
 * 
 * @author Arturo Volpe
 * @since 1.0
 * @version 1.0 Jun 4, 2014
 * 
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class PaisLogicTest extends BaseTestWithDatabase {

	@Configuration
	@EnableTransactionManagement()
	static class ContextConfiguration extends TransactionTestConfiguration {

		@Override
		public Class<?>[] getEntityClasses() {

			return TestUtils.getAsClassArray(Pais.class);
		}

		@Bean
		IPaisDao dao() {

			return new PaisDao();
		}

		@Bean
		IPaisLogic logic() {

			return new PaisLogic();
		}

	}

	@Autowired
	private IPaisLogic paisLogic;

	@Test
	public void testGetPaises() throws Exception {

		Pais p100_000 = paisLogic.getById(2L);
		Pais p500_000 = paisLogic.getById(3L);
		Pais p1_000_000 = paisLogic.getById(1L);
		Pais p5_000_000 = paisLogic.getById(4L);

		List<Pais> mp100 = paisLogic.getPaisesConPoblacionMayorA(p100_000);
		assertEquals(3, mp100.size());
		assertThat(mp100, hasItems(p500_000, p1_000_000, p5_000_000));

		List<Pais> mp500 = paisLogic.getPaisesConPoblacionMayorA(p500_000);
		assertEquals(2, mp500.size());
		assertThat(mp500, hasItems(p1_000_000, p5_000_000));

		List<Pais> mp1_000 = paisLogic.getPaisesConPoblacionMayorA(p1_000_000);
		assertEquals(1, mp1_000.size());
		assertThat(mp1_000, hasItem(p5_000_000));

		List<Pais> mp5_000 = paisLogic.getPaisesConPoblacionMayorA(p5_000_000);
		assertTrue(mp5_000.isEmpty());
	}
}
