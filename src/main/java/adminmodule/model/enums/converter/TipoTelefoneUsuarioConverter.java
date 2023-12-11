/*
 * TipoTelefoneUsuarioConverter.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package adminmodule.model.enums.converter;

import adminmodule.model.enums.TipoTelefone;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Classe de conversão JPA referente ao enum {@link TipoTelefone}.
 * 
 * @author UEG
 */
@Converter(autoApply = true)
public class TipoTelefoneUsuarioConverter implements AttributeConverter<TipoTelefone, Long> {

	/**
	 * @see AttributeConverter#convertToDatabaseColumn(Object)
	 */
	@Override
	public Long convertToDatabaseColumn(final TipoTelefone tipo) {
		return tipo != null ? tipo.getId() : null;
	}

	/**
	 * @see AttributeConverter#convertToEntityAttribute(Object)
	 */
	@Override
	public TipoTelefone convertToEntityAttribute(Long id) {
		return TipoTelefone.getById(id);
	}

}
