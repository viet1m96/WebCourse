package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.Point;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

import java.util.List;

public class DBTestHibernate {
    @PersistenceContext(name="appPU")
    private EntityManager em;

    public void request(String sid, String from, String to, Integer limit) {
        Session hib = em.unwrap(Session.class);

        Criteria crit = hib.createCriteria(Point.class)
                .add(Restrictions.eq("sessionId", sid))
                .add(Restrictions.between("releaseTime", from, to))
                .add(Restrictions.eq("hit", true))
                .addOrder(Order.desc("releaseTime"))
                .setMaxResults(limit);

        @SuppressWarnings("unchecked")
        List<Point> points = crit.list();
    }
}
