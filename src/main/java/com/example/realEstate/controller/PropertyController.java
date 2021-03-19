package com.example.realEstate.controller;

import com.example.realEstate.entity.dto.PropertyDTO;
import com.example.realEstate.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {


    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<PropertyDTO> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public PropertyDTO getSinglePropertyById(@PathVariable Long id) {
        return propertyService.getSinglePropertyById(id);
    }

    @GetMapping("/owner/{id}")
    public List<PropertyDTO> getAllPropertiesByOwner(@PathVariable Long id) {
        return propertyService.getAllPropertiesByOwner(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyDTO saveProperty(@RequestBody @Valid PropertyDTO propertyDTO) {
        return propertyService.createProperty(propertyDTO);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PropertyDTO updatePost(@RequestBody @Valid PropertyDTO propertyDTO) {
        return propertyService.updateProperty(propertyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }
}
