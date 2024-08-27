package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.CustomerTemp;
import th.mfu.domain.Ticket;
import th.mfu.dto.CustomerTempDTO;
import th.mfu.dto.TicketDTO;

@Mapper
public interface CustomerTempMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCustomerFromDto(CustomerTempDTO dto,@MappingTarget CustomerTemp entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCustomerFromEntity(CustomerTemp entity,@MappingTarget CustomerTempDTO dto);
}
