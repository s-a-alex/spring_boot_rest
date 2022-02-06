package getphos.spring.spring_boot_rest.dao;



import getphos.spring.spring_boot_rest.entity.Singer;

public interface SingerService {
    Singer[] findAll();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(long id);
}
