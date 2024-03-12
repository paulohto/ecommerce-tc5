package com.ecommercetc5.loginusers.complement.controller;

import com.ecommercetc5.loginusers.complement.dto.ComplementDto;
import com.ecommercetc5.loginusers.complement.dto.ComplementUserDto;
import com.ecommercetc5.loginusers.complement.service.ComplementService;
import com.ecommercetc5.loginusers.user.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/complement")
public class ComplementController {
    private final ComplementService complementService;
    public ComplementController(ComplementService complementService) {
        this.complementService = complementService;
    }

    @PostMapping("/save")
    public ResponseEntity<ComplementUserDto> saveComplement(@Valid @RequestBody ComplementUserDto dto){
        var cplSaved = complementService.saveComplement(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(cplSaved.id()).toUri();
        return ResponseEntity.created(uri).body(cplSaved);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ComplementUserDto>> findAll(
            @RequestParam (value = "page", defaultValue = "0") int page,
            @RequestParam (value = "size", defaultValue = "10") int size
    ){
        PageRequest pageRequest = PageRequest.of(page, size);
        var complements = complementService.findAll(pageRequest);
        return ResponseEntity.ok(complements);
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<ComplementUserDto> findById(@PathVariable Long id){
        var complement = complementService.findById(id);
        return ResponseEntity.ok(complement);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ComplementUserDto> updateComplement(@Valid @PathVariable Long id, @RequestBody ComplementUserDto dto){
        ComplementUserDto cplSaved = complementService.updateComplement(id,dto);
        return ResponseEntity.ok(cplSaved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        complementService.deleteComplementById(id);
        return ResponseEntity.noContent().build();
    }
}
