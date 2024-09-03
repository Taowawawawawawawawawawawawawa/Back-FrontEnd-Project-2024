package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import th.mfu.domain.Movie;
import th.mfu.dto.MovieDTO;

@Mapper(componentModel = "spring")
public interface MovieMapper  {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateMovieFromDto(MovieDTO dto,@MappingTarget Movie entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateMovieFromEntity(Movie entity,@MappingTarget MovieDTO dto);
}
