package re.ss13.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import re.ss13.model.Person;
import re.ss13.repository.IPersonRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements IPersonRepository {
    private final SessionFactory sessionFactory;

    @Override
    public List<Person> getAll() {

        // Mở phiên thao tác với database
        Session session = sessionFactory.openSession();

        // Cú pháp HQL - Hibernate Query Language
        List<Person> personList = session.createQuery(
                "SELECT p FROM Person p",
                Person.class
        ).list();

        // Đóng session lại
        session.close();

        return personList;
    }

    @Override
    public void save(Person person) {
        /**
         * getCurrentSession khác gì so với openSession
         * persist + merge + remove
         * */

        // Mở phiên thao tác với database
        Session session = sessionFactory.openSession();

        // Lưu dữ liệu
        session.save(person);
        // persist <=> save
        // merge <=> update
        // remove <=> delete

        // Đóng session
        session.close();
    }
}
