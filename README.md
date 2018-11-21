# BooksApi
Основные настройки к сервису распологоаются в файле /src/main/java/resources
Описание контроллеров можно найти в swagger-ui, для этого нужно перейти по ссылке localhost:{server.port}/swagger-ui.html#/

Поля сущностей: для доступа к ним через REST:
Book: id, name , series, countOfPage, discription, authorId, genreId, cost;
Author: id, firstName, lastName, dateOfBirthDay(например "1999-10-10");
Genre: id, genre;
Desire: id, userId, bookId;

Заполнение тела запросов при POST/PUT/DELETE, заполняется в json формате:
При обновлении сущности(POST): обязательное поле - id, доступные поля - все остальные;
При удалении сущности(DELETE): обязательное поле - id, других доступных полей нет;
При создании сущности(PUT): обязательного поля нет, доступные поля - все остальные. Если поле не введено, то оно заполнится пустым значением.

Примеры заполнения тела запросов:
Создание жанра:
{
    "genre":"Genre"
}

При получения списка книг или одной книги(GET), responce имеет следующий формат:

{
   "result": "success",
   "data":    {
      id1:       {
         "firstName": firstName1,
         "lastName": lastName1,
         "dateOfBirthDay": dateOfBirthDay1
      },
      id2:       {
         "firstName": firstName2,
         "lastName": lastName,
         "dateOfBirthDay": dateOfBirthDay2
      },
      ...,
      idN:       {
         "firstName": firstNameN,
         "lastName": lastNameN,
         "dateOfBirthDay": dateOfBirthDayN
      }
   }
}
