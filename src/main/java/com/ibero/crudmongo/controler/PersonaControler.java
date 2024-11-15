/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibero.crudmongo.controler;

import com.ibero.crudmongo.model.Persona;
import com.ibero.crudmongo.service.PersonaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/personas")
public class PersonaControler {
    
    @Autowired
    private PersonaService personaservice;
    
    @GetMapping("/all")
    public ResponseEntity<List<Persona>>getAllPersonas(){
        
        List<Persona> personas = personaservice.getAllPersonas();
        return ResponseEntity.ok().body(personas);
    }
    
    @GetMapping("/byid/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable long id) {

        Optional<Persona> persona = personaservice.getPersonaById(id);
        
        if (persona.isPresent()){
            return ResponseEntity.ok().body(persona.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Persona> savePersona(@RequestBody @Validated Persona persona){
        
        Persona savepersona = personaservice.savePersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(savepersona);
    }
    
    
}
