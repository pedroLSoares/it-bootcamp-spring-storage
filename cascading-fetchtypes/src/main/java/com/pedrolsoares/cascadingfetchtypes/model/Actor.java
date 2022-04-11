package com.pedrolsoares.cascadingfetchtypes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private BigDecimal rating;

    @OneToOne
    @JoinColumn(name = "favorite_movie_id")
    private Movie favoriteMovieId;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Actor(String firstName, String lastName, BigDecimal rating, Movie favoriteMovieId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.favoriteMovieId = favoriteMovieId;
    }
}
