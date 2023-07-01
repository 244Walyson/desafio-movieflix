package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.controllers.ReviewController;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review review = new Review();
        Movie movie = movieRepository.getReferenceById(dto.getMovieId());
        User user = authService.authenticated();
        review.setText(dto.getText());
        review.setMovie(movie);
        review.setUser(user);
        repository.save(review);
        return new ReviewDTO(review);
    }
}
