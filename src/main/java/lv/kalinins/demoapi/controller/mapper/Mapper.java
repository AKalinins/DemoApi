package lv.kalinins.demoapi.controller.mapper;

public interface Mapper<T, S> {

    S convertToDto(T entity);
    T convertToEntity(S dto);
}
