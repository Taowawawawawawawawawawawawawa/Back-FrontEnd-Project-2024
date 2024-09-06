package th.mfu.dto.mapper;

import java.util.List;

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
import th.mfu.dto.TheatreDTO;
import th.mfu.repository.TheatreRepository;

@Mapper(componentModel = "spring")
public interface TheatreMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTheatreFromDto(TheatreDTO dto, @MappingTarget Theatre entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTheatreFromEntity(Theatre entity, @MappingTarget TheatreDTO dto);

    @Mapping(target = "seats", source = "seats")
    TheatreDTO toTheatreDTO(Theatre entity);

    @Mapping(target = "seats", source = "seats")
    Theatre toTheatre(TheatreDTO dto);

    List<SeatDTO> toSeatDTOs(List<Seat> seats);

    List<Seat> toSeats(List<SeatDTO> seatDTOs);
}
