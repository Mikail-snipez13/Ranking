package mikail.Ranking.Interface;

public interface SimpleService<Type> {

    public void create(String data);
    public Type get(Long id);
    default public void delete(Long id){};

    default public void update(Long id, String data) {};
}
