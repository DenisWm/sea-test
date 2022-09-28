package br.com.sea.services.validation;

import br.com.sea.dto.SectorDTO;
import br.com.sea.repositories.SectorRepository;
import br.com.sea.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class SectorValidator implements ConstraintValidator<Sector, SectorDTO> {

    @Autowired
    SectorRepository SectorRepository;

    @Override
    public void initialize(Sector ann) {
    }

    @Override
    public boolean isValid(SectorDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();


        br.com.sea.entities.Sector aux = SectorRepository.findByName(objDto.getName());

        if(aux != null) {
            list.add(new FieldMessage("name", "Nome já existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
