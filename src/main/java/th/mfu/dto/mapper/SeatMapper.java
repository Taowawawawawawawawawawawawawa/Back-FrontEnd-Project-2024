package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import th.mfu.domain.Seat;
import th.mfu.dto.SeatDTO;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateSeatFromDto(SeatDTO dto, @MappingTarget Seat entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateSeatFromEntity(Seat entity, @MappingTarget SeatDTO dto);
}
