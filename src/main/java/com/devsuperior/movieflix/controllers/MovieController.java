package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> findByGenre(Pageable pageable,
                                                          @RequestParam(name = "name", defaultValue = "") String name,
                                                          @RequestParam(name = "genreId", defaultValue = "0") String genreId)
    {
        Long genre = null;
        if (!genreId.equals("0")){
            genre = Long.parseLong(genreId);
        }
        Page<MovieCardDTO> page = service.findAllCards(pageable, name, genre);
        return ResponseEntity.ok().body(page);
    }

    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id){
        MovieDetailsDTO result = service.findById(id);
        return ResponseEntity.ok().body(result);
    }
}
