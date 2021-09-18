package br.gov.sp.fatec.entrepreneur.controller;

import br.gov.sp.fatec.entrepreneur.domain.Entrepreneur;
import br.gov.sp.fatec.entrepreneur.service.EntrepreneurService;
import br.gov.sp.fatec.entrepreneur.view.EntrepreneurView;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.project.view.ProjectView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping("dev/entrepreneur")
public class EntrepreneurController {

    @Autowired
    private EntrepreneurService service;

    @Autowired
    private ProjectService projectService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    @JsonView(EntrepreneurView.Entrepreneur.class)
    public Entrepreneur create (@RequestBody Entrepreneur entrepreneur, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.path("/dev/entrepreneur/activate/").build().toUriString();
        return service.save(entrepreneur, url);
    }

    @GetMapping(produces =  APPLICATION_JSON_VALUE)
    @JsonView(EntrepreneurView.Entrepreneur.class)
    public List<Entrepreneur> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView(EntrepreneurView.Entrepreneur.class)
    public Entrepreneur findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    @JsonView(EntrepreneurView.Entrepreneur.class)
    public Entrepreneur update(@RequestBody Entrepreneur entrepreneur) {
        return  service.save(entrepreneur);
    }

    @GetMapping(value = "/active")
    @JsonView(EntrepreneurView.Entrepreneur.class)
    public List<Entrepreneur> findActive() {
        return service.findActive();
    }

    @GetMapping(value = "/activate/{b64}")
    public void activate(@PathVariable("b64") String b64) {
        service.activate(b64);
    }

    @PutMapping(value = "/update")
    @JsonView(ProjectView.Project.class)
    public Project update(@RequestBody Project project) {
        return projectService.update(project);
    }
}
