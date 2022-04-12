package com.pedrolsoares.hql.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer number;

    @Column
    private Date releaseDate;

    @Column
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    @JsonBackReference
    private Serie serie;


    @OneToMany(mappedBy = "season")
    @JsonManagedReference
    @JsonIgnoreProperties(value = "season")
    private List<Episode> episodes;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Season(String title, Integer number, Date releaseDate, Date endDate, List<Episode> episodes, Serie serie) {
        this.title = title;
        this.number = number;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.episodes = episodes;
        this.serie = serie;
    }
}
