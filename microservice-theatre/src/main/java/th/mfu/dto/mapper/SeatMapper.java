package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import th.mfu.domain.Seat;
import th.mfu.domain.Theatre;
import th.mfu.dto.SeatDTO;
import th.mfu.repository.TheatreRepository;

@Mapper(componentModel = "spring", uses = TheatreMapper.class)
public interface SeatMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSeatFromDto(SeatDTO dto, @MappingTarget Seat entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSeatFromEntity(Seat entity, @MappingTarget SeatDTO dto);

    @Mapping(target = "theatreId", source = "seat.theatre.id")
    SeatDTO toSeatDTO(Seat seat);

    @Mapping(target = "seatId", source = "dto.seatId")
    @Mapping(target = "theatre", source = "theatre")  // Explicitly map theatre
    Seat toSeat(SeatDTO dto, Theatre theatre); // Now pass Theatre directly when calling this method
}
