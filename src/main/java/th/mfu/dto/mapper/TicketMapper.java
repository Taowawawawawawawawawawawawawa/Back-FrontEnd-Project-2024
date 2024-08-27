package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.Ticket;
import th.mfu.dto.TicketDTO;

@Mapper
public interface TicketMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateTicketFromDto(TicketDTO dto,@MappingTarget Ticket entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateTicketFromEntity(Ticket entity,@MappingTarget TicketDTO dto);
}
