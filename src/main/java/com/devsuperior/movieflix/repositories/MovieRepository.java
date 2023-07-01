package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {


//    @Query(nativeQuery = true,value = """
//            SELECT tb_movie.id, title, sub_title, movie_year, img_url
//            FROM tb_movie
//            WHERE UPPER(title)
//            LIKE UPPER(CONCAT('%', :title,'%'))
//            AND (genre_id IS NULL OR genre_id = :genreId);""",
//            countQuery = """
//            SELECT COUNT(*) FROM ( SELECT tb_movie.id, title, sub_title, movie_year, img_url
//            FROM tb_movie
//            WHERE UPPER(title)
//            LIKE UPPER(CONCAT('%', :title,'%'))
//            AND (genre_id IS NULL OR genre_id = :genreId)) AS tb_result;""")

            @Query("SELECT m FROM Movie m " +
            "WHERE UPPER(m.title) LIKE UPPER(CONCAT('%', :title,'%')) " +
            "AND (:genreId IS NULL OR m.genre.id = :genreId) ORDER BY m.title")
            Page<Movie> findAllCards(Pageable pageable, String title, Long genreId);

}
