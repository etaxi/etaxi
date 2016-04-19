package lv.etaxi.dao.hibernate;

import lv.etaxi.dao.DBException;
import lv.etaxi.dao.GenericDAO;
import lv.etaxi.dao.jdbc.DBConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Transactional
    public abstract class DAOImpl<T> extends DBConnection implements GenericDAO<T> {

        private Class<T> persistentClass;

        @SuppressWarnings("unchecked")
        public DAOImpl() {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0];
        }

        @Autowired
        public SessionFactory sessionFactory;

        public Session getCurrentSession() {
            return sessionFactory.getCurrentSession();
        }

        @Override
        public void create(T type) throws DBException {
            getCurrentSession().persist(type);
        }

        @Override
        public T getById(Long id) throws DBException {
            return (T) getCurrentSession().get(persistentClass, id);
        }

        @Override
        public void delete(Long id) throws DBException {
            Session session = sessionFactory.getCurrentSession();
            T type = (T) session.get(persistentClass, id);
            session.delete(type);
        }

        @Override
        public long update(T type) throws DBException {
            getCurrentSession().update(type);
            return 0;
        }

        @Override
        public List<T> getAll() throws DBException {
            return getCurrentSession().createCriteria(persistentClass).list();
        }
    }


