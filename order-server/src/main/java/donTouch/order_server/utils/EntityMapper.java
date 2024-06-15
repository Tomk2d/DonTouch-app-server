package donTouch.order_server.utils;

public interface EntityMapper<D, E> {
    E toEntity(final D dto);
    D toDto(final E entity);
}
