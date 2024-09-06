package th.mfu.dto.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.Seat;
import th.mfu.domain.Theatre;
import th.mfu.dto.SeatDTO;
import th.mfu.dto.TheatreDTO;

@Mapper(componentModel = "spring")
public interface TheatreMapper {
    @Mapping(target = "seats", source = "seats")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateTheatreFromDto(TheatreDTO dto, @MappingTarget Theatre entity);

    // Map seats as well when updating TheatreDTO from Theatre entity
    @Mapping(target = "seats", source = "seats")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTheatreFromEntity(Theatre entity, @MappingTarget TheatreDTO dto);

    // Add a mapping method for converting Seat to SeatDTO
    SeatDTO toSeatDTO(Seat seat);

    @Mapping(target = "seats", source = "seats")
    TheatreDTO toTheatreDTO(Theatre entity);

    @Mapping(target = "seats", source = "seats")
    Theatre toTheatre(TheatreDTO dto);

    List<SeatDTO> toSeatDTOs(List<Seat> seats);

}
