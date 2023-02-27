package ru.bip.client1.entity;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookEntity {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private String year;
    private String kind;

}
