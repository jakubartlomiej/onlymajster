package com.jakubartlomiej.onlymajster.publication.model;

import com.jakubartlomiej.onlymajster.auditing.Auditable;
import com.jakubartlomiej.onlymajster.category.model.Category;
import com.jakubartlomiej.onlymajster.user.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "publication")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Publication extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String text;
    private String photo;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}