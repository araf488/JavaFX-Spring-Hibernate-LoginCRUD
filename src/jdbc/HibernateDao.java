package jdbc;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateDao {

    private List< Employee> list;
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean login(Employee e) {
        try {

            Query query = getSessionFactory().openSession().createQuery("SELECT u FROM Employee u WHERE u.name=:name AND u.pass =:pass");
            query.setString("name", e.getName());
            query.setString("pass", e.getPass());

            List<Employee> cList = query.list();
            cList.toString();

//            
            if (cList.size() > 0) {
                System.out.println("OK");
                return true;
            } else {
                System.out.println("Not OKkkkkk");
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;

        }
    }

    public Employee getId(int id) {
        return (Employee) getSessionFactory().getCurrentSession().get(Employee.class, id);
    }

    public List< Employee> getEmployee() {

//        String hql = "select * from Employee";
        list = getSessionFactory().openSession().createCriteria(Employee.class).list();
        return list;

    }

    @Transactional
    public void saveEmployee(Employee e) {
        getSessionFactory().getCurrentSession().save(e);
    }

    @Transactional
    public void updateEmployee(Employee e) {
        getSessionFactory().getCurrentSession().update(e);
    }

//    @Transactional
//    public void deleteEmployee(Employee e){
//    getSessionFactory().getCurrentSession().delete(e);
//    }
    @Transactional
    public void deleteEmployee(int id) {
        getSessionFactory().getCurrentSession().delete(getId(id));
    }
}
