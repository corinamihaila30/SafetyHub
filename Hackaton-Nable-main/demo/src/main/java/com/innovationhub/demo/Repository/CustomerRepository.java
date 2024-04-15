package com.innovationhub.demo.Repository;

import com.innovationhub.demo.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
//    Ce se intampla in Background?In loc sa se foloseasca un DAO (Data access object)
//     Adica o interfata creata de noi special pe care sa definim metode de save, get, etc.
//    Spring Data JPA e un proiect care face asta pentru noi.
//    Implementarea e oferita de Hibernate si de ce are nevoie ? El o sa salveze obiectele
//    cu ajutorul EntityManager. EntityManager.persist(), etc. Si ca sa poata salva are nevoie de
//    obiectul in sine pe care dorim sa il salvam si ca sa poata stii ORM-ul catre baza de
//    date si are nevoie de tipul Primary key-ului pentru a putea efectua cautarea dupa
//    Cheia primara
    // Deci in mod normal aveam: DAO, DAOImplement, EntityManager.persist(Customer) -> JDBC -> DB
    // Iar acum cu ajutorul genericitatii avem direct interfata pe care o foloseste hibernate
    // Si implementarea de Repository foloseste EntityManager -> JDBC -> DB
}
