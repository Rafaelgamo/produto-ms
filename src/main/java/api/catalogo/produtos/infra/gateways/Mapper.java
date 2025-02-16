package api.catalogo.produtos.infra.gateways;

public interface Mapper {

    <D> D map(Object source, Class<D> destinationType);
    void map(Object source, Object destination);
}
