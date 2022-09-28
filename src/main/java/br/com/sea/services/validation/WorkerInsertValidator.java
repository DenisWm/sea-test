package br.com.sea.services.validation;

import br.com.sea.dto.InsertWorkerDTO;
import br.com.sea.entities.Worker;
import br.com.sea.repositories.WorkerRepository;
import br.com.sea.resources.exceptions.FieldMessage;
import br.com.sea.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkerInsertValidator implements ConstraintValidator<WorkerInsert, InsertWorkerDTO> {

    @Autowired
    WorkerRepository workerRepository;

    @Override
    public void initialize(WorkerInsert ann) {
    }

    @Override
    public boolean isValid(InsertWorkerDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(!BR.isValidCPF(objDto.getCpf())) {
            list.add(new FieldMessage("cpf", "CPF inválido"));
        }

        Optional<Worker> aux = workerRepository.findByCpf(objDto.getCpf());

        if(aux.isPresent()) {
            list.add(new FieldMessage("cpf", "Cpf já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
