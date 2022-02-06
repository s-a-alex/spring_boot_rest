package getphos.spring.spring_boot_rest.web;

import getphos.spring.spring_boot_rest.dao.SingerService;
import getphos.spring.spring_boot_rest.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/singer")
public class SingerController {
    private final Logger logger = LoggerFactory.getLogger(SingerController.class);

    @Autowired
    private SingerService singerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listdata")
    public Singer[] singers() {
        return singerService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Singer show(@PathVariable("id") Long id) {
        return singerService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Singer create(@RequestBody Singer singer) {
        return singerService.save(singer);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "{id}")
    public void update(@RequestBody Singer singer, @PathVariable("id") Long id) {
        singer.setId(id);
        singerService.save(singer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        singerService.delete(id);
    }
}
