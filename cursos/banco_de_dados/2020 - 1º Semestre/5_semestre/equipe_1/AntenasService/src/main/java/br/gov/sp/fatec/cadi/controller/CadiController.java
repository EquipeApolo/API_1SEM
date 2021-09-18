package br.gov.sp.fatec.cadi.controller;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.service.CadiService;
import br.gov.sp.fatec.cadi.view.CadiView;
import br.gov.sp.fatec.project.domain.Date;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.project.view.ProjectView;
import br.gov.sp.fatec.user.domain.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping("/dev/cadi")
public class CadiController {

    @Autowired
    CadiService service;

    @Autowired
    ProjectService projectService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @JsonView(CadiView.Cadi.class)
    private Cadi create (@RequestBody Cadi cadi, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.path("/dev/cadi/activate/").build().toUriString();
        return service.save(cadi, url);
    }

    @GetMapping(produces =  APPLICATION_JSON_VALUE)
    @JsonView(CadiView.Cadi.class)
    public List<Cadi> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView(CadiView.Cadi.class)
    public Cadi findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/activate/{b64}")
    public void activate(@PathVariable("b64") String b64) {
        service.activate(b64);
    }

    @PutMapping(value = "/{id}")
    @JsonView(CadiView.Cadi.class)
    public Cadi update(@PathVariable("id") Long id,
                       @RequestBody Cadi cadi) {
        return  service.save(cadi);
    }

    @GetMapping(value = "/active")
    @JsonView(CadiView.Cadi.class)
    public List<Cadi> findActive() {
        return service.findActive();
    }

    @PutMapping(value = "/approve/{id}")
    @JsonView(ProjectView.Project.class)
    public Project approve(@PathVariable("id") Long id) {
        return projectService.approve(id);
    }
}
