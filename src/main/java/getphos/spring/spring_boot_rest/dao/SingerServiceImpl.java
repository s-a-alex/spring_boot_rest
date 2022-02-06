package getphos.spring.spring_boot_rest.dao;

import getphos.spring.spring_boot_rest.entity.Singer;
import getphos.spring.spring_boot_rest.repo.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.StreamSupport;

@Service
@Transactional
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerRepository singerRepository;

    @Transactional(readOnly=true)
    @Override
    public Singer[] findAll() {
        return StreamSupport.stream(singerRepository.findAll().spliterator(), false).toArray(size->new Singer[size]);
    }

    @Transactional(readOnly=true)
    @Override
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void delete(long id) {singerRepository.deleteById(id);}
}
