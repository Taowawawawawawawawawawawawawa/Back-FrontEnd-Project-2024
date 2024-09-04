package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import th.mfu.domain.Round;
import th.mfu.dto.RoundDTO;

@Mapper(componentModel = "spring")
public interface RoundMapper  {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateRoundFromDto(RoundDTO dto,@MappingTarget Round entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateRoundFromEntity(Round entity,@MappingTarget RoundDTO dto);
}
