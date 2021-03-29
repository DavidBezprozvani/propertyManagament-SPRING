package com.example.realEstate.controller;

import com.example.realEstate.entity.dto.PropertyDTO;
import com.example.realEstate.service.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private PropertyServiceImpl propertyServiceImpl;

    public PropertyController(PropertyServiceImpl propertyServiceImpl) {
        this.propertyServiceImpl = propertyServiceImpl;
    }

    @GetMapping
    public List<PropertyDTO> getAllProperties() {
        return propertyServiceImpl.getAllProperties();
    }

    @GetMapping("/{id}")
    public PropertyDTO getSinglePropertyById(@PathVariable Long id) {
        return propertyServiceImpl.getSinglePropertyById(id);
    }

    @GetMapping("/owner/{id}")
    public List<PropertyDTO> getAllPropertiesByOwner(@PathVariable Long id) {
        return propertyServiceImpl.getAllPropertiesByOwner(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyDTO saveProperty(@RequestBody @Valid PropertyDTO propertyDTO) {
        return propertyServiceImpl.createProperty(propertyDTO);
    }

    @PutMapping("edit/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PropertyDTO updatePost(@RequestBody @Valid PropertyDTO propertyDTO) {
        return propertyServiceImpl.updateProperty(propertyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        propertyServiceImpl.deleteProperty(id);
    }
}
