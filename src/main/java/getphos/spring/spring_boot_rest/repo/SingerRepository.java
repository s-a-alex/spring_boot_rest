package getphos.spring.spring_boot_rest.repo;

import getphos.spring.spring_boot_rest.entity.Singer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SingerRepository extends PagingAndSortingRepository<Singer, Long> {
}
