package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.Customer;
import th.mfu.domain.Ticket;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.TicketDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCustomerFromDto(CustomerDTO dto,@MappingTarget Customer entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCustomerFromEntity(Customer entity,@MappingTarget CustomerDTO dto);
}
