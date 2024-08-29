package th.mfu.domain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MovieMapper  {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDTO toDto(Movie movie);
    Movie toEntity(MovieDTO movieDTO);
}
