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
package py.una.project.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.envers.Audited;
import py.una.pol.karaku.dao.entity.annotations.URI;
import py.una.pol.karaku.dao.entity.annotations.URI.Type;
import py.una.pol.karaku.domain.BaseEntity;
import py.una.pol.karaku.math.Quantity;
import py.una.pol.karaku.model.DisplayName;
import py.una.pol.karaku.model.Unique;
import py.una.pol.karaku.util.ValidationConstants;

/**
 * 
 * @author Arturo Volpe
 * @since 1.0
 * @version 1.0 Jun 4, 2014
 * 
 */
@Entity
@Audited
@Table(name = "pais")
@SequenceGenerator(name = "PAIS_SEQ", sequenceName = "pais_id_seq")
public class Pais extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -765796731841850271L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAIS_SEQ")
	private Long id;

	@Column(scale = 15, precision = 4, columnDefinition = "NUMERIC")
	private Quantity habitantes;

	@NotNull
	@Size(min = 1, max = 100, message = "{LENGTH}")
	@Pattern(regexp = ValidationConstants.WORD, message = "{ONLY_STRING}")
	@Unique("uq_sexo_abreviatura")
	@DisplayName(key = "{pais.descripcion}")
	private String descripcion;

	@NotNull
	@Size(max = 100, message = "{LENGTH}")
	@URI(type = Type.FIELD, baseUri = "www.sistem.com/paises/", field = "descripcion")
	@Unique("uq_sexo_uri")
	private String uri;

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	@Override
	public Long getId() {

		return id;
	}

	/**
	 * @return habitantes
	 */
	public Quantity getHabitantes() {

		return habitantes;
	}

	/**
	 * @param habitantes
	 *            habitantes para setear
	 */
	public void setHabitantes(Quantity habitantes) {

		this.habitantes = habitantes;
	}

	/**
	 * @return descripcion
	 */
	public String getDescripcion() {

		return descripcion;
	}

	/**
	 * @param descripcion
	 *            descripcion para setear
	 */
	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
	}

	/**
	 * @return uri
	 */
	public String getUri() {

		return uri;
	}

	/**
	 * @param uri
	 *            uri para setear
	 */
	public void setUri(String uri) {

		this.uri = uri;
	}

}
