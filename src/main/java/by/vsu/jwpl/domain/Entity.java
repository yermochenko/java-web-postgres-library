package by.vsu.jwpl.domain;

import java.io.Serializable;
import java.util.Objects;

abstract public class Entity implements Serializable {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		Entity entity = (Entity) obj;
		return Objects.equals(id, entity.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
