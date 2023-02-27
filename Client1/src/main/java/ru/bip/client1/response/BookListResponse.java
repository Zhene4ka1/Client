package ru.bip.client1.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.bip.client1.entity.BookEntity;

import java.util.List;

@RequiredArgsConstructor
public class BookListResponse extends BaseResponse{
    public List<BookEntity> data;
    public  BookListResponse(List<BookEntity> data){
        super(true, "Список книг");
        this.data = data;
    }
}
