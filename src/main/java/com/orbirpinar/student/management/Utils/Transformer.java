package com.orbirpinar.student.management.Utils;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.*;

@NoArgsConstructor
public class Transformer {

    private static ModelMapper modelMapper = new ModelMapper();



    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }


    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }


    public static <D, T> Page<D> mapPaginate(final Page<T> entityList, Class<D> outCLass) {
        return entityList.map(entity -> map(entity,outCLass));
    }


    public static <D, T> D mapOptional(final Optional<T> entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }


    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

}
