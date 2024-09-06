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

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // @Mapping(target = "theatre", source = "theatreId", qualifiedByName = "toTheatre")
    void updateSeatFromDto(SeatDTO dto, @MappingTarget Seat entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // @Mapping(target = "theatreId", source = "theatre.id")
    void updateSeatFromEntity(Seat entity, @MappingTarget SeatDTO dto);

    @Named("toTheatre")
    default Theatre toTheatre(Long theatreId, @Autowired TheatreRepository theatreRepository) {
        return theatreRepository.findById(theatreId).orElse(null);
    }
}
